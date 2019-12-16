package com.prolagos.sispcbackend.resources;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Gerencias;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.prolagos.sispcbackend.services.GerenciasService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/gerencias" })
public class GerenciasResource {
	
	@Autowired
    private GerenciasService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Cad_SisPC_Gerencias> find(@PathVariable Integer id) {
        Cad_SisPC_Gerencias obj = service.find(id);
        return (ResponseEntity<Cad_SisPC_Gerencias>)ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Cad_SisPC_Gerencias>> findAll() {
        final List<Cad_SisPC_Gerencias> list = this.service.findAll();
        return (ResponseEntity<List<Cad_SisPC_Gerencias>>)ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(method = { RequestMethod.POST })
    public ResponseEntity<Void> insert(@RequestBody Cad_SisPC_Gerencias obj) {
        obj = this.service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/{id}").buildAndExpand(new Object[] { obj.getGerenciaId() }).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Cad_SisPC_Gerencias obj, @PathVariable final Integer id) {
        obj.setGerenciaId(id);
        obj = this.service.update(obj);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
