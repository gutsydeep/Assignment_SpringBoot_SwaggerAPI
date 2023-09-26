package com.Subhadeep.Assignment.controller;

import com.Subhadeep.Assignment.model.Pet;
import com.Subhadeep.Assignment.response.ResponseHandler;
import com.Subhadeep.Assignment.service.PetService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pet")
public class PetController
{
    PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // Read Specific Cloud Vendor Details from DB
    @GetMapping("/{PetId}")
    @ApiOperation(value ="Pet id", notes="Provide pet details",
            response = ResponseEntity.class)
    public ResponseEntity<Object> getPetDetails(@PathVariable("PetId") Long PetId)
    {
        return ResponseHandler.responseBuilder("Requested Pet Details are given here",
                HttpStatus.OK, petService.getPet(PetId));
    }

    @PostMapping("/")
    public String createPetDetails(@RequestBody Pet pet)
    {
        petService.createPet(pet);
        return "Pet Created Successfully";
    }

    @PutMapping("/")
    public String updatePetDetails(@RequestBody Pet pet)
    {
        petService.updatePet(pet);
        return "Pet Updated Successfully";
    }

    @DeleteMapping("/{PetId}")
    public String deletePetDetails(@PathVariable("PetId") Long PetId)
    {
        petService.deletePet(PetId);
        return "Pet Deleted Successfully";
    }
}
