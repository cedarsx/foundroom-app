package co.startupweek.foundroom.login;

import javax.validation.constraints.NotNull;

public class MigrateDTO {

    @NotNull
    private String oldUsername;

    @NotNull
    private String oldPassword;

    @NotNull
    private String newUsername;

    @NotNull
    private String newPassword;

    private String newTwitter;

    public MigrateDTO() {}

    public MigrateDTO(String oldUsername, String oldPassword, String newUsername, String newPassword, String newTwitter) {
        this.oldUsername = oldUsername;
        this.oldPassword = oldPassword;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.newTwitter = newTwitter;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getTwitter() {
        return newTwitter;
    }
}
