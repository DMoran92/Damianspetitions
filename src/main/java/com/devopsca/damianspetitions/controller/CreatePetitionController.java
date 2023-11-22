package com.devopsca.damianspetitions.controller;

import com.devopsca.damianspetitions.model.Petition;
import com.devopsca.damianspetitions.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            @RequestParam String email,
            Model model) {

        /* Check if a petition with the same title already exists */
        if (petitionService.searchPetitionByTitle(title) != null) {
            model.addAttribute("errorMessage",
                    "Petition with the same name was already created. Please use search function.");
            return "create-petitions";
        }

        /* Create a new petition and add it to the service */
        Petition newPetition = new Petition(title, content);
        petitionService.addSignatureToPetition(newPetition, name, email);
        petitionService.addPetition(newPetition);
        System.out.println("Number of Petitions already stored: " + petitionService.getAllPetitions().size());
        /* return to base dir, which should present all current petitions */
        return "redirect:/";
    }
}
