package gov.ca.cwds.idm.service.authorization;

import java.util.List;

public interface AdminActionsAuthorizer {

  void checkCanViewUser();

  void checkCanCreateUser();

  void checkCanUpdateUser();

  void checkCanResendInvitationMessage();

  List<String> getPossibleUserRolesAtCreate();

  List<String> getPossibleUserRolesAtUpdate();

  void checkCanEditRoles();
}
