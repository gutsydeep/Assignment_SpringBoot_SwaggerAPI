package com.Subhadeep.Assignment.service.impl;

import com.Subhadeep.Assignment.model.Category;
import com.Subhadeep.Assignment.model.Pet;
import com.Subhadeep.Assignment.model.Tag;
import com.Subhadeep.Assignment.repository.PetRepository;
import com.Subhadeep.Assignment.service.PetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;
    private PetService petService;
    AutoCloseable autoCloseable;
    Pet pet;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        petService = new PetServiceImpl(petRepository);
        pet = new Pet(0L, "doggo", new Category(0, "stringc"), Arrays.asList("Stringu"),new Tag(0L,"stringt"),"available");

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreatePet() {
        mock(Pet.class);
        mock(PetRepository.class);

        when(petRepository.save(pet)).thenReturn(pet);
        assertThat(petService.createPet(pet)).isEqualTo("Success");

    }

    @Test
    void testUpdatePet() {
        mock(Pet.class);
        mock(PetRepository.class);

        when(petRepository.save(pet)).thenReturn(pet);
        assertThat(petService.updatePet(pet)).isEqualTo("Success");
    }

    @Test
    void testDeletePet() {
        mock(Pet.class);
        mock(PetRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(petRepository)
                .deleteById(any());
        assertThat(petService.deletePet(1L)).isEqualTo("Success");
    }

    @Test
    void testGetPet() {
        mock(Pet.class);
        mock(PetRepository.class);

        when(petRepository.findById(1L)).thenReturn(Optional.ofNullable(pet));

        assertThat(petService.getPet(1L).getName())
                .isEqualTo(pet.getName());
    }

    @Test
    void testGetAllPets() {
        mock(Pet.class);
        mock(PetRepository.class);

        when(petRepository.findAll()).thenReturn(new ArrayList<Pet>(
                Collections.singleton(pet)
        ));

        assertThat(petService.getAllPets().get(0).getName()).
                isEqualTo(pet.getName());

    }
}
