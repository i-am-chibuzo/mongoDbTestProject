package africa.semicolon.mongoDbTestProject;

import africa.semicolon.mongoDbTestProject.data.repositories.OurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EmbeddedMongoSpringBootTest
class SpringBootSmokeTest {

    @Autowired
    private OurRepository ourRepository;

    @Test
    void contextLoadsWithEmbeddedMongo() {
        assertNotNull(ourRepository);
        ourRepository.count();
    }
}
