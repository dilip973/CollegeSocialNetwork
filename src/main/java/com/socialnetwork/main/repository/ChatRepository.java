package com.socialnetwork.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialnetwork.main.model.Chat;
import com.socialnetwork.main.model.User;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findBySenderAndReceiverOrReceiverAndSenderOrderByTimestampAsc(User sender1, User receiver1, User sender2, User receiver2);

    List<Chat> findBySenderOrReceiverOrderByTimestampAsc(User sender, User receiver);
}