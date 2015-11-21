import co.startupweek.foundroom.Application;
import co.startupweek.foundroom.chat.ChatController;
import co.startupweek.foundroom.chat.ChatlineSubmissionDTO;
import co.startupweek.foundroom.login.LoginController;
import co.startupweek.foundroom.login.LoginDTO;
import co.startupweek.foundroom.login.MigrateDTO;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration
public class EndpointTests {

    @Autowired
    LoginController loginController;

    @Autowired
    ChatController chatController;

    @Test
    public void runSession() {
        LoginDTO user1, user2, migrated;


        user1 = loginController.sayHello();
        user2 = loginController.sayHello();

        migrated = loginController.migrateUser(new MigrateDTO(user1.getUsername(), user1.getPassword(), "tom", "blank", "cedarsx"));

        Assert.assertEquals(migrated.getUsername(), "tom");
        Assert.assertEquals(migrated.getPassword(), "blank");

        chatController.join(migrated);
        chatController.join(user2);

        chatController.say(new ChatlineSubmissionDTO("Hello", user2.getUsername(), user2.getPassword()));
        Assert.assertEquals(chatController.all(user2).getLines().size(), 2);
        Assert.assertEquals(chatController.all(user2).getLines().get(0).getLine(), ":join");
        Assert.assertEquals(chatController.all(user2).getLines().get(0).getSpeaker(), 1);
        Assert.assertEquals(chatController.all(user2).getLines().get(1).getLine(), "Hello");
        Assert.assertEquals(chatController.all(user2).getLines().get(1).getSpeaker(), 1);

        chatController.say(new ChatlineSubmissionDTO("Hey", migrated.getUsername(), migrated.getPassword()));
        Assert.assertEquals(chatController.all(user2).getLines().size(), 3);
        Assert.assertEquals(chatController.all(user2).getLines().get(2).getLine(), "Hey");
        Assert.assertEquals(chatController.all(user2).getLines().get(2).getSpeaker(), 0);
    }

}
