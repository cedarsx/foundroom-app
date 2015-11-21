package co.startupweek.foundroom.chat;

import co.startupweek.foundroom.user.User;

import java.util.ArrayList;
import java.util.List;

public class Chatroom {

    private List<Chatline> lines;

    // private ChatroomTurn turn = ChatroomTurn.PARTICIPANT_1;

    private int[] unread = new int[2];

    private User[] users = new User[2];

    private int index;

    // private boolean[] continuing = new boolean[2];

    public Chatroom(User user, int index) {
        lines = new ArrayList<>();
        this.index = index;
        user.setChatroom(index);
        users[0] = user;
    }

    public void addToRoom(User user) {
        if (users[1] != null) throw new IllegalArgumentException("Chatroom is already full");
        user.setChatroom(index);
        users[1] = user;
        lines.add(new Chatline(":join", 1));
        unread[0] += 1;
    }

    public boolean hasSpace() {
        return (users[1] == null);
    }

    public void leave(User user) {
        for (int i = 0; i < 2; i++) {
            if (user.getUsername().equals(users[i].getUsername())) {
                lines.add(new Chatline(":leave", i));
                if (i == 0) unread[1] += 1;
                else unread[0] += 1;
                user.setChatroom(-1);
            }
        }

        throw new IllegalArgumentException("User not part of chatroom");
    }

    public void addLine(ChatlineSubmissionDTO submitted) {
        for (int i = 0; i < 2; i++) {
            if (submitted.getUsername().equals(users[i].getUsername())) {
                lines.add(new Chatline(submitted.getLine(), i));
                if (i == 0) unread[1] += 1;
                else unread[0] += 1;
                return;
            }
        }

        throw new IllegalArgumentException("User not part of chatroom");
    }

    public List<Chatline> getUnread(User user) {
        List<Chatline> result = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            if (user.getUsername().equals(users[i].getUsername())) {
                if (unread[i] == 0) return result;
                for (int j = lines.size() - 1; j >= 0; j--) {
                    if (unread[i] > 0 && lines.get(j).getSpeaker() != i) {
                        unread[i] -= 1;
                        result.add(0, lines.get(j));
                    }
                }

                return result;
            }
        }

        throw new IllegalArgumentException("User not part of chatroom");
    }

    public List<Chatline> getAll(User user) {

        for (int i = 0; i < 2; i++) {
            if (user.getUsername().equals(users[i].getUsername())) {
                return lines;
            }
        }

        throw new IllegalArgumentException("User not part of chatroom");
    }
}
