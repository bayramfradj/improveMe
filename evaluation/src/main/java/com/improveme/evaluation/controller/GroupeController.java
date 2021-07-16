package com.improveme.evaluation.controller;

import com.improveme.evaluation.entities.Groupe;
import com.improveme.evaluation.entities.Membre;
import com.improveme.evaluation.service.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Groupes")
public class GroupeController {
    @Autowired
    private GroupeService groupeService;

    @GetMapping("/{missionId}")
    public List<Groupe> AllByMissionId(@PathVariable("missionId") long missionId)
    {
        return groupeService.AllByMissionId(missionId);
    }

    @GetMapping("/ByUser/{userId}")
    public List<Groupe> AllBybyUser(@PathVariable("userId") String userId)
    {
        return groupeService.getGroupesByUserId(userId);
    }

    @GetMapping("Mission/ByUser/{userId}/{missionId}")
    public Groupe ByUser(@PathVariable("userId") String userId, @PathVariable("missionId") long missionId )
    {
        return groupeService.getGroupeByUserAndMission(userId, missionId);
    }

    @PostMapping("/")
    public Groupe save(@RequestBody Groupe groupe)
    {
        return groupeService.Save(groupe);
    }

    @PutMapping("/")
    public Groupe update(@RequestBody Groupe groupe)
    {
        return groupeService.Update(groupe);
    }

    @PutMapping("/{groupeId}")
    public Groupe archiver(@PathVariable("groupeId") long groupeId)
    {
        return groupeService.archiver(groupeId);
    }

    @PostMapping("{groupeId}/Members/")
    public Membre AddMembre(@RequestBody Membre membre, @PathVariable("groupeId") long groupeId)
    {
        return groupeService.AddMember(membre,groupeId);
    }

    @DeleteMapping("/Members/{membreId}")
    public boolean deleteMember(@PathVariable("membreId") long membreId)
    {
        return groupeService.removeMember(membreId);
    }
}