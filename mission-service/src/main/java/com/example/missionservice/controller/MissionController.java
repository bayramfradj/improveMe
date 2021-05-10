package com.example.missionservice.controller;

import com.example.missionservice.entities.Mission;
import com.example.missionservice.entities.StateMission;
import com.example.missionservice.entities.TypeMission;
import com.example.missionservice.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public Mission getById(@PathVariable("id") long id)
    {
        return missionService.getMission(id);
    }

    @GetMapping("/Accepted/")
    public List<Mission> allAccepted()
    {
        return missionService.AllAccepted();
    }

    @GetMapping("/Entreprise/{id}")
    public List<Mission> allByEntrepriseId(@PathVariable("id") String entrepriseId)
    {
        return missionService.getByEntrepriseId(entrepriseId);
    }

    @GetMapping("/Formation/")
    public List<Mission> AllFormation()
    {
        return missionService.getMissionsAccepted(TypeMission.FORMATION, StateMission.ACCEPTED);
    }

    @GetMapping("/Payante/")
    public List<Mission> AllPayante()
    {
        return missionService.getMissionsAccepted(TypeMission.PAYANTE, StateMission.ACCEPTED);
    }

    @GetMapping("/Recrutement/")
    public List<Mission> AllRec()
    {
        return missionService.getMissionsAccepted(TypeMission.RECRUTEMENT, StateMission.ACCEPTED);
    }

    @GetMapping("/Invitation/")
    public List<Mission> allInvi()
    {
        return missionService.AllInvitation();
    }

    @GetMapping("/Prototype/")
    public List<Mission> allProto()
    {
        return missionService.AllProtoType();
    }

    @GetMapping("/Accepted/Coach/{id}")
    public List<Mission> AllAcceptedByCoachId(@PathVariable("id") String coachId)
    {
        return missionService.AllAcceptedByCoachId(coachId);
    }
}
