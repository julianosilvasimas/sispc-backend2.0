package com.prolagos.sispcbackend.resources;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.domain.Cad_Ind_Indicadores;
import com.prolagos.sispcbackend.domain.cad_rpa_robos;
import com.prolagos.sispcbackend.domain.AppWeb_Ind_ExeIndicadores;
import com.prolagos.sispcbackend.domain.Apprpa_Rpa_Statusbots;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.prolagos.sispcbackend.services.CondutoresService;
import com.prolagos.sispcbackend.services.GerenciasService;
import com.prolagos.sispcbackend.services.StatusBotsService;
import com.prolagos.sispcbackend.services.VeiculosService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/statusbot" })
public class StatusbotsResource {
	
	@Autowired
    private StatusBotsService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Apprpa_Rpa_Statusbots> find(@PathVariable Integer id) {
        Apprpa_Rpa_Statusbots obj = service.find(id);
        return (ResponseEntity<Apprpa_Rpa_Statusbots>)ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Rpa_Statusbots>> findAll() {
        final List<Apprpa_Rpa_Statusbots> list = this.service.findAll();
        return (ResponseEntity<List<Apprpa_Rpa_Statusbots>>)ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = { "/bot/{bot}" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Rpa_Statusbots>> findByBot(@PathVariable final Integer bot) {
    	List<Apprpa_Rpa_Statusbots> list  = service.findByBot(bot);
        return (ResponseEntity<List<Apprpa_Rpa_Statusbots>>)ResponseEntity.ok().body(list);
    }
    
    
    @RequestMapping(method = { RequestMethod.POST })
    public ResponseEntity<Void> insert(@RequestBody Apprpa_Rpa_Statusbots obj) {
        obj = this.service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/{id}").buildAndExpand(new Object[] { obj.getIdAppWeb()}).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Apprpa_Rpa_Statusbots obj, @PathVariable final Integer id) {
        obj.setIdAppWeb(id);
        obj = this.service.update(obj);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Apprpa_Rpa_Statusbots>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="1000") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="idAppWeb") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Apprpa_Rpa_Statusbots> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
