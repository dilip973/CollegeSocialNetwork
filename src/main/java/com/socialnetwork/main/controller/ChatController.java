package com.socialnetwork.main.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.main.chat.dto.ChatDto;
import com.socialnetwork.main.model.Chat;
import com.socialnetwork.main.model.User;
import com.socialnetwork.main.repository.ChatRepository;
import com.socialnetwork.main.repository.UserRepository;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/chat")
public class ChatController{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @PostMapping("/send")
    public ResponseEntity<Object> sendMessage(@RequestBody ChatDto chatDto, HttpSession session) {
        User currentUser = (User) session.getAttribute("login");
        if (currentUser == null) {
            Chat c = new Chat();
            c.setMessage("User not logged in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(c);
        }
        User receiver = userRepository.findById(chatDto.getReceiverId()).orElse(null);
        if (receiver == null) {
            Chat c = new Chat();
            c.setMessage("Invalid receiver ID");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(c);
        }
        Chat chat = new Chat();
        chat.setSender(currentUser);
        chat.setReceiver(receiver);
        chat.setMessage(chatDto.getContent());
        chat.setTimestamp(LocalDateTime.now());
        chatRepository.save(chat);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/messages/{otherUserId}")
    public ResponseEntity<Object> getMessages(@PathVariable Long otherUserId, HttpSession session) {
        User currentUser = (User) session.getAttribute("login");
        if (currentUser == null) {
            Chat c = new Chat();
            c.setMessage("User not logged in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(c);
        }
        User otherUser = userRepository.findById(otherUserId).orElse(null);
        if (otherUser == null) {
            Chat c = new Chat();
            c.setMessage("Invalid user ID");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(c);
        }
        List<Chat> chats = chatRepository.findBySenderAndReceiverOrReceiverAndSenderOrderByTimestampAsc(
                currentUser, otherUser, otherUser, currentUser);
        return ResponseEntity.ok(chats);
    }
    
    @GetMapping("/history")
    public ResponseEntity<Object> getChatHistory(HttpSession session) {
        User currentUser = (User) session.getAttribute("login");
        if (currentUser == null) {
            Chat c = new Chat();
            c.setMessage("User not logged in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(c);
        }
        List<Chat> chats = chatRepository.findBySenderOrReceiverOrderByTimestampAsc(currentUser, currentUser);
        return ResponseEntity.ok(chats);
    }
}


