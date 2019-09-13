package com.qa.controllers;

import com.qa.models.Pets;
import com.qa.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class PetController {

    @Autowired
    private PetRepository repository;

    @Transactional
    @RequestMapping(value = "pets/{id}", method = RequestMethod.PUT)
    public Pets updatePets(@PathVariable Long id, @RequestBody Pets pet){
        Pets existing = repository.findOne(id);

        existing.updateAll(pet);

        return repository.saveAndFlush(existing);
    }


}
