package com.devopsca.damianspetitions.controller;

import com.devopsca.damianspetitions.model.Petition;
import com.devopsca.damianspetitions.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddSignatureController {

    @Autowired
    private PetitionService petitionService;
    /* add signature to the petition */
    @PostMapping("/petitions/add-signature")
    public String addSignatureToPetition(
            @RequestParam Long petitionId,
            @RequestParam String name,
            @RequestParam String email) {

        Petition petition = petitionService.getPetitionById(petitionId);
        petitionService.addSignatureToPetition(petition, name, email);

        /* Redirect back to the petition details page */
        return "redirect:/petitions/" + petitionId;
    }
}
