package com.devopsca.damianspetitions.controller;

import com.devopsca.damianspetitions.model.Petition;
import com.devopsca.damianspetitions.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class SearchController {

    @Autowired
    private PetitionService petitionService;

    @GetMapping("/petitions/search")
    public String searchPetitions(@RequestParam String title, Model model) {
        Petition searchResults = petitionService.searchPetitionByTitle(title);
        model.addAttribute("petitions", searchResults);
        return "search-petitions";
    }
}
