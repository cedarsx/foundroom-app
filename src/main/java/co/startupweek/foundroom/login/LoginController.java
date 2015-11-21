package co.startupweek.foundroom.login;

import co.startupweek.foundroom.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/login/hello")
    public LoginDTO sayHello() {
        return new LoginDTO(userService.generateAnonymous());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/login/migrate")
    public LoginDTO migrateUser(@RequestBody MigrateDTO migration) {
        return new LoginDTO(userService.migrateUser(migration));
    }

}
