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
    public Demande findByUserIdAndMission(String userId, Mission mission);
    public int countAllByMissionAndIsPayed(Mission mission, boolean isPayed);
    public List<Demande> findAllByUserIdAndStateCandidatureAndMission_StateMission(String UserId, StateCandidature stateCandidature, StateMission stateMission);
}
