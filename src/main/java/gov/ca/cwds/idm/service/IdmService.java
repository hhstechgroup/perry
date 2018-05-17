package gov.ca.cwds.idm.service;

import gov.ca.cwds.idm.dto.User;

import java.util.List;

public interface IdmService {
  List<User> getUsers(String lastName);

  User findUser(String id);
}
