package com.improveme.evaluation.dao;

import com.improveme.evaluation.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe,Long> {
        List<Groupe> findAllByMissionIdAndArchived(long missionId, boolean archived);
}
