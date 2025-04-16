package com.asthana.chat_group.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.asthana.chat_group.Entity.MessageEntity;

public interface ChatRepository extends MongoRepository<MessageEntity , String>{
    @Query("{'sender' : ?0}")
    List<MessageEntity> findBySender(String sender , Pageable pageable);
}
