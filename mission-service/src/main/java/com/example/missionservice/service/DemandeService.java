package com.example.missionservice.service;

import com.example.missionservice.dao.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;
}
