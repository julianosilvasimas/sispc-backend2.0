package com.prolagos.sispcbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prolagos.sispcbackend.domain.AppWeb_ProcessAdm_DelibFraudes;
import com.prolagos.sispcbackend.dto.InputDeliberacaoDTO;
import com.prolagos.sispcbackend.services.GestaoDeliberacaoService;

@RestController
@RequestMapping(value="/gestaodeliberacao")
public class GestaoDeliberacaoResources {

	@Autowired
	private GestaoDeliberacaoService service;
	@SuppressWarnings("unused")
	private AppWeb_ProcessAdm_DelibFraudes obj;
	
	public void setObj(AppWeb_ProcessAdm_DelibFraudes obj) {
		this.obj = obj;
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody InputDeliberacaoDTO objDTO) {
	setObj(service.fromDTO(objDTO));
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(objDTO.getProcesso()).toUri();
	return ResponseEntity.created(uri).build();
}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<AppWeb_ProcessAdm_DelibFraudes>> findAll() {
		List<AppWeb_ProcessAdm_DelibFraudes> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{processo}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable String processo) {
		AppWeb_ProcessAdm_DelibFraudes obj = service.find(processo);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/irreg/{irreg}", method=RequestMethod.GET)
	public ResponseEntity<List<AppWeb_ProcessAdm_DelibFraudes>> findIrreg(@PathVariable Integer irreg) {
		List<AppWeb_ProcessAdm_DelibFraudes> list = service.findIrreg(irreg); 
		return ResponseEntity.ok().body(list);
	}
	
}
