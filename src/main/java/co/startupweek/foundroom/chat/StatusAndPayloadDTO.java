package co.startupweek.foundroom.chat;

import java.util.List;

public class StatusAndPayloadDTO {

    private Status status;

    private List<Chatline> lines;

    public StatusAndPayloadDTO() { status = Status.OK; }

    public StatusAndPayloadDTO(List<Chatline> lines) { status = Status.OK; this.lines = lines; }

    public Status getStatus() {
        return status;
    }

    public List<Chatline> getLines() {
        return lines;
    }
}
