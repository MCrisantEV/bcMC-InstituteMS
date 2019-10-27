package mc.bc.ms.institute.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mc.bc.ms.institute.app.models.Institute;
import mc.bc.ms.institute.app.services.InstituteService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/institutes")
public class InstituteController {

	@Autowired
	private InstituteService intServ;

	@PostMapping
	public Mono<Map<String, Object>> createInstitute(@RequestBody Institute institute) {

		return intServ.saveInstitute(institute);
	}

}
