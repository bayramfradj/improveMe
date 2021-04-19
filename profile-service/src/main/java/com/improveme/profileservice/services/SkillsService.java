package com.improveme.profileservice.services;

import com.improveme.profileservice.dao.SkillsRepository;
import com.improveme.profileservice.entities.Skills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {

    @Autowired
    private SkillsRepository skillsRepository;

    public Skills getSkills(Long id)
    {
        return skillsRepository.findById(id).orElse(null);
    }

    public List<Skills> getAllSkills()
    {
        return skillsRepository.findAll();
    }

    public Skills create(Skills skills)
    {
        return skillsRepository.save(skills);
    }
}
