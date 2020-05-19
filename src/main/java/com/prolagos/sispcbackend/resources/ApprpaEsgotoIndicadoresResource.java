package com.prolagos.sispcbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Unidades;
import com.prolagos.sispcbackend.services.ApprpaEsgotoIndicadoresService;
import com.prolagos.sispcbackend.services.ApprpaEsgotoUnidadesService;

@RestController
@RequestMapping(value="/appindicadoresesgoto")
public class ApprpaEsgotoIndicadoresResource {
	
	@Autowired
	private ApprpaEsgotoIndicadoresService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Apprpa_Esgoto_Indicadores> find(@PathVariable Integer id) {
		Apprpa_Esgoto_Indicadores obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@RequestBody Apprpa_Esgoto_Indicadores obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Apprpa_Esgoto_Indicadores obj, @PathVariable Integer id) {

    	if(id == 0) {
			obj.setId(null);
			obj.setDataDaCriacao(null);;
    		obj = service.insert(obj);
    		
    	}else {
			obj.setId(id);
			obj = service.update(obj,id);
    	}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Apprpa_Esgoto_Indicadores>> findAll() {
		List<Apprpa_Esgoto_Indicadores> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}



	
	@RequestMapping(value="/porunidade/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Apprpa_Esgoto_Indicadores>> findAllUnidade(@PathVariable Integer id) {
		List<Apprpa_Esgoto_Indicadores> list = service.findByUnidade(id);
		return ResponseEntity.ok().body(list);
	}
	
	

	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Apprpa_Esgoto_Indicadores>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="ordem") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Apprpa_Esgoto_Indicadores> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
