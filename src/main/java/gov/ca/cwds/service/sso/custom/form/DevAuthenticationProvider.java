package gov.ca.cwds.service.sso.custom.form;

import static gov.ca.cwds.config.Constants.IDENTITY;
import static gov.ca.cwds.config.Constants.IDENTITY_JSON;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import gov.ca.cwds.PerryProperties;
import gov.ca.cwds.UniversalUserToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * username format: user:role1,role2 password can by any
 */
@Profile("dev")
@Component
public class DevAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  PerryProperties perryProperties;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    tryAuthenticate(authentication);
    String json = authentication.getName();
    json = cognitoJsonToPerryJson(json);
    Map userInfo = getUserInfo(json);
    String userName = (String) userInfo.get("user");
    UniversalUserToken userToken = new UniversalUserToken();
    userToken.setToken(UUID.randomUUID().toString());
    userToken.setUserId(userName);
    userToken.setParameter(IDENTITY, userInfo);
    userToken.setParameter(IDENTITY_JSON, json);
    return new UsernamePasswordAuthenticationToken(
        userToken, "N/A", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
  }

  private Map getUserInfo(String json) {
    try {
      return objectMapper.readValue(json, Map.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(
        UsernamePasswordAuthenticationToken.class);
  }

  @SuppressFBWarnings("PATH_TRAVERSAL_IN") //user file location taken from property file only!
  private void tryAuthenticate(Authentication authentication) throws AuthenticationException {
    try {
      if (!StringUtils.isEmpty(perryProperties.getUsers())) {
        String user = authentication.getName();
        String password = authentication.getCredentials().toString();
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(perryProperties.getUsers())));
        if (!properties.containsKey(user) || !properties.getProperty(user).equals(password)) {
          throw new BadCredentialsException("Authentication failed");
        }
      }
    } catch (IOException e) {
      throw new AuthenticationServiceException("Can't read users", e);
    }
  }

  private String cognitoJsonToPerryJson(String cognitoJsonString) throws AuthenticationException {
    try {
      JsonNode cognitoJson = objectMapper.readValue(cognitoJsonString, JsonNode.class);
      JsonNode payloadNode = cognitoJson.get("idToken").get("payload");

      Map userAttributes = objectMapper.convertValue(payloadNode, Map.class);

      Map<String, List> userInfo = new HashMap<>();
      userInfo.put("UserAttributes", mapToNameValueList(userAttributes));

      UniversalUserToken userToken =
          perryProperties.getIdentityProvider().getIdpMapping().map(userInfo);
      return perryProperties.getServiceProviders().get("mfa").getIdentityMapping()
          .map(userToken);
    } catch (Exception e) {
      throw new BadCredentialsException("COGNITO JSON MAPPING FAILED!", e);
    }
  }

  @SuppressWarnings("unchecked")
  private List<Map> mapToNameValueList(Map input) {
    List<Map> result = new LinkedList<>();
    input.forEach((key, value) -> {
      Map nameValue = new HashMap<>();
      nameValue.put("Name", String.valueOf(key));
      nameValue.put("Value", String.valueOf(value));
      result.add(nameValue);
    });
    return result;
  }
}