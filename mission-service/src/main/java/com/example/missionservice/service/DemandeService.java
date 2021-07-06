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

    public List<Demande> getAllFinishedByUserId(String userId)
    {
        return demandeRepository.findAllByUserIdAndStateCandidatureAndMission_StateMission(userId, StateCandidature.ACCEPTED, StateMission.ARCHIVED);
    }

    public List<Demande> getAllPendingByMission(long missionId)
    {
        return demandeRepository.findAllByMissionAndStateCandidature(missionService.getMission(missionId),StateCandidature.PENDING);
    }

    public List<Demande> getAllByUserId(String UserId)
    {
        return demandeRepository.findAllByUserId(UserId);
    }

    public List<Demande> getAllNotAffectedByMission(long MissionId)
    {
        return demandeRepository.findAllByMissionAndStateCandidatureAndAffected(missionService.getMission(MissionId),StateCandidature.ACCEPTED,false );
    }

    public Demande update(Demande demande)
    {
        demande.setId(demandeRepository.findById(demande.getId()).get().getId());
        return demandeRepository.save(demande);
    }

    public Demande switchDemande(long demandeId)
    {
        Demande demande = demandeRepository.findById(demandeId).get();
        demande.setAffected(!demande.isAffected());
        return demandeRepository.save(demande);
    }

    public Demande switchByMissionAndUserId(String userId, long missionId)
    {
        Mission mission = missionService.getMission(missionId);
        Demande demande = demandeRepository.findByUserIdAndMission(userId, mission);
        demande.setAffected(!demande.isAffected());
        return demandeRepository.save(demande);
    }

    public Demande Accepter(long demandeId)
    {
        Demande d = demandeRepository.findById(demandeId).get();
        d.setAffected(false);
        d.setStateCandidature(StateCandidature.ACCEPTED);
        return demandeRepository.save(d);
    }

    public Demande refuser(long demandeId)
    {
        Demande d = demandeRepository.findById(demandeId).get();
        d.setAffected(false);
        d.setStateCandidature(StateCandidature.REJECTED);
        return demandeRepository.save(d);
    }
}
