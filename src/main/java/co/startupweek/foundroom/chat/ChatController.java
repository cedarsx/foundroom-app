package co.startupweek.foundroom.chat;

import co.startupweek.foundroom.login.LoginDTO;
import co.startupweek.foundroom.user.User;
import co.startupweek.foundroom.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    UserService userService;

    @Autowired
    ChatService chatService;

    @RequestMapping(method = RequestMethod.POST, value = "/chat/join")
    @PreAuthorize(value="userService.loadUserByUsername(#credentials.getUsername()).getPassword().equals(#credentials.getPassword())")
    public StatusAndPayloadDTO join(@RequestBody LoginDTO credentials) {
        User user = userService.loadUserByUsername(credentials.getUsername());
        chatService.nextRoom(user);
        return new StatusAndPayloadDTO();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chat/next")
    @PreAuthorize(value="userService.loadUserByUsername(#credentials.getUsername()).getPassword().equals(#credentials.getPassword())")
    public StatusAndPayloadDTO next(@RequestBody LoginDTO credentials) {
        User user = userService.loadUserByUsername(credentials.getUsername());
        chatService.nextRoom(user);
        return new StatusAndPayloadDTO();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chat/say")
    @PreAuthorize(value="userService.loadUserByUsername(#submission.getUsername()).getPassword().equals(#submission.getPassword())")
    public StatusAndPayloadDTO say(@RequestBody ChatlineSubmissionDTO submission) {
        User user = userService.loadUserByUsername(submission.getUsername());
        chatService.chat(user).addLine(submission);
        return new StatusAndPayloadDTO();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chat/unread")
    @PreAuthorize(value="userService.loadUserByUsername(#credentials.getUsername()).getPassword().equals(#credentials.getPassword())")
    public StatusAndPayloadDTO unread(@RequestBody LoginDTO credentials) {
        User user = userService.loadUserByUsername(credentials.getUsername());
        return new StatusAndPayloadDTO(chatService.chat(user).getUnread(user));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chat/all")
    @PreAuthorize(value="userService.loadUserByUsername(#credentials.getUsername()).getPassword().equals(#credentials.getPassword())")
    public StatusAndPayloadDTO all(@RequestBody LoginDTO credentials) {
        User user = userService.loadUserByUsername(credentials.getUsername());
        return new StatusAndPayloadDTO(chatService.chat(user).getAll(user));
    }

}