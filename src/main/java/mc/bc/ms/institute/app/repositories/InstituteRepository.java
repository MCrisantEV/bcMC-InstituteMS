package mc.bc.ms.institute.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import mc.bc.ms.institute.app.models.Institute;

public interface InstituteRepository extends ReactiveMongoRepository<Institute, String> {

}
