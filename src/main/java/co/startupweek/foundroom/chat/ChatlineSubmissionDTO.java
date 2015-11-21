package co.startupweek.foundroom.chat;

public class ChatlineSubmissionDTO {

    private String line;

    private String username;

    private String password;

    public ChatlineSubmissionDTO() { }

    public ChatlineSubmissionDTO(String line, String username, String password) {
        this.line = line;
        this.username = username;
        this.password = password;
    }

    public String getLine() {
        return line;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
