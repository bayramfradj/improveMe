package com.example.missionservice.dao;

import com.example.missionservice.entities.Mission;
import com.example.missionservice.entities.StateMission;
import com.example.missionservice.entities.TypeMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission,Long> {
    List<Mission> findByTypeMission(TypeMission typeMission);
    List<Mission> findByEntrepriseId(String entrepriseId);
    List<Mission> findAllByStateMission(StateMission stateMission);
    List<Mission> findByTypeMissionAndStateMission(TypeMission typeMission, StateMission stateMission);
    List<Mission> findAllByCoachIdAndStateMission(String coachId, StateMission stateMission);

}
