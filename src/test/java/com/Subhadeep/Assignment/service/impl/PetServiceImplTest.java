package com.Subhadeep.Assignment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Subhadeep.Assignment.model.Category;
import com.Subhadeep.Assignment.model.Pet;
import com.Subhadeep.Assignment.model.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.*;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.*;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PetServiceImpl.class)
@AutoConfigureWebClient(registerRestTemplate = true)
class PetServiceImplTest {
    private final String apiUrl = "https://petstore.swagger.io/v2/pet";

    @Autowired
    private PetServiceImpl petService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;
    Pet pet,pet1;
    String[] Photolist1={"StringU"};
    List<String> photoList1=Arrays.asList(Photolist1);
    List<Tag> tagL = new ArrayList<>();
    Tag tag1 =new Tag(0L,"stringT");

    Category cat1=new Category(0L,"string C");

    String json = """
                           {
                              "id": 1,
                              "category": {
                                "id": 0,
                                "name": "string"
                              },
                              "name": "doggie",
                              "photoUrls": [
                                "string"
                              ],
                              "tags": [
                                {
                                  "id": 0,
                                  "name": "string"
                                }
                              ],
                              "status": "available"
                            }
                """;

    @BeforeEach
    void setUp() {
        tagL.add(tag1);
        pet = new Pet(1L, "doggo", cat1,photoList1,tagL,"available");
        pet1 = new Pet(2L, "tom", cat1,photoList1,tagL,"available");
        this.mockRestServiceServer.reset();
    }

    @AfterEach
    void tearDown() throws Exception {
        this.mockRestServiceServer.verify();
    }

    @Test
    void testCreatePet() {
        String textCreate = """
                           {
                              "id": 1,
                              "category": {
                                "id": 0,
                                "name": "string"
                              },
                              "name": "doggo",
                              "photoUrls": [
                                "string"
                              ],
                              "tags": [
                                {
                                  "id": 0,
                                  "name": "string"
                                }
                              ],
                              "status": "available"
                            }
                """;
        this.mockRestServiceServer
                .expect(requestTo(apiUrl))
                .andRespond(withSuccess(textCreate, MediaType.APPLICATION_JSON));

        Pet result =petService.createPet(pet);
        assertEquals("Create","doggo",result.getName());
    }

    @Test
    void testUpdatePet() {
        String textUpd = """
                           {
                              "id": 1,
                              "category": {
                                "id": 0,
                                "name": "string"
                              },
                              "name": "tom",
                              "photoUrls": [
                                "string"
                              ],
                              "tags": [
                                {
                                  "id": 0,
                                  "name": "string"
                                }
                              ],
                              "status": "available"
                            }
                """;
        this.mockRestServiceServer
                .expect(requestTo(apiUrl))
                .andRespond(withSuccess(textUpd, MediaType.APPLICATION_JSON));

        Pet result =petService.updatePet(pet1);
        assertEquals("Update","tom",result.getName());
    }

    @Test
    void testDeletePet() {

        String text = """
                      {
                      "code": 200,
                "type": "unknown",
                "message": "1"
                            }
                """;
        this.mockRestServiceServer
                .expect(requestTo(apiUrl+"/1"))
                .andRespond(withSuccess(text, MediaType.APPLICATION_JSON));

        String result =petService.deletePet(1L);
        assertEquals("Delete",text,result);
    }

    @Test
    public void testGetPet() {

        this.mockRestServiceServer
                .expect(requestTo(apiUrl+"/1"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        Pet result =petService.getPet(1L);
        assertEquals("Name","doggie", result.getName());
    }

}