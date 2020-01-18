package com.prolagos.sispcbackend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prolagos.sispcbackend.domain.Base_GSS_EndEntregas;
import com.prolagos.sispcbackend.services.EntregaService;

@RestController
@RequestMapping(value="/endentrega")
public class EntregaResource {
	
	@Autowired
	private EntregaService service;
	
	@RequestMapping(value="/{matricula}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer matricula) {
		Base_GSS_EndEntregas obj = service.find(matricula);
		return ResponseEntity.ok().body(obj);
	}

}
