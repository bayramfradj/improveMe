package com.example.missionservice.controller;

import com.example.missionservice.entities.Mission;
import com.example.missionservice.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @PostMapping("/")
    public Mission create(@RequestBody Mission mission)
    {
        return missionService.Create(mission);
    }

    @PutMapping("/{id}")
    public Mission update(@PathVariable("id") long id, @RequestBody Mission mission)
    {
        return missionService.update(id,mission);
    }
}
