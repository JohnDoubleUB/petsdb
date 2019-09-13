package com.qa.controllers;

import com.qa.models.Pets;
import com.qa.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetController {

    @Autowired
    private PetRepository repository;

    @RequestMapping(value = "pets", method = RequestMethod.GET)
    public List<Pets> getPets(){
        return repository.findAll();
    }

    @RequestMapping(value = "pets", method = RequestMethod.POST)
    public Pets createPet(@RequestBody Pets p){
        return repository.saveAndFlush(p);
    }

}
