package mc.bc.ms.institute.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mc.bc.ms.institute.app.services.InstituteService;


@RestController
@RequestMapping("/institutes")
public class InstituteController {
	
	@Autowired
	private InstituteService intServ;

}
