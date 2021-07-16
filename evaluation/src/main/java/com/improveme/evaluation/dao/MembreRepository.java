package com.improveme.evaluation.dao;

import com.improveme.evaluation.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembreRepository extends JpaRepository<Membre,Long> {
    List<Membre> findAllByUserIdAndGroupe_Archived(String userId, boolean archived);
    Membre findFirstByUserIdAndGroupe_MissionIdAndGroupe_Archived(String userId,long missionId , boolean archived);
}
