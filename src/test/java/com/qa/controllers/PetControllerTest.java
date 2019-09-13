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
import java.util.ArrayList;
import java.util.List;

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
  
    public void listAllPets() {
        List<Pets> petList = new ArrayList<>();

        Pets pet1 = new Pets();
        pet1.setName("Oscar");
        pet1.setColour("Brown");
        pet1.setType("Dog");
        pet1.setId(2L);

        Pets pet2 = new Pets();
        pet2.setName("Fudge");
        pet2.setAge(11);
        pet2.setId(4L);

        petList.add(pet1);
        petList.add(pet2);

        when(repository.findAll()).thenReturn(petList);

        assertEquals(petController.getPets().get(0).getName(), "Oscar");
        assertEquals(petController.getPets().get(0).getId(), new Long(2L));
        assertEquals(petController.getPets().get(1).getName(), "Fudge");
        assertEquals(petController.getPets().get(1).getId(), new Long(4L));

    }

    @Test
    public void createPetTest() {
        Pets pet1 = new Pets();
        pet1.setName("Pippa");
        pet1.setId(1L);

        when(repository.saveAndFlush(pet1)).thenReturn(pet1);

        assertEquals(petController.createPet(pet1).getName(), "Pippa");
        assertEquals(petController.createPet(pet1).getId(), new Long(1L));

    }


}
