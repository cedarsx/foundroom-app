package co.startupweek.foundroom.login;

import co.startupweek.foundroom.user.User;

import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public LoginDTO() {}

    public LoginDTO(User user) {
        username = user.getUsername();
        password = user.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
