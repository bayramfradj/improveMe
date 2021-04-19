package com.example.missionservice.service;

import com.example.missionservice.dao.MissionRepository;
import com.example.missionservice.entities.Mission;
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
        Mission m = missionRepository.getOne(id);
        mission.setId(m.getId());
        return missionRepository.save(mission);
    }

    public Mission getMission(long id)
    {
        return missionRepository.getOne(id);
    }

    public List<Mission> getAll()
    {
        return missionRepository.findAll();
    }




}
