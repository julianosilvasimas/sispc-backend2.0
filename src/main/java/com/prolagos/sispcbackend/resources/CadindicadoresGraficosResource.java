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

import com.prolagos.sispcbackend.domain.Cad_Ind_Graficos;
import com.prolagos.sispcbackend.services.CadindicadoresGraficosService;
import com.prolagos.sispcbackend.services.CadindicadoresService;

@RestController
@RequestMapping(value="/cadindicadoresgraficos")
public class CadindicadoresGraficosResource {
	
	@Autowired
	private CadindicadoresGraficosService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cad_Ind_Graficos> find(@PathVariable Integer id) {
		Cad_Ind_Graficos obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@RequestBody Cad_Ind_Graficos obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getCampoDoGraficoId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cad_Ind_Graficos obj, @PathVariable Integer id) {

		if(id == 0) {
			obj = service.insert(obj);
		}else {
			obj.setCampoDoGraficoId(id);
			obj = service.update(obj);
		}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cad_Ind_Graficos>> findAll() {
		List<Cad_Ind_Graficos> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Cad_Ind_Graficos>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="ordem") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cad_Ind_Graficos> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
