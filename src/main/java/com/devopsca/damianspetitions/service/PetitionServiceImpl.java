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

    @Override
    /* create and populate the default petitions and signatures.
      Petition names taken from: https://www.gopetition.com/petition-examples
     */
    public void initializeDefaultPetitions() {
        Petition defaultPetition1 = new Petition("Ban BEAR FARMS IN KOREA - Save Bears!"
                , "There are more than 1000 bears in captivity (most of them are Asian Moon Bears- Moon Bears," +
                " with distinctive 'V' mark on their chest) in over 60 bear farms in Korea.\n" +
                "The Korean government is responsible for this as they started importing bears from North America " +
                "and Japan for breeding and export in early 1980's.");
        addPetition(defaultPetition1);
        addSignatureToPetition(defaultPetition1, "John Kerry", "J.K1970@gmail.com");
        addSignatureToPetition(defaultPetition1, "Una Frazier", "UnaHello@protonmail.com");
        addSignatureToPetition(defaultPetition1, "Douglas Day", "DouglasUK@yahoo.co.uk");
        addSignatureToPetition(defaultPetition1, "Francis Powers", "Francis1988@yahoo.co.uk");

        Petition defaultPetition2 = new Petition("Europeans Against Whaling", "The Atlantic Whale Foundation" +
                " operates the volunteer programme on the whale watching boats of Tenerife, Spain.\n" +
                "There are one million Europeans each year whale watching on these boats and this petition will give " +
                "voice to their concerns about whaling.\n" +
                "As citizens of the European Union we want our governments to take all necessary actions to protect our " +
                "planet's environment.");
        addPetition(defaultPetition2);
        addSignatureToPetition(defaultPetition2, "Joe Doe", "joe.d90@aol.com");
        addSignatureToPetition(defaultPetition2, "Cecil Olson", "cecilolson@gmail.com");

        Petition defaultPetition3 = new Petition("Stop HS2 (High Speed Rail)", "On 11th March 2010," +
                " the outgoing Labour Government announced plans for a High Speed Rail (HS2) link from London Euston to" +
                " Birmingham. It was reported then that it would cost £11bn, but that figure was 6 years out of date." +
                " On the same day you could have got information from the Department for Transport which put the cost at" +
                " £17.4bn or from HS2 Ltd, which put it at £25.5bn, or 2.8% of our generational national debt " +
                "(based on a total national debt estimate of £916.6bn).");
        addPetition(defaultPetition3);
        addSignatureToPetition(defaultPetition3, "Sidney Collins", "Collinssid@gmail.com");
        addSignatureToPetition(defaultPetition3, "Dennis Peay", "peayden@yahoo.co.uk");
        addSignatureToPetition(defaultPetition3, "Morton Lindsey", "mortonlis@gmail.com");

    }
}
