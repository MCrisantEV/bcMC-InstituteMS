package mc.bc.ms.institute.app.services;

import java.util.Map;

import mc.bc.ms.institute.app.models.Institute;
import reactor.core.publisher.Mono;

public interface InstituteService {

	public Mono<Map<String, Object>> saveInstitute(Institute institute);

}
