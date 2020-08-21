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

import com.prolagos.sispcbackend.domain.Cad_SisPC_PartesInteressadas;
import com.prolagos.sispcbackend.services.PartesInteressadasService;

@RestController
@RequestMapping(value="/partesinteressadas")
public class PartesInteressadasResource {
	
	@Autowired
    private PartesInteressadasService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Cad_SisPC_PartesInteressadas> find(@PathVariable Integer id) {
        Cad_SisPC_PartesInteressadas obj = service.find(id);
        return (ResponseEntity<Cad_SisPC_PartesInteressadas>)ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Cad_SisPC_PartesInteressadas>> findAll() {
        final List<Cad_SisPC_PartesInteressadas> list = this.service.findAll();
        return (ResponseEntity<List<Cad_SisPC_PartesInteressadas>>)ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(method = { RequestMethod.POST })
    public ResponseEntity<Void> insert(@RequestBody Cad_SisPC_PartesInteressadas obj) {
        obj = this.service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/{id}").buildAndExpand(new Object[] { obj.getOrgaoId() }).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Cad_SisPC_PartesInteressadas obj, @PathVariable final Integer id) {
        obj.setOrgaoId(id);
        obj = this.service.update(obj);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
