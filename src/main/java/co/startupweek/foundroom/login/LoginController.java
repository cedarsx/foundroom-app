package co.startupweek.foundroom.login;

import co.startupweek.foundroom.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pazollim on 21/11/2015.
 */
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/login/hello")
    public LoginDTO sayHello() {
        return new LoginDTO(userService.generateAnonymous());
    }


}
