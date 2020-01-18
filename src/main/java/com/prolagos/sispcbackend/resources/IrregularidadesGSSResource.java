package com.prolagos.sispcbackend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prolagos.sispcbackend.domain.Base_GSS_Irregularidades;
import com.prolagos.sispcbackend.services.IrregularidadesGSSService;

@RestController
@RequestMapping(value="/birregularidades")
public class IrregularidadesGSSResource {


	@Autowired
	private IrregularidadesGSSService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Base_GSS_Irregularidades>> findAll() {
		List<Base_GSS_Irregularidades> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{idIrregularidade}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer idIrregularidade) {
		Base_GSS_Irregularidades obj = service.find(idIrregularidade);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/termo/{termo}", method=RequestMethod.GET)
	public ResponseEntity<List<Base_GSS_Irregularidades>> findTermo(@PathVariable String termo) {
		List<Base_GSS_Irregularidades> list = service.findTermo(termo); 
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/termo/{termo}/status/{status}", method=RequestMethod.GET)
	public ResponseEntity<List<Base_GSS_Irregularidades>> findTermoStatus(@PathVariable String termo, @PathVariable String status) {
		List<Base_GSS_Irregularidades> list = service.findTermoStatus(termo, status); 
		return ResponseEntity.ok().body(list);
	}

	
}
