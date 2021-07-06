package com.improveme.chat.service;

import com.improveme.chat.dao.MessageRepository;
import com.improveme.chat.dao.RoomRepository;
import com.improveme.chat.entities.Message;
import com.improveme.chat.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    public Room getByGroupeId(long groupeId)
    {
        return roomRepository.findFirstByGroupeId(groupeId);
    }

    public Room Save(Room room)
    {
        return roomRepository.save(room);
    }

    public Message SendMessage(long roomId , Message message)
    {
        Room room = roomRepository.findById(roomId).get();
        message.setRoom(room);
        messagingTemplate.convertAndSend("/chat-room/"+roomId, message);
        return messageRepository.save(message);
    }

    public List<Message> getMessages(long roomId)
    {
        Room room = roomRepository.findById(roomId).get();
        return messageRepository.findAllByRoomOrderByIdAsc(room);
    }

    public Message LastMessage(long roomId) {
        Room room = roomRepository.findById(roomId).get();
        return messageRepository.findTopByRoomOrderByIdDesc(room);
    }

    public Boolean removeRoomByGroupId(long groupeId)
    {
        try {
            Room room = roomRepository.findFirstByGroupeId(groupeId);
            roomRepository.delete(room);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
