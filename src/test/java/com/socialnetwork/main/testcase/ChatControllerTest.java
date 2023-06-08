package com.socialnetwork.main.testcase;

import com.socialnetwork.main.controller.ChatController;

//public class ChatControllerTest {
//
//}


import com.socialnetwork.main.model.ApprovedRegistration;
import com.socialnetwork.main.model.Message;
import com.socialnetwork.main.repository.ApprovedRegistrationRepository;
import com.socialnetwork.main.repository.MessageRepository;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChatControllerTest {

    @Mock
    private ApprovedRegistrationRepository approvedRegistrationRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ChatController chatController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void getMessagesByRecipientId_withValidRecipientIdAndLoggedInUser_shouldReturnMessages() {
//        // Arrange
//        Long recipientId = 1L;
//        ApprovedRegistration loggedInUser = new ApprovedRegistration();
//        loggedInUser.setId(2L);
//        List<Message> messages = new ArrayList<>();
//        messages.add(new Message());
//        messages.add(new Message());
//
//        when(session.getAttribute("loggedInUser")).thenReturn(loggedInUser);
//        when(approvedRegistrationRepository.findById(recipientId)).thenReturn(Optional.of(new ApprovedRegistration()));
//        when(messageRepository.findByRecipientId(recipientId)).thenReturn(messages);
//
//        // Act
//        ResponseEntity<?> response = chatController.getMessagesByRecipientId(recipientId, session);
//
//        // Assert
//        assertEquals(ResponseEntity.ok(messages), response);
//        verify(session, times(1)).getAttribute("loggedInUser");
//        verify(approvedRegistrationRepository, times(1)).findById(recipientId);
//        verify(messageRepository, times(1)).findByRecipientId(recipientId);
//    }

    @Test
    public void getMessagesByRecipientId_withInvalidRecipientId_shouldReturnBadRequest() {
        // Arrange
        Long recipientId = 1L;
        ApprovedRegistration loggedInUser = new ApprovedRegistration();
        loggedInUser.setId(2L);

        when(session.getAttribute("loggedInUser")).thenReturn(loggedInUser);
        when(approvedRegistrationRepository.findById(recipientId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = chatController.getMessagesByRecipientId(recipientId, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Recipient with ID " + recipientId + " does not exist or is not an approved user."), response);
        verify(session, times(1)).getAttribute("loggedInUser");
        verify(approvedRegistrationRepository, times(1)).findById(recipientId);
        verify(messageRepository, never()).findByRecipientId(anyLong());
    }

    @Test
    public void getMessagesByRecipientId_withNullLoggedInUser_shouldReturnBadRequest() {
        // Arrange
        Long recipientId = 1L;

        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<?> response = chatController.getMessagesByRecipientId(recipientId, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("You need to login first."), response);
        verify(session, times(1)).getAttribute("loggedInUser");
        verify(approvedRegistrationRepository, never()).findById(anyLong());
        verify(messageRepository, never()).findByRecipientId(anyLong());
    }

    @Test
    public void sendMessageToMultiple_withValidRecipientIdsAndLoggedInUser_shouldSendMessage() {
        // Arrange
        ApprovedRegistration loggedInUser = new ApprovedRegistration();
        loggedInUser.setId(1L);
        List<Long> recipientIds = new ArrayList<>();
        recipientIds.add(2L);
        recipientIds.add(3L);

        ChatController.MessageRequest messageRequest = new ChatController.MessageRequest();
        messageRequest.setRecipientIds(recipientIds);
        messageRequest.setContent("Hello!");

        List<ApprovedRegistration> recipients = new ArrayList<>();
        recipients.add(new ApprovedRegistration());
        recipients.add(new ApprovedRegistration());

        StringBuilder recipientsNames = new StringBuilder();
        for (ApprovedRegistration recipient : recipients) {
            recipientsNames.append(recipient.getFirstName()).append(", ");
        }
        recipientsNames.deleteCharAt(recipientsNames.length() - 2); // remove last comma and space

        when(session.getAttribute("loggedInUser")).thenReturn(loggedInUser);
        when(approvedRegistrationRepository.findById(2L)).thenReturn(Optional.of(new ApprovedRegistration()));
        when(approvedRegistrationRepository.findById(3L)).thenReturn(Optional.of(new ApprovedRegistration()));

        // Act
        ResponseEntity<?> response = chatController.sendMessageToMultiple(messageRequest, session);

        // Assert
        assertEquals(ResponseEntity.ok("Message sent to users " + recipientsNames + " successfully!"), response);
        verify(session, times(1)).getAttribute("loggedInUser");
        verify(approvedRegistrationRepository, times(1)).findById(2L);
        verify(approvedRegistrationRepository, times(1)).findById(3L);
        verify(messageRepository, times(2)).save(any(Message.class));
    }

//    @Test
//    public void sendMessageToMultiple_withInvalidRecipientId_shouldReturnBadRequest() {
//        // Arrange
//        ApprovedRegistration loggedInUser = new ApprovedRegistration();
//        loggedInUser.setId(1L);
//        List<Long> recipientIds = new ArrayList<>();
//        recipientIds.add(2L);
//        recipientIds.add(3L);
//
//        ChatController.MessageRequest messageRequest = new ChatController.MessageRequest();
//        messageRequest.setRecipientIds(recipientIds);
//        messageRequest.setContent("Hello!");
//
//        when(session.getAttribute("loggedInUser")).thenReturn(loggedInUser);
//        when(approvedRegistrationRepository.findById(2L)).thenReturn(Optional.of(new ApprovedRegistration()));
//        when(approvedRegistrationRepository.findById(3L)).thenReturn(Optional.empty());
//
//        // Act
//        ResponseEntity<?> response = chatController.sendMessageToMultiple(messageRequest, session);
//
//        // Assert
//        assertEquals(ResponseEntity.badRequest().body("Recipient with ID 3 does not exist or is not an approved user."), response);
//        verify(session, times(1)).getAttribute("loggedInUser");
//        verify(approvedRegistrationRepository, times(1)).findById(2L);
//        verify(approvedRegistrationRepository, times(1)).findById(3L);
//        verify(messageRepository, never()).save(any(Message.class));
//    }

    @Test
    public void sendMessageToMultiple_withNullLoggedInUser_shouldReturnBadRequest() {
        // Arrange
        List<Long> recipientIds = new ArrayList<>();
        recipientIds.add(2L);
        recipientIds.add(3L);

        ChatController.MessageRequest messageRequest = new ChatController.MessageRequest();
        messageRequest.setRecipientIds(recipientIds);
        messageRequest.setContent("Hello!");

        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        ResponseEntity<?> response = chatController.sendMessageToMultiple(messageRequest, session);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("You need to login first."), response);
        verify(session, times(1)).getAttribute("loggedInUser");
        verify(approvedRegistrationRepository, never()).findById(anyLong());
        verify(messageRepository, never()).save(any(Message.class));
    }
}
