package gov.ca.cwds.idm.service.role.implementor;

import static gov.ca.cwds.idm.service.authorization.UserRolesService.isAdmin;
import static gov.ca.cwds.idm.service.role.implementor.AuthorizationUtils.isPrincipalInTheSameCountyWith;
import static gov.ca.cwds.service.messages.MessageCode.OFFICE_ADMIN_CANNOT_VIEW_USER_FROM_OTHER_COUNTY;
import static gov.ca.cwds.util.CurrentAuthenticatedUserUtil.getCurrentUserOfficeIds;

import gov.ca.cwds.idm.dto.User;
import java.util.Set;

class OfficeAdminAuthorizer extends AbstractAdminActionsAuthorizer {

  OfficeAdminAuthorizer(User user) {
    super(user);
  }

  @Override
  public void canViewUser() {
    if(!isPrincipalInTheSameCountyWith(getUser())) {
      throwAuthorizationException(OFFICE_ADMIN_CANNOT_VIEW_USER_FROM_OTHER_COUNTY, getUser().getId());
    }
  }

  @Override
  public boolean canCreateUser() {
    return isAdminInTheSameOfficeAsUser();
  }

  @Override
  public boolean canUpdateUser() {
    return isAdminInTheSameOfficeAsUser() && !isAdmin(getUser());
  }

  @Override
  public boolean canResendInvitationMessage() {
    return isAdminInTheSameOfficeAsUser();
  }

  private boolean isAdminInTheSameOfficeAsUser() {
    String userOfficeId = getUser().getOfficeId();
    Set<String> adminOfficeIds = getCurrentUserOfficeIds();
    return userOfficeId != null && adminOfficeIds != null && adminOfficeIds.contains(userOfficeId);
  }
}
