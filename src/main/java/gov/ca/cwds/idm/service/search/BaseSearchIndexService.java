package gov.ca.cwds.idm.service.search;

import static gov.ca.cwds.idm.persistence.ns.OperationType.CREATE;
import static gov.ca.cwds.idm.persistence.ns.OperationType.UPDATE;

import gov.ca.cwds.idm.persistence.ns.OperationType;
import gov.ca.cwds.idm.service.cognito.SearchProperties;
import gov.ca.cwds.idm.service.cognito.SearchProperties.SearchIndexProperties;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public abstract class BaseSearchIndexService {

  private static final String DORA_URL = "doraUrl";
  private static final String ES_INDEX_NAME = "esIndexName";
  private static final String ES_INDEX_TYPE = "esIndexType";
  private static final String ID = "id";

  private static final String URL_TEMPLATE_ROOT =
      "{"
          + DORA_URL
          + "}/dora/{"
          + ES_INDEX_NAME
          + "}/{"
          + ES_INDEX_TYPE
          + "}/{"
          + ID
          + "}";

  private static final String CREATE_URL_TEMPLATE = URL_TEMPLATE_ROOT + "/_create";

  private static final String UPDATE_URL_TEMPLATE = URL_TEMPLATE_ROOT;

  private static final Map<OperationType, String> URL_TEMPLATE_MAP =
      new EnumMap<>(OperationType.class);

  static {
    URL_TEMPLATE_MAP.put(CREATE, CREATE_URL_TEMPLATE);
    URL_TEMPLATE_MAP.put(UPDATE, UPDATE_URL_TEMPLATE);
  }

  @Autowired protected SearchProperties searchProperties;

  @Autowired private IndexRestSender restSender;

  final <T> ResponseEntity<String> sendToIndex(T body, String id, OperationType operation,
      SearchIndexProperties indexProps) {
    String urlTemplate = getUrlTemplate(operation);
    HttpEntity<T> requestEntity = createRequestEntity(body);
    Map<String, String> params = createParams(id, indexProps);
    return restSender.send(urlTemplate, requestEntity, params);
  }

  private Map<String, String> createParams(String indexId, SearchIndexProperties indexProps) {
    Map<String, String> params = new HashMap<>();
    params.put(DORA_URL, searchProperties.getDoraUrl());
    params.put(ES_INDEX_NAME, indexProps.getName());
    params.put(ES_INDEX_TYPE, indexProps.getType());
    params.put(ID, indexId);
    return params;
  }

  private <T> HttpEntity<T> createRequestEntity(T body) {
    return new HttpEntity<>(body, createHeaders());
  }

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

  static String getUrlTemplate(OperationType operation) {
    if (operation == null) {
      throw new IllegalArgumentException("Operation type is null");
    }

    String template = URL_TEMPLATE_MAP.get(operation);

    if (template == null) {
      throw new IllegalArgumentException(
          "Provided unsupported OperationType: " + operation.toString());
    }
    return template;
  }

  public void setSearchProperties(SearchProperties searchProperties) {
    this.searchProperties = searchProperties;
  }

  public void setRestSender(IndexRestSender restSender) {
    this.restSender = restSender;
  }
}
