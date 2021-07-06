package com.improveme.evaluation.service;

import com.improveme.evaluation.dao.GroupeRepository;
import com.improveme.evaluation.dao.MembreRepository;
import com.improveme.evaluation.entities.Groupe;
import com.improveme.evaluation.entities.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupeService {
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private MembreRepository membreRepository;

    public List<Groupe> AllByMissionId(long missionId)
    {
        return groupeRepository.findAllByMissionIdAndArchived(missionId, false);
    }

    public Groupe Save(Groupe groupe)
    {
        return groupeRepository.save(groupe);
    }

    public Groupe Update(Groupe groupe)
    {
        Groupe updatedGroupe = groupeRepository.findById(groupe.getId()).get();
        groupe.setId(updatedGroupe.getId());
        return groupeRepository.save(groupe);
    }

    public Groupe archiver(long groupeId)
    {
       Groupe ArchivedGroupe = groupeRepository.findById(groupeId).get();
       ArchivedGroupe.setArchived(true);
       return groupeRepository.save(ArchivedGroupe);
    }

    public Membre AddMember(Membre membre, long groupeId)
    {
        Groupe groupe = groupeRepository.findById(groupeId).get();
        membre.setGroupe(groupe);
        return membreRepository.save(membre);
    }

    public Boolean removeMember(long membreId)
    {
        try {
            membreRepository.deleteById(membreId);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    public List<Groupe> getGroupesByUserId(String userId)
    {
        return membreRepository.findAllByUserIdAndGroupe_Archived(userId, false)
                .stream().map(membre -> membre.getGroupe())
                .collect(Collectors.toList());
    }
}
