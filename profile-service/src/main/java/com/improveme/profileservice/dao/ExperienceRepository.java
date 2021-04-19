package com.improveme.profileservice.dao;

import com.improveme.profileservice.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience , Long>
{

}