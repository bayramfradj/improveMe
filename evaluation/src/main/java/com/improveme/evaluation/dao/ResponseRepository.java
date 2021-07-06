package com.improveme.evaluation.dao;

import com.improveme.evaluation.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    Response findFirstByGroupeIdAndEvaluation_Id(long groupeId, long evaluationId);
}
