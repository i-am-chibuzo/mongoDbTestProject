package africa.semicolon.mongoDbTestProject.data.repositories;


import africa.semicolon.mongoDbTestProject.data.models.OurModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
public class OurRepositoryTest {

    @Autowired
    private OurRepository ourRepository;

    @Test
    public void testSaveAndFindOurModel() {
        OurModel ourModel = new OurModel();
        ourModel.setName("Test Name");
        ourModel.setDescription("Test Description");

        ourRepository.save(ourModel);

        // Retrieve the model by ID
        Optional<OurModel> retrievedModel = ourRepository.findById(ourModel.getId());

        assertTrue(retrievedModel.isPresent());
        assertEquals("Test Name", retrievedModel.get().getName());
        assertEquals("Test Description", retrievedModel.get().getDescription());
        assertEquals(ourModel.getId(), retrievedModel.get().getId());
        assertEquals(1, ourRepository.count());
    }


}