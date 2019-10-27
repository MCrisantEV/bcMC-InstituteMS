package mc.bc.ms.institute.app.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import mc.bc.ms.institute.app.models.Institute;
import mc.bc.ms.institute.app.repositories.InstituteRepository;
import mc.bc.ms.institute.app.services.InstituteService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InstituteImpl implements InstituteService {

	@Autowired
	private InstituteRepository intRep;

	@Autowired
	private Validator validator;

	@Override
	public Mono<Map<String, Object>> saveInstitute(Institute institute) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Mono<Institute> monoInst = Mono.just(institute);

		return monoInst.flatMap(ins -> {

			Errors errors = new BeanPropertyBindingResult(ins, Institute.class.getName());
			validator.validate(ins, errors);

			if (errors.hasErrors()) {
				return Flux.fromIterable(errors.getFieldErrors()).map(err -> {
					String[] matriz = { err.getField(), err.getDefaultMessage() };
					return matriz;
				}).collectList().flatMap(l -> {
					respuesta.put("status", HttpStatus.BAD_REQUEST.value());
					respuesta.put("Mensaje", "Error, revise los datos");
					l.forEach(m -> {
						for (int i = 0; i < m.length; i++) {
							respuesta.put(m[0], m[i]);
						}
					});
					return Mono.just(respuesta);
				});
			} else {
				return intRep.findByInstitute(ins.getInstitute()).map(i -> {
					respuesta.put("Error: ", ins.getInstitute() + " Ya esta registrado");
					return respuesta;
				}).switchIfEmpty(intRep.save(institute).map(instDb -> {
					respuesta.put("Instituto: ", instDb.getInstitute());
					respuesta.put("Mensaje: ", "Instituto creado con éxito");
					return respuesta;
				}));
			}
		});

	}

	@Override
	public Flux<Institute> finadAllInstitute() {
		return intRep.findAll();
	}

	@Override
	public Mono<Institute> findByNameInstitute(String name) {
		return intRep.findByInstitute(name);
	}

	@Override
	public Mono<Map<String, Object>> updateInstitute(String id, Institute institute) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Mono<Institute> monoInst = Mono.just(institute);

		return monoInst.flatMap(ins -> {

			Errors errors = new BeanPropertyBindingResult(ins, Institute.class.getName());
			validator.validate(ins, errors);

			if (errors.hasErrors()) {
				return Flux.fromIterable(errors.getFieldErrors()).map(err -> {
					String[] matriz = { err.getField(), err.getDefaultMessage() };
					return matriz;
				}).collectList().flatMap(l -> {
					respuesta.put("status", HttpStatus.BAD_REQUEST.value());
					respuesta.put("Mensaje", "Error, revise los datos");
					l.forEach(m -> {
						for (int i = 0; i < m.length; i++) {
							respuesta.put(m[0], m[i]);
						}
					});
					return Mono.just(respuesta);
				});
			} else {
				return intRep.findById(id).map(instDb -> {
					ins.setId(id);
					intRep.save(ins).subscribe();
					respuesta.put("Mensaje: ", ins.getInstitute() + " se actualizo con éxito");
					return respuesta;
				}).switchIfEmpty(Mono.just(institute).map(er -> {
					respuesta.put("Error: ", er.getInstitute() + " No se puede actualizar");
					return respuesta;
				}));
			}
		});
	}

	@Override
	public Mono<Map<String, Object>> deleteInstitute(String id) {
		Map<String, Object> respuesta = new HashMap<String, Object>();

		return intRep.findById(id).map(instDb -> {
			intRep.delete(instDb).subscribe();
			respuesta.put("Mensaje: ", instDb.getInstitute() + " se eliminó con éxito");
			return respuesta;
		}).switchIfEmpty(Mono.just("").map(er -> {
			respuesta.put("Mensaje: ", "El Instituto no se pudo elimininar");
			respuesta.put("Status", HttpStatus.BAD_REQUEST.value());
			respuesta.put("Error", "Problemas con ID");
			return respuesta;
		}));

	}

}
