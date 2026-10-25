package africa.semicolon.mongoDbTestProject.data.repositories;


import africa.semicolon.mongoDbTestProject.data.models.OurModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OurRepository extends MongoRepository<OurModel, String> {

}
