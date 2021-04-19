package com.improveme.profileservice.controller;

import com.improveme.profileservice.entities.Experience;
import com.improveme.profileservice.entities.Profile;
import com.improveme.profileservice.entities.Skills;
import com.improveme.profileservice.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<Profile> get(@PathVariable("userId") String userId) {
        Profile profile = profileService.getProfileByUserId(userId);
        if (profile != null)
            return new ResponseEntity<>(profile, HttpStatus.OK);
        else
            return new ResponseEntity("utilisateur n'existe pas", HttpStatus.NOT_FOUND);

    }

    @PostMapping(value = "/experience/{id}")
    public Experience addExperienceToProfile(@RequestBody Experience experience , @PathVariable("id") long id)
    {
        Logger.getGlobal().info("Experience update "+experience.toString());

        return profileService.addExperience(experience,id);
    }

    @PostMapping(value = "/")
    public Profile create(@RequestBody Profile profile)
    {
        return profileService.addProfile(profile);
    }

    @PutMapping
    public Profile update(@RequestBody Profile profile)
    {
        Logger.getGlobal().info("profile update "+profile.toString());
        return profileService.updateProfile(profile);
    }



    @PutMapping("/experience")
    public Experience updateExperience(@RequestBody Experience experience)
    {
       return profileService.upExperience(experience);
    }

    @PutMapping("/skills/{id}")
    public Profile addSkillstoProfile(@RequestBody List<Skills> skillsList , @PathVariable("id") long id)
    {
        return profileService.addSkillsToProfile(skillsList,id);
    }

    @DeleteMapping("/skills/{idProfile}/{idS}")
    public Profile removeSkillsfromProfile(@PathVariable("idProfile") long idP, @PathVariable("idS") long idS )
    {

        return profileService.removeSkillsFromProfile(idS,idP);
    }

    @DeleteMapping("/experience/{id}")
    public ResponseEntity removeExperience(@PathVariable("id") long id)
    {
         profileService.removeExperience(id);

         return new ResponseEntity(HttpStatus.OK);
    }




}
