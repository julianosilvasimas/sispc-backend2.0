	package com.prolagos.sispcbackend.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prolagos.sispcbackend.domain.Apprpa_Energia_cadCenarios;
import com.prolagos.sispcbackend.dto.CenariosEnergiaDTO;
import com.prolagos.sispcbackend.services.CadCenariosEnergiaService;

@RestController
@RequestMapping(value="/cenariosenergia")
public class CadCenariosEnergiaResource {




	@Autowired
	private CadCenariosEnergiaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Apprpa_Energia_cadCenarios> find(@PathVariable Integer id) {
		Apprpa_Energia_cadCenarios obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	
	@RequestMapping(method=RequestMethod.POST)
	public Apprpa_Energia_cadCenarios insert(@RequestBody CenariosEnergiaDTO obj) {
		Apprpa_Energia_cadCenarios objComplete = new Apprpa_Energia_cadCenarios();
		objComplete.setId(null);
		objComplete.setImportadoParaIndicadores(obj.getImportadoParaIndicadores());
		objComplete.setNomeDoCenario(obj.getNomeDoCenario());
		objComplete.setDataReferencia(obj.getDataReferencia());
		objComplete.setUsuario(obj.getUsuario());
		objComplete.setAumento(obj.getAumento());
		objComplete.setClassificacao(obj.getClassificacao());
		objComplete.setTarifa(obj.getTarifa());
		objComplete = service.insert(objComplete);
		return objComplete;
	}


	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Apprpa_Energia_cadCenarios update(@RequestBody Apprpa_Energia_cadCenarios obj,@PathVariable Integer id) {
		Apprpa_Energia_cadCenarios objComplete = service.find(id);
		objComplete.setImportadoParaIndicadores(obj.getImportadoParaIndicadores());
		objComplete.setNomeDoCenario(obj.getNomeDoCenario());
		objComplete.setUsuario(obj.getUsuario());	
		objComplete.setAumento(obj.getAumento());
		objComplete.setClassificacao(obj.getClassificacao());
		objComplete.setTarifa(obj.getTarifa());
		objComplete = service.update(obj.getCenariosLinhas(),objComplete);	
		return objComplete;
	}
	


	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CenariosEnergiaDTO>> findAll() {
		List<Apprpa_Energia_cadCenarios> list = service.findAll();
		List<CenariosEnergiaDTO> listDto = list.stream().map(obj -> new CenariosEnergiaDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Apprpa_Energia_cadCenarios>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="exeindicadorId") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Apprpa_Energia_cadCenarios> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	

	@RequestMapping(value="/atualizarIndicadores/{idKw}/{idRS}/{Kwm3}/{RSm3}", method=RequestMethod.PUT)
	public Apprpa_Energia_cadCenarios updateIndicadores(@RequestBody Apprpa_Energia_cadCenarios obj,@PathVariable Integer idKw,@PathVariable Integer idRS,@PathVariable Integer Kwm3, @PathVariable Integer RSm3 ) {
		service.updateIndicador(obj, idKw, idRS, Kwm3, RSm3);
		return obj;
	}

  
}
