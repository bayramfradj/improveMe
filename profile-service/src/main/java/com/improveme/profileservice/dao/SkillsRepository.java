package com.improveme.profileservice.dao;

import com.improveme.profileservice.entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skills,Long> {
}
