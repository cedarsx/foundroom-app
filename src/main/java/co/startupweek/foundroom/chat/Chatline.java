package co.startupweek.foundroom.chat;

public class Chatline {

    private String line;

    private int speaker;

    private String image;

    private String sound;

    public Chatline(String line, int speaker) {
        this.line = line;
        this.speaker = speaker;
    }

    public Chatline(String line, int speaker, String image, String sound) {
        this.line = line;
        this.speaker = speaker;
        this.image = image;
        this.sound = sound;
    }

    public String getLine() {
        return line;
    }

    public int getSpeaker() {
        return speaker;
    }

    public String getImage() {
        return image;
    }

    public String getSound() {
        return sound;
    }
}
