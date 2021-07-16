package com.improveme.evaluation.service;

import com.improveme.evaluation.dao.EvaluationRepository;
import com.improveme.evaluation.dao.ResponseRepository;
import com.improveme.evaluation.entities.Evaluation;
import com.improveme.evaluation.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private ResponseRepository responseRepository;

    public Evaluation save(Evaluation evaluation)
    {
        return evaluationRepository.save(evaluation);
    }

    public Evaluation update(Evaluation evaluation)
    {
       Evaluation eval = evaluationRepository.findById(evaluation.getId()).get();
       evaluation.setId(eval.getId());
       return evaluationRepository.save(evaluation);
    }

    public Boolean delete(long id)
    {
        try {
            evaluationRepository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    public Evaluation getById(long id)
    {
        return evaluationRepository.findById(id).get();
    }

    public List<Evaluation> getListByMissionId(long missionId)
    {
        return evaluationRepository.findAllByMissionId(missionId);
    }

    public Response saveResponse(Response response, long evald)
    {
        Evaluation eval = evaluationRepository.findById(evald).get();
        response.setEvaluation(eval);
        return responseRepository.save(response);
    }

    public Response updateResponse(Response response, long repId)
    {
        Response res = responseRepository.findById(repId).get();
        response.setId(res.getId());
        response.setEvaluation(res.getEvaluation());
        return responseRepository.save(response);
    }

    public Boolean delelteResById(long id)
    {
        try {
            responseRepository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    public Response getResponseById(long resId)
    {
        return responseRepository.findById(resId).get();
    }

    public Response getResponseByGroupeAndEvaluation(long groupeId, long evaluationId)
    {
        return responseRepository.findFirstByGroupeIdAndEvaluation_Id(groupeId, evaluationId);
    }

}
