package com.improveme.chat.controller;
import com.improveme.chat.entities.Message;
import com.improveme.chat.entities.Room;
import com.improveme.chat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("Rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/")
    public Room save(@RequestBody Room room)
    {
        return roomService.Save(room);
    }

    @GetMapping("/{groupeId}")
    public Room getByGroupeId(@PathVariable("groupeId") long groupeId)
    {
        return roomService.getByGroupeId(groupeId);
    }

    @PostMapping("/Messages/{roomId}")
    public Message sendMessage(@PathVariable("roomId") long roomId, @RequestBody Message message)
    {
        return roomService.SendMessage(roomId,message);
    }

    @GetMapping("/messages/{roomId}")
    public List<Message> getMessages(@PathVariable("roomId") long roomId)
    {
        return roomService.getMessages(roomId);
    }

    @GetMapping("/messages/Last/{roomId}")
    public Message LastMessages(@PathVariable("roomId") long roomId)
    {
        return roomService.LastMessage(roomId);
    }

    @DeleteMapping("/{groupeId}")
    public Boolean removeRoomByGroupId(@PathVariable("groupeId") long groupeId)
    {
        return roomService.removeRoomByGroupId(groupeId);
    }
}
