package com.Subhadeep.Assignment.repository;

import com.Subhadeep.Assignment.model.Category;
import com.Subhadeep.Assignment.model.Pet;
import com.Subhadeep.Assignment.model.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class PetRepositoryTest {

    //  given - when - then

    @Autowired
    private PetRepository petRepository;
    Pet pet;
    String[] Photolist1={"StringU"};
    List<String> photoList1= Arrays.asList(Photolist1);

    @BeforeEach
    void setUp() {
        pet = new Pet(0L, "doggo", new Category(0, "stringc"),photoList1,new Tag(0L,"stringt"),"available");
        petRepository.save(pet);
    }

    @AfterEach
    void tearDown() {
        pet = null;
        petRepository.deleteAll();
    }

    // Test case SUCCESS

    @Test
    void testFindByName_Found()
    {
        List<Pet> PetList = petRepository.findByName("doggo");
        assertThat(PetList.get(0).getId()).isEqualTo(pet.getId());
        assertThat(PetList.get(0).getCategory().getCategoryName()).isEqualTo(pet.getCategory().getCategoryName());
    }

    // Test case FAILURE
    @Test
    void testFindByName_NotFound()
    {
        List<Pet> PetList = petRepository.findByName("dog");
        assertThat(PetList.isEmpty()).isTrue();
    }
}
