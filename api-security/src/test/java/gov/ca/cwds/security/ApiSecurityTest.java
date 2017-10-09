package gov.ca.cwds.security;

import com.google.inject.Inject;
import gov.ca.cwds.testapp.domain.Case;
import gov.ca.cwds.testapp.domain.CaseDTO;
import gov.ca.cwds.testapp.service.TestService;
import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dmitry.rudenko on 10/6/2017.
 */
public class ApiSecurityTest extends AbstractApiSecurityTest {

  @Inject
  TestService testService;


  @Before
  public void before() throws Exception {
    initInjector();
  }

  @Test(expected = UnauthorizedException.class)
  public void testUnauthorized() throws Exception {
    testService.testArg("2");
  }

  @Test
  public void testAuthorized() throws Exception {
    testService.testArg("1");
  }

  @Test
  public void testArgAuthorizedCompositeObject() throws Exception {
    CaseDTO caseDTO = new CaseDTO();
    Case caseObject = new Case(1L, "name");
    caseDTO.setCaseObject(caseObject);
    testService.testCompositeObject(caseDTO);
  }

  @Test(expected = UnauthorizedException.class)
  public void testArgUnauthorizedCompositeObject() throws Exception {
    CaseDTO caseDTO = new CaseDTO();
    Case caseObject = new Case(2L, "name");
    caseDTO.setCaseObject(caseObject);
    testService.testCompositeObject(caseDTO);
  }
}
