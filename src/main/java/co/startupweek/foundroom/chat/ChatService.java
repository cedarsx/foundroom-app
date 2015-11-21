package co.startupweek.foundroom.chat;

import co.startupweek.foundroom.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    List<Chatroom> chatrooms;

    public ChatService() {
        chatrooms = new ArrayList<>();
    }

    public synchronized void nextRoom(User user) {
        if (user.getChatroom() > -1) {
            chatrooms.get(user.getChatroom()).leave(user);
        }
        if (chatrooms.size() > 0 && chatrooms.get(chatrooms.size() - 1).hasSpace()) {
            chatrooms.get(chatrooms.size() - 1).addToRoom(user);
        }
        else {
            chatrooms.add(new Chatroom(user, chatrooms.size()));
        }
    }

    public Chatroom chat(User user) {
        if (user.getChatroom() < 0) throw new IllegalArgumentException("User has not joined a chatroom");
        else return chatrooms.get(user.getChatroom());
    }

}
