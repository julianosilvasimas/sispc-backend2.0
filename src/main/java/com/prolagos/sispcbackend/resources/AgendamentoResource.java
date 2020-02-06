package com.prolagos.sispcbackend.resources;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.domain.Cad_Transporte_Veiculos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.prolagos.sispcbackend.services.AgendamentosService;
import com.prolagos.sispcbackend.services.VeiculosService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/agendamento" })
public class AgendamentoResource {
	
	@Autowired
    private AgendamentosService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Appweb_Transporte_Agendamentos> find(@PathVariable Integer id) {
        Appweb_Transporte_Agendamentos obj = service.find(id);
        return (ResponseEntity<Appweb_Transporte_Agendamentos>)ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = { "/aprovar" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Appweb_Transporte_Agendamentos>> Aprovar() {
    	List<Appweb_Transporte_Agendamentos> list  = (List<Appweb_Transporte_Agendamentos>) service.paraAprovar();
        return (ResponseEntity<List<Appweb_Transporte_Agendamentos>>)ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = { "/aprovados" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Appweb_Transporte_Agendamentos>> Aprovados() {
    	List<Appweb_Transporte_Agendamentos> list  = (List<Appweb_Transporte_Agendamentos>) service.Aprovados();
        return (ResponseEntity<List<Appweb_Transporte_Agendamentos>>)ResponseEntity.ok().body(list);
    }
    

    @RequestMapping(value = { "/disponiveis/{de}/{ate}" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Appweb_Transporte_Agendamentos>> Disponiveis(@PathVariable String de, @PathVariable String ate) {
    	List<Appweb_Transporte_Agendamentos> list  = service.Disponiveis(de, ate);
        return (ResponseEntity<List<Appweb_Transporte_Agendamentos>>)ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Appweb_Transporte_Agendamentos>> findAll() {
        final List<Appweb_Transporte_Agendamentos> list = this.service.findAll();
        return (ResponseEntity<List<Appweb_Transporte_Agendamentos>>)ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(method = { RequestMethod.POST })
    public ResponseEntity<Void> insert(@RequestBody Appweb_Transporte_Agendamentos obj) {
        obj = this.service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/{id}").buildAndExpand(new Object[] { obj.getAgendamentoId()}).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Appweb_Transporte_Agendamentos obj, @PathVariable final Integer id) {
        obj.setAgendamentoId(id);
        obj = this.service.update(obj);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
