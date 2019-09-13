package com.qa.controllers;

import com.qa.models.Pets;
import com.qa.repository.PetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetControllerTest {

    @InjectMocks
    private PetController petController;

    @Mock
    private PetRepository repository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void updatePetsTest(){
        Pets pet1 = new Pets();
        pet1.setName("Luka");
        pet1.setColour("black");
        pet1.setId(3L);

        when(repository.findOne(3L)).thenReturn(pet1);
        when(repository.saveAndFlush(pet1)).thenReturn(pet1);

        assertEquals(petController.updatePets(3L, pet1).getId(),new Long(3L));
    }

    @Test
    public void deletePetTest(){
        Pets pet1 = new Pets();
        pet1.setName("Pip");
        pet1.setColour("Brown");
        pet1.setId(5L);


        when(repository.findOne(5L)).thenReturn(pet1);

        assertEquals(petController.deletePet(5L).getName(), "Pip");

    }


}
