package gov.ca.cwds.service.messages;

public enum MessageCode {
  ERROR_CONNECT_TO_IDM("CAP-001"),
  USER_WITH_EMAIL_EXISTS_IN_IDM("CAP-002"),
  NO_USER_WITH_RACFID_IN_CWSCMS("CAP-003"),
  NOT_AUTHORIZED_TO_ADD_USER_FOR_OTHER_COUNTY("CAP-004"),
  IDM_USER_VALIDATION_FAILED("CAP-005"),
  IDM_MAPPING_SCRIPT_ERROR("CAP-006"),
  DUPLICATE_USERID_FOR_RACFID_IN_CWSCMS("CAP-007"),
  USER_NOT_FOUND_BY_ID_IN_IDM("CAP-008"),
  ERROR_GET_USER_FROM_IDM("CAP-009"),
  ERROR_UPDATE_USER_IN_IDM("CAP-010"),
  UNABLE_CREATE_NEW_IDM_USER("CAP-011"),
  UNABLE_LOG_IDM_USER_CREATE("CAP-012"),
  UNABLE_LOG_IDM_USER_UPDATE("CAP-013"),
  UNABLE_LOG_IDM_USER("CAP-014"),
  UNABLE_CREATE_IDM_USER_IN_ES("CAP-015"),
  UNABLE_UPDATE_IDM_USER_IN_ES("CAP-016"),
  INVALID_DATE_FORMAT("CAP-017"),
  USER_CREATE_SAVE_TO_SEARCH_ERROR("CAP-018"),
  USER_CREATE_SAVE_TO_SEARCH_AND_DB_LOG_ERRORS("CAP-019"),
  USER_UPDATE_SAVE_TO_SEARCH_ERROR("CAP-020"),
  USER_UPDATE_SAVE_TO_SEARCH_AND_DB_LOG_ERRORS("CAP-021"),
  USER_PARTIAL_UPDATE("CAP-022"),
  USER_PARTIAL_UPDATE_AND_SAVE_TO_SEARCH_ERRORS("CAP-023"),
  USER_PARTIAL_UPDATE_AND_SAVE_TO_SEARCH_AND_DB_LOG_ERRORS("CAP-024"),
  ERROR_UPDATE_USER_ENABLED_STATUS("CAP-025"),
  USER_NOTHING_UPDATED("CAP-026"),
  UNABLE_TO_PURGE_PROCESSED_USER_LOGS("CAP-027"),
  ACTIVE_USER_WITH_RAFCID_EXISTS_IN_IDM("CAP-028"),
  NOT_AUTHORIZED_TO_ADD_USER_FOR_OTHER_OFFICE("CAP-029"),
  NOT_AUTHORIZED_TO_GET_MANAGED_OFFICES_LIST("CAP-030"),
  UNABLE_UPDATE_UNALLOWED_ROLES("CAP-031"),
  UNABLE_TO_REMOVE_ALL_ROLES("CAP-032"),
  NOT_AUTHORIZED_TO_CREATE_USER("CAP-033"),
  UNABLE_TO_WRITE_LAST_LOGIN_TIME("CAP-034"),
  FIRST_NAME_IS_NOT_PROVIDED("CAP-035"),
  LAST_NAME_IS_NOT_PROVIDED("CAP-036"),
  COUNTY_NAME_IS_NOT_PROVIDED("CAP-037"),
  UNABLE_TO_WRITE_LAST_REGISTRATION_RESUBMIT_TIME("CAP-038"),
  IDM_GENERIC_ERROR("CAP-039"),
  IDM_ERROR_AT_RESEND_INVITATION_EMAIL("CAP-040"),
  CALS_ADMIN_CANNOT_VIEW_NON_CALS_USER("CAP-041"),
  COUNTY_ADMIN_CANNOT_VIEW_USER_FROM_OTHER_COUNTY("CAP-042"),
  OFFICE_ADMIN_CANNOT_VIEW_USER_FROM_OTHER_COUNTY("CAP-043"),
  ADMIN_CANNOT_UPDATE_HIMSELF("CAP-044"),
  COUNTY_ADMIN_CANNOT_UPDATE_USER_FROM_OTHER_COUNTY("CAP-045"),
  COUNTY_ADMIN_CANNOT_UPDATE_STATE_ADMIN("CAP-046"),
  ROLE_IS_UNSUFFICIENT_FOR_OPERATION("CAP-047"),
  OFFICE_ADMIN_CANNOT_UPDATE_USER_FROM_OTHER_OFFICE("CAP-048"),
  OFFICE_ADMIN_CANNOT_UPDATE_ADMIN("CAP-049"),
  COUNTY_ADMIN_CANNOT_RESEND_INVITATION_FOR_USER_FROM_OTHER_COUNTY("CAP-050"),
  OFFICE_ADMIN_CANNOT_RESEND_INVITATION_FOR_USER_FROM_OTHER_OFFICE("CAP-051"),
  CANNOT_EDIT_ROLES_OF_CALS_EXTERNAL_WORKER("CAP-052"),
  CALS_ADMIN_ROLES_CANNOT_BE_EDITED("CAP-053"),
  STATE_ADMIN_ROLES_CANNOT_BE_EDITED("CAP-054"),
  COUNTY_ADMIN_CANNOT_EDIT_ROLES_OF_OTHER_COUNTY_ADMIN("CAP-055"),
  UNABLE_TO_ASSIGN_CANS_PERMISSION_TO_NON_RACFID_USER("CAP-056"),
  OFFICE_ADMIN_CANNOT_VIEW_USERS_WITH_CALS_EXTERNAL_WORKER_ROLE("CAP-057"),
  OFFICE_ADMIN_CANNOT_VIEW_USERS_WITH_CALS_ADMIN_ROLE("CAP-058"),
  UNABLE_TO_CREATE_NON_RACFID_USER_WITH_CANS_PERMISSION("CAP-059"),
  UNABLE_TO_CREATE_USER_WITHOUT_ROLES("CAP-060"),
  UNABLE_TO_CREATE_USER_WITH_UNALLOWED_ROLES("CAP-061"),
  NOT_SUPER_ADMIN_CANNOT_VIEW_USERS_WITH_SUPER_ADMIN_ROLE("CAP-062"),
  NOT_SUPER_ADMIN_CANNOT_UPDATE_USERS_WITH_SUPER_ADMIN_ROLE("CAP-063"),
  UNABLE_TO_SEND_EMAIL_NOTIFICATION_ON_EMAIL_CHANGE("CAP-064"),
  INVALID_PHONE_FORMAT("CAP-065");

  private String value;

  MessageCode(String value){
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
