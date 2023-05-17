package com.socialnetwork.main.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialnetwork.main.model.Chat;
import com.socialnetwork.main.model.User;
import com.socialnetwork.main.repository.ChatRepository;
import com.socialnetwork.main.repository.UserRepository;

@Service
public class ChatService {

@Autowired
private UserRepository userRepository;

@Autowired
private ChatRepository chatRepository;

public void sendMessage(User sender, Long receiverId, String message) {
    User receiver = userRepository.findById(receiverId).orElse(null);
    if (receiver != null) {
        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setReceiver(receiver);
        chat.setMessage(message);
        chatRepository.save(chat);
    }
}

public List<Chat> getChats(User user, Long otherUserId) {
    User otherUser = userRepository.findById(otherUserId).orElse(null);
    if (otherUser != null) {
        return chatRepository.findBySenderAndReceiverOrReceiverAndSenderOrderByTimestampAsc(
                user, otherUser, otherUser, user);
    }
    return Collections.emptyList();
}

}

