package gov.ca.cwds.service.messages;

import static gov.ca.cwds.service.messages.MessageCodes.IDM_MAPPING_SCRIPT_ERROR;
import static gov.ca.cwds.service.messages.MessageCodes.NO_USER_WITH_RACFID;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"dev"})
public class MessagesServiceTest {

  @Autowired
  private MessagesService messagesService;

  @Test
  public void testGet(){
    assertThat(messagesService.get(IDM_MAPPING_SCRIPT_ERROR), is("Error running the IdmMappingScript"));
    assertThat(messagesService.get(NO_USER_WITH_RACFID, "ABCDEF"), is("No user with RACFID: ABCDEF found in CWSCMS"));
  }
}
