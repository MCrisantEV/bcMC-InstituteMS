package mc.bc.ms.institute.app.services;

import java.util.Map;

import mc.bc.ms.institute.app.models.Institute;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InstituteService {

	public Mono<Map<String, Object>> saveInstitute(Institute institute);
	
	public Flux<Institute> finadAllInstitute();
	
	public Mono<Institute> findByNameInstitute(String name);
	
	public Mono<Map<String, Object>> updateInstitute(String id, Institute institute);
	
}
