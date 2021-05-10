package com.example.missionservice.service;

import com.example.missionservice.dao.DemandeRepository;
import com.example.missionservice.entities.Demande;
import com.example.missionservice.entities.Mission;
import com.example.missionservice.entities.StateCandidature;
import com.example.missionservice.entities.StateMission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private MissionService missionService;

    public Demande create(Demande demande)
    {
        demande.setMission(missionService.getMission(demande.getMission().getId()));
        return demandeRepository.save(demande);
    }

    public int getNbrInscription(Long missionId)
    {
        return demandeRepository.countAllByMissionAndIsPayed(missionService.getMission(missionId),true);
    }

    public boolean checkUserDemande(String userId, Long missionId)
    {
        return demandeRepository.findByUserIdAndMission(userId, missionService.getMission(missionId)) != null;
    }

    public List<Demande> getAllAccptedByUserId(String userId)
    {
        return demandeRepository.findAllByUserIdAndStateCandidatureAndMission_StateMission(userId, StateCandidature.ACCEPTED, StateMission.ACCEPTED);
    }

    public Demande update(Demande demande)
    {
        demande.setId(demandeRepository.findById(demande.getId()).get().getId());
        return demandeRepository.save(demande);
    }
}
