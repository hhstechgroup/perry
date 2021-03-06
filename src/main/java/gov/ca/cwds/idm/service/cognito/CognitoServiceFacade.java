package gov.ca.cwds.idm.service.cognito;

import com.amazonaws.services.cognitoidp.model.UserType;
import gov.ca.cwds.idm.dto.User;
import gov.ca.cwds.idm.service.UserUpdateRequest;
import gov.ca.cwds.idm.service.cognito.dto.CognitoUserPage;
import gov.ca.cwds.idm.service.cognito.dto.CognitoUsersSearchCriteria;
import java.util.List;
import org.springframework.context.annotation.Profile;

/**
 * Created by Alexander Serbin on 9/13/2018
 */
@Profile("idm")
public interface CognitoServiceFacade {

  UserType createUser(User user);

  CognitoUserPage searchPage(CognitoUsersSearchCriteria searchCriteria);

  List<UserType> searchAllPages(CognitoUsersSearchCriteria searchCriteria);

  UserType getCognitoUserById(String id);

  boolean isActiveRacfIdPresentInCognito(String racfId);

  boolean doesUserWithEmailExistInCognito(String email);

  void healthCheck();

  /**
   * @return true if Cognito operations were really executed, false otherwise
   */
  boolean updateUserAttributes(UserUpdateRequest userUpdateRequest);

  void changeUserEnabledStatus(String userId, Boolean newValue);

  void unlockUser(String userId);

  /**
   * Send the invitation message to the user that was just created.
   *
   * @param email email address of the user.
   */
  UserType sendInvitationMessageByEmail(String email);

  /**
   * Resend the invitation message to a user that already exists and reset the expiration limit on
   * the user's account by admin.
   *
   * @param userId Cognito username of the user.
   */
  UserType resendInvitationMessage(String userId);

  void deleteCognitoUserById(String id);
}
