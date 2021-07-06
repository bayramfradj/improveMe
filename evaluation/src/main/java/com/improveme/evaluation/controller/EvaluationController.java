package com.improveme.evaluation.controller;

import com.improveme.evaluation.entities.Evaluation;
import com.improveme.evaluation.entities.Response;
import com.improveme.evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/")
    public Evaluation AddEvaluation(@RequestBody Evaluation evaluation)
    {
        return evaluationService.save(evaluation);
    }

    @PutMapping("/")
    public Evaluation upEvaluation(@RequestBody Evaluation evaluation)
    {
        return evaluationService.update(evaluation);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteEvaluation(@PathVariable("id") long id)
    {
        return evaluationService.delete(id);
    }

    @GetMapping("/{id}")
    public Evaluation getEvaluationById(@PathVariable("id") long id)
    {
        return evaluationService.getById(id);
    }

    @GetMapping("/Mission/{id}")
    public List<Evaluation> getEvalsByMissionId(@PathVariable("id") long id)
    {
        return evaluationService.getListByMissionId(id);
    }

    @PostMapping("/Response/")
    public Response saveRes(@RequestBody Response response)
    {
        return evaluationService.saveResponse(response);
    }

    @PutMapping("/Response/")
    public Response upRes(@RequestBody Response response)
    {
        return evaluationService.updateResponse(response);
    }

    @DeleteMapping("/Response/{id}")
    public Boolean deleteRes(@PathVariable("id") long id)
    {
        return evaluationService.delelteResById(id);
    }

    @GetMapping("/Response/{id}")
    public Response getResById(@PathVariable("id") long id)
    {
        return evaluationService.getResponseById(id);
    }

    @GetMapping("/Response/Groupe/{groupeId}/{evaluationId}")
    public Response getResById(@PathVariable("groupeId") long groupeId, @PathVariable("evaluationId") long evaluationId )
    {
        return evaluationService.getResponseByGroupeAndEvaluation(groupeId, evaluationId);
    }


}
