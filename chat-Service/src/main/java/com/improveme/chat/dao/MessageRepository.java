package com.improveme.chat.dao;

import com.improveme.chat.entities.Message;
import com.improveme.chat.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findTopByRoomOrderByIdDesc(Room room);
    List<Message> findAllByRoomOrderByIdAsc(Room room);
}
