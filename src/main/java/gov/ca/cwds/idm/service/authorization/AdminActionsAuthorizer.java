package gov.ca.cwds.idm.service.authorization;

public interface AdminActionsAuthorizer {

  void checkCanViewUser();

  boolean canCreateUser();

  void checkCanUpdateUser();

  boolean canResendInvitationMessage();

  boolean canEditRoles();

}
