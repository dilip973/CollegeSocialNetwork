package com.socialnetwork.main.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Message;
import com.socialnetwork.main.repository.ApprovedRegistrationRepository;
import com.socialnetwork.main.repository.MessageRepository;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private ApprovedRegistrationRepository approvedRegistrationRepository;
    
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/messages")
    public ResponseEntity<?> getMessagesByRecipientId(Long recipientId, HttpSession session) {
        ApprovedRegistration loggedInUser = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return ResponseEntity.badRequest().body("You need to login first.");
        }

        Optional<ApprovedRegistration> recipientOptional = approvedRegistrationRepository.findById(recipientId);
        if (!recipientOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Recipient with ID " + recipientId + " does not exist or is not an approved user.");
        }
        ApprovedRegistration recipient = recipientOptional.get();

        List<Message> messages = messageRepository.findByRecipientId(recipient.getId());

        return ResponseEntity.ok(messages);
    }
    @PostMapping("/send")
    public ResponseEntity<?> sendMessageToMultiple(@RequestBody MessageRequest messageRequest, HttpSession session) {
        ApprovedRegistration sender = (ApprovedRegistration) session.getAttribute("loggedInUser");
        if (sender == null) {
            return ResponseEntity.badRequest().body("You need to login first.");
        }

        List<Long> recipientIds = messageRequest.getRecipientIds();
        List<ApprovedRegistration> recipients = new ArrayList<>();

        for (Long recipientId : recipientIds) {
            Optional<ApprovedRegistration> recipientOptional = approvedRegistrationRepository.findById(recipientId);
            if (!recipientOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Recipient with ID " + recipientId + " does not exist or is not an approved user.");
            }
            ApprovedRegistration recipient = recipientOptional.get();
            recipients.add(recipient);

            Message message = new Message();
            message.setSender(sender);
            message.setRecipient(recipient);
            message.setContent(messageRequest.getContent());
            message.setSentAt(LocalDateTime.now());

            messageRepository.save(message);
        }

        StringBuilder recipientsNames = new StringBuilder();
        for (ApprovedRegistration recipient : recipients) {
            recipientsNames.append(recipient.getFirstName()).append(", ");
        }
        recipientsNames.deleteCharAt(recipientsNames.length() - 2); // remove last comma and space

        return ResponseEntity.ok("Message sent to users " + recipientsNames + " successfully!");
    }

    public static class MessageRequest {
        private Long recipientId;
        private String content;
        private List<Long> recipientIds;

        public List<Long> getRecipientIds() {
            return recipientIds;
        }

        public void setRecipientIds(List<Long> recipientIds) {
            this.recipientIds = recipientIds;
        }

        public Long getRecipientId() {
            return recipientId;
        }

        public void setRecipientId(Long recipientId) {
            this.recipientId = recipientId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}


