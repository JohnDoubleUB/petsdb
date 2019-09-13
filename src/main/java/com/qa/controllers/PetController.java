package com.qa.controllers;

import com.qa.models.Pets;
import com.qa.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RestController
@CrossOrigin()
public class PetController {

    @Autowired
    private PetRepository repository;

    //delete a single pet by id
    @RequestMapping(value = "pets/{id}", method = RequestMethod.DELETE)
    public Pets deletePet(@PathVariable Long id) {
        Pets existing = repository.findOne(id);
        repository.delete(existing);
        return existing;
    }
     
      
    @Transactional
    @RequestMapping(value = "pets/{id}", method = RequestMethod.PUT)
    public Pets updatePets(@PathVariable Long id, @RequestBody Pets pet){
        Pets existing = repository.findOne(id);

        existing.updateAll(pet);

        return repository.saveAndFlush(existing);
    }

    @RequestMapping(value = "pets", method = RequestMethod.GET)
    public List<Pets> getPets(){
        return repository.findAll();
    }

    @RequestMapping(value = "pets", method = RequestMethod.POST)
    public Pets createPet(@RequestBody Pets p){
        return repository.saveAndFlush(p);
    }


}

