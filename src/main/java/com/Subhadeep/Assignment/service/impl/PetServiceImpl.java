package com.Subhadeep.Assignment.service.impl;

import com.Subhadeep.Assignment.exception.PetNotFoundException;
import com.Subhadeep.Assignment.model.Pet;
import com.Subhadeep.Assignment.repository.PetRepository;
import com.Subhadeep.Assignment.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public String createPet(Pet pet) {
        // More Business Logic
        petRepository.save(pet);
        return "Success";
    }

    @Override
    public String updatePet(Pet pet) {
        // More Business Logic
        petRepository.save(pet);
        return "Success";
    }

    @Override
    public String deletePet(Long PetId) {
        // More Business Logic
        petRepository.deleteById(PetId);
        return "Success";
    }

    @Override
    public Pet getPet(Long PetId) {
        // More Business Logic
        if(petRepository.findById(PetId).isEmpty())
            throw new PetNotFoundException("Requested Pet does not exist");
        return petRepository.findById(PetId).get();
    }

    @Override
    public List<Pet> getAllPets() {
        // More Business Logic
        return petRepository.findAll();
    }
    @Override
    public List<Pet> getByName(String Name)
    {
        return petRepository.findByName(Name);
    }
}

