package com.devopsca.damianspetitions.service;

import com.devopsca.damianspetitions.model.Petition;

import java.util.List;

public interface PetitionService {
    List<Petition> getAllPetitions();

    Petition getPetitionById(Long id);
    void addPetition(Petition petition);
    void addSignatureToPetition(Petition petition, String name, String email);
}
