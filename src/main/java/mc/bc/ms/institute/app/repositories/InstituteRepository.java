package mc.bc.ms.institute.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import mc.bc.ms.institute.app.models.Institute;
import reactor.core.publisher.Mono;

public interface InstituteRepository extends ReactiveMongoRepository<Institute, String> {

	public Mono<Institute> findByInstitute(String institute);

}
