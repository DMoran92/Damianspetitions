package com.devopsca.damianspetitions.controller;

import com.devopsca.damianspetitions.model.Petition;
import com.devopsca.damianspetitions.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewPetitionsController {

    @Autowired
    private PetitionService petitionService;

    @GetMapping("/")
    public String viewPetitions(Model model) {
        model.addAttribute("petitions", petitionService.getAllPetitions());
        return "view-petitions";
    }
    /* present specific petition based on the requested id */
    @GetMapping("/petitions/{id}")
    public String viewPetitionDetails(@PathVariable Long id, Model model) {
        Petition petition = petitionService.getPetitionById(id);
        model.addAttribute("petition", petition);
        return "petitions-details";
    }
}
