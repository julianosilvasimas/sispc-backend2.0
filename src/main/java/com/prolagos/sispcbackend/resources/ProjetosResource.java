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

import com.prolagos.sispcbackend.domain.Cad_Projetos_Engenharia;
import com.prolagos.sispcbackend.domain.Cad_Projetos_FinanceiroAnos;
import com.prolagos.sispcbackend.domain.Cad_Projetos_Licenciamentos;
import com.prolagos.sispcbackend.domain.Cad_Projetos_Regulatorio;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Projetos;
import com.prolagos.sispcbackend.services.ProjEngenhariaService;
import com.prolagos.sispcbackend.services.ProjFinanceiroAnosService;
import com.prolagos.sispcbackend.services.ProjLicenciamentosService;
import com.prolagos.sispcbackend.services.ProjRegulatorioService;
import com.prolagos.sispcbackend.services.ProjetosService;

@RestController
@RequestMapping(value="/projetos")
public class ProjetosResource {
	
	@Autowired
	private ProjetosService service;
	
	@Autowired
	private ProjRegulatorioService regservice;
	
	@Autowired
	private ProjEngenhariaService engservice;
	
	@Autowired
	private ProjLicenciamentosService licenservice;
	
	@Autowired
	private ProjFinanceiroAnosService anoscapexservice;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cad_SisPC_Projetos> find(@PathVariable Integer id) {
		Cad_SisPC_Projetos obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(value="/{projetoId}/regulatorios", method=RequestMethod.GET)
	public ResponseEntity<List<Cad_Projetos_Regulatorio>> findByProjeto(@PathVariable Integer projetoId) {
		List<Cad_Projetos_Regulatorio> list = regservice.findByProjeto(projetoId);
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{projetoId}/anoscapex", method=RequestMethod.GET)
	public ResponseEntity<List<Cad_Projetos_FinanceiroAnos>> findByProjetoanoscapex(@PathVariable Integer projetoId) {
		List<Cad_Projetos_FinanceiroAnos> list = anoscapexservice.findByCapex(projetoId);
		
		return ResponseEntity.ok().body(list);
	}
	
	
	@RequestMapping(value="/{projetoId}/engenharia", method=RequestMethod.GET)
	public ResponseEntity<List<Cad_Projetos_Engenharia>> findByProjetoeng(@PathVariable Integer projetoId) {
		List<Cad_Projetos_Engenharia> list = engservice.findByProjeto(projetoId);
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{projetoId}/licenciamentos", method=RequestMethod.GET)
	public ResponseEntity<List<Cad_Projetos_Licenciamentos>> findByProjetolicen(@PathVariable Integer projetoId) {
		List<Cad_Projetos_Licenciamentos> list = licenservice.findByProjeto(projetoId);
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@RequestBody Cad_SisPC_Projetos obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getProjetoId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cad_SisPC_Projetos obj, @PathVariable Integer id) {
		obj.setProjetoId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cad_SisPC_Projetos>> findAll() {
		List<Cad_SisPC_Projetos> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Cad_SisPC_Projetos>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="precoId") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cad_SisPC_Projetos> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
