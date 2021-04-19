package com.improveme.profileservice.services;

import com.improveme.profileservice.dao.ExperienceRepository;
import com.improveme.profileservice.dao.ProfileRepository;
import com.improveme.profileservice.entities.Experience;
import com.improveme.profileservice.entities.Profile;
import com.improveme.profileservice.entities.Skills;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;


@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private SkillsService skillsService;

    public Profile getProfileByUserId(String userId)
    {
        return profileRepository.findByUserId(userId).orElse(null);
    }

    public Profile addProfile(Profile profile)
    {
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Profile profile)
    {
        Profile p = this.getProfileByUserId(profile.getUserId());
        p.setName(profile.getName());
        p.setTitle(profile.getTitle());
        p.setBio(profile.getBio());
        p.setImg(profile.getImg());
        return profileRepository.save(p);
    }

    public Experience addExperience(Experience experience , Long profileId)
    {
       // experience = experienceRepository.save(experience);

        Profile profile = profileRepository.getOne(profileId);

        profile.getExperiences().add(experience);
        profileRepository.save(profile);
        return experience;
    }

    public Experience upExperience(Experience experience)
    {
        Experience ex = experienceRepository.getOne(experience.getId());
        ex.setPoste(experience.getPoste());
        ex.setEtablissement(experience.getEtablissement());
        ex.setDescription(experience.getDescription());
        ex.setStartDate(experience.getStartDate());
        ex.setEndDate(experience.getEndDate());
        return experienceRepository.save(ex);
    }

    public void removeExperience(Long experienceId)
    {
        Experience experience = experienceRepository.getOne(experienceId);
        experienceRepository.delete(experience);
      /*  Profile profile = profileRepository.getOne(profileId);
        profile.getExperiences().remove(experience);
       return profileRepository.save(profile);*/
    }

    public Profile addSkillsToProfile(List<Skills> skilles, Long profileId)
    {
        Profile profile = profileRepository.getOne(profileId);
        skilles.forEach( skills -> {
            skills = ( skills.getId() == 0L ) ?
                    skillsService.create(skills) :
                    skillsService.getSkills(skills.getId());
            profile.getSkills().add(skills);
        });

        return profileRepository.save(profile);
    }

    public Profile removeSkillsFromProfile(long skillsId, long profileId)
    {
        Profile profile = profileRepository.getOne(profileId);
        Skills skills = profile.getSkills().stream().filter(new Predicate<Skills>() {
            @Override
            public boolean test(Skills skills) {
                return skills.getId() == skillsId;
            }
        }).findFirst().get();
        profile.getSkills().remove(skills);
        return profileRepository.save(profile);
    }




}
