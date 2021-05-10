package com.example.missionservice.service;

import com.example.missionservice.dao.MissionRepository;
import com.example.missionservice.entities.Mission;
import com.example.missionservice.entities.StateMission;
import com.example.missionservice.entities.TypeMission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    public Mission Create(Mission mission)
    {
        return missionRepository.save(mission);
    }

    public Mission update(long id, Mission mission)
    {
        Mission m = missionRepository.findById(id).get();
        mission.setId(m.getId());
        return missionRepository.save(mission);
    }

    public List<Mission> getMissionsAccepted(TypeMission typeMission, StateMission stateMission)
    {
        return missionRepository.findByTypeMissionAndStateMission(typeMission,stateMission);
    }

    public Mission getMission(long id)
    {
        return missionRepository.findById(id).get();
    }

    public List<Mission> getAll()
    {
        return missionRepository.findAll();
    }

    public List<Mission> getByEntrepriseId(String entrepriseId)
    {
        return missionRepository.findByEntrepriseId(entrepriseId);
    }

    public List<Mission> AllAccepted()
    {
        return missionRepository.findAllByStateMission(StateMission.ACCEPTED);
    }

    public List<Mission> AllInvitation()
    {
        return missionRepository.findAllByStateMission(StateMission.PENDING);
    }

    public List<Mission> AllProtoType()
    {
        return missionRepository.findByTypeMission(TypeMission.PROTOTYPE);
    }

    public List<Mission> AllAcceptedByCoachId(String coachId)
    {
        return missionRepository.findAllByCoachIdAndStateMission(coachId, StateMission.ACCEPTED);
    }




}
