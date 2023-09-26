package com.Subhadeep.Assignment.service.impl;

import com.Subhadeep.Assignment.exception.InvalidInputDataException;
import com.Subhadeep.Assignment.exception.PetNotFoundException;
import com.Subhadeep.Assignment.model.Pet;
import com.Subhadeep.Assignment.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class PetServiceImpl implements PetService {
    public PetServiceImpl() {
    }

    @Autowired
    private RestTemplate petRepository;

    public PetServiceImpl(RestTemplate petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet createPet(Pet pet) {
        try {
            // More Business Logic
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
            HttpEntity<Pet> entity = new HttpEntity<Pet>(pet, headers);

            return petRepository.exchange(
                    "https://petstore.swagger.io/v2/pet", HttpMethod.POST, entity, Pet.class).getBody();
        } catch (HttpClientErrorException.BadRequest ex) {
            throw new InvalidInputDataException("Invalid input data");
        }
    }

    @Override
    public Pet updatePet(Pet pet) {
        try {
            // More Business Logic
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
            HttpEntity<Pet> entity = new HttpEntity<Pet>(pet, headers);

            return petRepository.exchange(
                    "https://petstore.swagger.io/v2/pet", HttpMethod.PUT, entity, Pet.class).getBody();
        }catch (HttpClientErrorException.BadRequest ex) {
            throw new InvalidInputDataException("Invalid input data");
        }
    }

    @Override
    public String deletePet(Long PetId) {
        try {
            // More Business Logic
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
            HttpEntity<Pet> entity = new HttpEntity<Pet>(headers);

            return petRepository.exchange(
                    "https://petstore.swagger.io/v2/pet/" + String.valueOf(PetId), HttpMethod.DELETE, entity, String.class).getBody();
        } catch (HttpClientErrorException.NotFound ex) {
            throw new PetNotFoundException("Pet not found with id: " + PetId);
        } catch (HttpClientErrorException.BadRequest ex) {
            throw new InvalidInputDataException("Invalid input data");
        }
    }

    @Override
    public Pet getPet(Long PetId) {
        try {
            // More Business Logic
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
            HttpEntity<Pet> entity = new HttpEntity<Pet>(headers);

            return petRepository.exchange(
                    "https://petstore.swagger.io/v2/pet/" + String.valueOf(PetId), HttpMethod.GET, entity, Pet.class).getBody();
        }catch (HttpClientErrorException.NotFound ex) {
            throw new PetNotFoundException("Pet not found with id: " + PetId);
        } catch (HttpClientErrorException.BadRequest ex) {
            throw new InvalidInputDataException("Invalid input data");
        }
    }
}
