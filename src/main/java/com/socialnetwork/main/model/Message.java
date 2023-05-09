package com.socialnetwork.main.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private ApprovedRegistration sender;
    
    @ManyToOne
    private ApprovedRegistration recipient;
    
    private String content;
    
    private LocalDateTime sentAt;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApprovedRegistration getSender() {
        return sender;
    }

    public void setSender(ApprovedRegistration sender) {
        this.sender = sender;
    }

    public ApprovedRegistration getRecipient() {
        return recipient;
    }

    public void setRecipient(ApprovedRegistration recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}


