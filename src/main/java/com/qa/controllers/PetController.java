package com.qa.controllers;

import com.qa.models.Pets;
import com.qa.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}

