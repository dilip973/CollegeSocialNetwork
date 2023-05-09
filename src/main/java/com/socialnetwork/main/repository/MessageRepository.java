package com.socialnetwork.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialnetwork.main.model.Message;



@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    List<Message> findByRecipientIdOrderBySentAtDesc(Long recipientId);
    List<Message> findByRecipientId(Long recipientId);
    
}