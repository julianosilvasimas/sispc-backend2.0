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

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Ocorrencias;
import com.prolagos.sispcbackend.services.ApprpaEsgotoOcorrenciasService;

@RestController
@RequestMapping({ "/appesgotoocorrencias" })
public class ApprpaEsgotoOcorrenciasResource {
	
	@Autowired
    private ApprpaEsgotoOcorrenciasService service;
    
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Apprpa_Esgoto_Ocorrencias> find(@PathVariable Integer id) {
		Apprpa_Esgoto_Ocorrencias obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@RequestBody Apprpa_Esgoto_Ocorrencias obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Apprpa_Esgoto_Ocorrencias obj, @PathVariable Integer id) {

		if(id == 0) {
			obj = service.insert(obj);
		}else {
			obj.setId(id);
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
	public ResponseEntity<List<Apprpa_Esgoto_Ocorrencias>> findAll() {
		List<Apprpa_Esgoto_Ocorrencias> list = this.service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/findlimit", method=RequestMethod.GET)
	public ResponseEntity<List<Apprpa_Esgoto_Ocorrencias>> findlimit() {
		List<Apprpa_Esgoto_Ocorrencias> list = this.service.findLimit();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Apprpa_Esgoto_Ocorrencias>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="100") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="ordem") String orderBy, 
			@RequestParam(value="direction", defaultValue="desc") String direction) {
		Page<Apprpa_Esgoto_Ocorrencias> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}


}
