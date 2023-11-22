package com.devopsca.damianspetitions.service;

import com.devopsca.damianspetitions.model.Petition;
import com.devopsca.damianspetitions.model.Signature;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* implementation of the petition service
  It should handle:
  1. Create petitions
  2. Add signature to the petition
  3. Return list of petitions
  4. Return specific petition
 */
@Service
public class PetitionServiceImpl implements PetitionService {

    /* initialize  map of petitions */
    private Map<Long, Petition> petitions = new HashMap<>();
    /* the id should always start at 1 */
    private long nextPetitionId = 1;

    /* return list of petitions */
    @Override
    public List<Petition> getAllPetitions() {
        return new ArrayList<>(petitions.values());
    }
    /* return specific petition */
    @Override
    public Petition getPetitionById(Long id) {
        return petitions.get(id);
    }

    /* add the petition to the list of current petitions */
    @Override
    public void addPetition(Petition petition) {
        /* increment the petition id*/
        petition.setId(nextPetitionId++);
        /* place the petition into the list of petitions */
        petitions.put(petition.getId(), petition);
        System.out.println("New petition added");
    }
    /* add the signature to specific petition
       if there are 0 signatures, create new list
       and include the signature
     */
    @Override
    public void addSignatureToPetition(Petition petition, String name, String email) {
        Signature signature = new Signature(name, email);
        /* get the list of signatures */
        List<Signature> signatures = petition.getSignatures();
        /* there are 0 signatures, create new list and include signature */
        if (signatures == null) {
            signatures = new ArrayList<>();
            petition.setSignatures(signatures);
        }
        signatures.add(signature);
        System.out.println("New signature added");
    }

    @Override
    public Petition searchPetitionByTitle(String title) {
        /* to avoid issues make the title lower case */
        String lowerCaseTitle = title.toLowerCase();

        return petitions.values().stream()
                .filter(petition -> petition.getTitle().toLowerCase().contains(lowerCaseTitle))
                .findFirst()
                .orElse(null);
    }
}
