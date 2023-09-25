package com.Subhadeep.Assignment.service;

import com.Subhadeep.Assignment.model.Pet;

import java.util.List;

public interface PetService {
    public String createPet(Pet pet);
    public String updatePet(Pet pet);
    public String deletePet(Long PetId);
    public Pet getPet(Long PetId);
    public List<Pet> getAllPets();
    public List<Pet> getByName(String Name);
}