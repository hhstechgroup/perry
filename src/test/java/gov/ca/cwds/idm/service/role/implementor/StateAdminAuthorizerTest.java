package gov.ca.cwds.idm.service.role.implementor;

import static gov.ca.cwds.config.api.idm.Roles.STATE_ADMIN;
import static gov.ca.cwds.idm.util.TestHelper.admin;
import static gov.ca.cwds.idm.util.TestHelper.countyAdmin;
import static gov.ca.cwds.idm.util.TestHelper.officeAdmin;
import static gov.ca.cwds.idm.util.TestHelper.stateAdmin;
import static gov.ca.cwds.idm.util.TestHelper.superAdmin;
import static gov.ca.cwds.service.messages.MessageCode.NOT_SUPER_ADMIN_CANNOT_UPDATE_USERS_WITH_SUPER_ADMIN_ROLE;
import static gov.ca.cwds.service.messages.MessageCode.NOT_SUPER_ADMIN_CANNOT_VIEW_USERS_WITH_SUPER_ADMIN_ROLE;
import static gov.ca.cwds.service.messages.MessageCode.STATE_ADMIN_ROLES_CANNOT_BE_EDITED;
import static gov.ca.cwds.util.CurrentAuthenticatedUserUtil.getCurrentUser;
import static gov.ca.cwds.util.CurrentAuthenticatedUserUtil.getCurrentUserCountyName;
import static gov.ca.cwds.util.Utils.toSet;
import static org.powermock.api.mockito.PowerMockito.when;

import gov.ca.cwds.idm.dto.User;
import org.junit.Before;
import org.junit.Test;

public class StateAdminAuthorizerTest extends BaseAuthorizerTest {


  @Override
  protected AbstractAdminActionsAuthorizer getAuthorizer(User user) {
    return new StateAdminAuthorizer(user);
  }

  @Before
  public void before() {
    when(getCurrentUser())
        .thenReturn(admin(toSet(STATE_ADMIN),
            "Yolo", toSet("Yolo_2")));
    when(getCurrentUserCountyName()).thenReturn("Yolo");
  }

  @Test
  public void canNotStateAdminEditRoles() {
    assertCanNotEditRoles(stateAdmin(), STATE_ADMIN_ROLES_CANNOT_BE_EDITED);
  }

  @Test
  public void canEditCountyAdminRoles() {
    assertCanEditRoles(countyAdmin());
  }

  @Test
  public void canEditOfficeAdminRoles() {
    assertCanEditRoles(officeAdmin());
  }

  @Test
  public void canNotViewSuperAdmin() {
    assertCanNotViewUser(superAdmin(), NOT_SUPER_ADMIN_CANNOT_VIEW_USERS_WITH_SUPER_ADMIN_ROLE);
  }

  @Test
  public void canNotUpdateSuperAdmin() {
    assertCanNotUpdateUser(superAdmin(), NOT_SUPER_ADMIN_CANNOT_UPDATE_USERS_WITH_SUPER_ADMIN_ROLE);
  }
}