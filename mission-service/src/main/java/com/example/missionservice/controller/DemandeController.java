package com.example.missionservice.controller;

import com.example.missionservice.entities.Demande;
import com.example.missionservice.service.DemandeService;
import com.sun.istack.NotNull;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @PostMapping("/")
    public Demande create(@RequestBody Demande demande)
    {
        return demandeService.create(demande);
    }

    @GetMapping("/Inscription/Nombre/{id}")
    public int GetNbrInscription(@PathVariable("id") Long missionId)
    {
        return demandeService.getNbrInscription(missionId);
    }

    @GetMapping("/Check/{userId}/{missionId}")
    public Boolean CheckInscription(@PathVariable("missionId") Long missionId, @PathVariable("userId") String userId)
    {
        return demandeService.checkUserDemande(userId, missionId);
    }

    @GetMapping("/Accepted/User/{id}")
    public List<Demande> AllAcceptedByUserId(@PathVariable("id") String userId)
    {
        return demandeService.getAllAccptedByUserId(userId);
    }

    @GetMapping("/Archived/User/{id}")
    public List<Demande> AllFinishedMissionsByUserId(@PathVariable("id") String userId)
    {
        return demandeService.getAllFinishedByUserId(userId);
    }

    @GetMapping("/User/{id}")
    public List<Demande> AllByUserId(@PathVariable("id") String userId)
    {
        return demandeService.getAllByUserId(userId);
    }

    @GetMapping("/Pending/Mission/{id}")
    public List<Demande> AllPendingByMission(@PathVariable("id") long missionId)
    {
        return demandeService.getAllPendingByMission(missionId);
    }

    @GetMapping("/NotAffected/Mission/{id}")
    public List<Demande> AllNotAffectedByMission(@PathVariable("id") long missionId)
    {
        return demandeService.getAllNotAffectedByMission(missionId);
    }

    @PutMapping("/")
    public Demande update(@RequestBody @NotNull Demande demande)
    {
        return  demandeService.update(demande);
    }

    @PutMapping("/switchAffected/{demandeId}")
    public Demande switchDemande(@PathVariable("demandeId") long demandeId)
    {
        return demandeService.switchDemande(demandeId);
    }

    @PutMapping("/switchAffectedByMission/{userId}/{missionId}")
    public Demande switchDemandeByMission(@PathVariable("userId") String userId,@PathVariable("missionId") long missionId )
    {
        return demandeService.switchByMissionAndUserId(userId,missionId);
    }

    @PutMapping("/Accepter/{demandeId}")
    public Demande accepter(@PathVariable("demandeId") long demandeId)
    {
        return demandeService.Accepter(demandeId);
    }

    @PutMapping("/Refuser/{demandeId}")
    public Demande refuser(@PathVariable("demandeId") long demandeId)
    {
        return demandeService.refuser(demandeId);
    }
}
