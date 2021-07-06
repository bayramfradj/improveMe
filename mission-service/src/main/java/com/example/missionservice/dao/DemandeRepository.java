package com.example.missionservice.dao;

import com.example.missionservice.entities.Demande;
import com.example.missionservice.entities.Mission;
import com.example.missionservice.entities.StateCandidature;
import com.example.missionservice.entities.StateMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
     Demande findByUserIdAndMission(String userId, Mission mission);
     int countAllByMissionAndIsPayed(Mission mission, boolean isPayed);
     List<Demande> findAllByUserIdAndStateCandidatureAndMission_StateMission(String UserId, StateCandidature stateCandidature, StateMission stateMission);
     List<Demande> findAllByMissionAndStateCandidature(Mission mission, StateCandidature stateCandidature);
     List<Demande> findAllByUserId(String UserId);
     List<Demande> findAllByMissionAndStateCandidatureAndAffected(Mission mission, StateCandidature stateCandidature, boolean affected);
     Demande findFirstByUserIdAndMission(String UserId, Mission mission );
}
