package com.improveme.profileservice.controller;

import com.improveme.profileservice.entities.Skills;
import com.improveme.profileservice.services.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @GetMapping("/")
    public List<Skills> getAll()
    {
        return this.skillsService.getAllSkills();
    }

    @PostMapping("/")
    public Skills create(@RequestBody Skills skills)
    {
        return this.skillsService.create(skills);
    }
}
