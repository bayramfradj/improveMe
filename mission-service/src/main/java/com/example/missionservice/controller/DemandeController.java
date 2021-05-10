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

    @GetMapping("/Accpted/User/{id}")
    public List<Demande> AllAcceptedByUserId(@PathVariable("id") String userId)
    {
        return demandeService.getAllAccptedByUserId(userId);
    }

    @PutMapping("/")
    public Demande update(@RequestBody @NotNull Demande demande)
    {
        return  demandeService.update(demande);
    }
}
