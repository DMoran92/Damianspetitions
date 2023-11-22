package com.devopsca.damianspetitions.controller;

import com.devopsca.damianspetitions.model.Petition;
import com.devopsca.damianspetitions.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/petitions")
public class CreatePetitionController {
    /* autowired annotation - have single PetitionService instance */
    @Autowired
    private PetitionService petitionService;

    /* handle /create get call, serve the create-petitions.html */
    @GetMapping("/create")
    public String showCreatePetition() {
        return "create-petitions";
    }
    /* handle the post form */
    @PostMapping("/create")
    public String createPetition(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String name,
            @RequestParam String email) {

        /* Create a new petition and add it to the service */
        Petition newPetition = new Petition(title, content);
        petitionService.addSignatureToPetition(newPetition, name, email);
        petitionService.addPetition(newPetition);
        System.out.println("Number of Petitions already stored: " + petitionService.getAllPetitions().size());
        /* return to base dir, which should present all current petitions */
        return "redirect:/";
    }
}
