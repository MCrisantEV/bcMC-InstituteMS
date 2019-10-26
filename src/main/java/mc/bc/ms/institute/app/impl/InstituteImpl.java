package mc.bc.ms.institute.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.bc.ms.institute.app.repositories.InstituteRepository;
import mc.bc.ms.institute.app.services.InstituteService;

@Service
public class InstituteImpl implements InstituteService{
	
	@Autowired
	private InstituteRepository intRep;

}
