package com.Subhadeep.Assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.Subhadeep.Assignment.model.Category;
import com.Subhadeep.Assignment.model.Pet;
import com.Subhadeep.Assignment.model.Tag;
import com.Subhadeep.Assignment.service.PetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PetService petService;
    Pet petOne;
    Pet petTwo;
    List<Pet> petList = new ArrayList<>();
    String[] Photolist1={"StringU"};
    List<String> photoList1=Arrays.asList(Photolist1);

    @BeforeEach
    void setUp() {
        petOne = new Pet(1L, "doggo", new Category(0, "stringc"),photoList1,new Tag(0L,"stringt"),"available");

        petTwo = new Pet(2L, "doggo2", new Category(0,"stringu"),photoList1,new Tag(0L,"stringt"),"available");
        petList.add(petOne);
        petList.add(petTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPetDetails() throws Exception {
        when(petService.getPet(1L)).thenReturn(petOne);
        this.mockMvc.perform(get("/Pet/1" )).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllPetDetails() throws  Exception {
        when(petService.getAllPets()).thenReturn(petList);
        this.mockMvc.perform(get("/Pet/"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createPetDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(petOne);

        when(petService.createPet(petOne)).thenReturn("Success");
        this.mockMvc.perform(post("/Pet/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updatePetDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(petOne);

        when(petService.updatePet(petOne))
                .thenReturn("Pet Updated Successfully");
        this.mockMvc.perform(put("/Pet/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deletePetDetails() throws Exception {
        when(petService.deletePet(1L))
                .thenReturn("Pet Deleted Successfully");
        this.mockMvc.perform(delete("/Pet/" + "1"))
                .andDo(print()).andExpect(status().isOk());

    }
}

