package com.Subhadeep.Assignment.service;

import com.Subhadeep.Assignment.model.Pet;


public interface PetService {
    public Pet createPet(Pet pet);
    public Pet updatePet(Pet pet);
    public String deletePet(Long PetId);
    public Pet getPet(Long PetId);
}