package com.devopsca.damianspetitions.controller;

import com.devopsca.damianspetitions.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewPetitionsController {

    @Autowired
    private PetitionService petitionService;

    @GetMapping("/")
    public String viewPetitions(Model model) {
        model.addAttribute("petitions", petitionService.getAllPetitions());
        return "view-petitions";
    }
}
