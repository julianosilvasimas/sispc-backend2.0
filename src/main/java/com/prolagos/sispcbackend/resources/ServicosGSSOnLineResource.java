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

import com.prolagos.sispcbackend.domain.Base_GSSOnLine_ServicosTMA;
import com.prolagos.sispcbackend.services.ServicosGSSOnLineService;

@RestController
@RequestMapping({ "/servgssonline" })
public class ServicosGSSOnLineResource {
	
	@Autowired
    private ServicosGSSOnLineService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Base_GSSOnLine_ServicosTMA> find(@PathVariable Integer id) {
        Base_GSSOnLine_ServicosTMA obj = service.find(id);
        return (ResponseEntity<Base_GSSOnLine_ServicosTMA>)ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Base_GSSOnLine_ServicosTMA>> findAll() {
        final List<Base_GSSOnLine_ServicosTMA> list = this.service.findAll();
        return (ResponseEntity<List<Base_GSSOnLine_ServicosTMA>>)ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(method = { RequestMethod.POST })
    public ResponseEntity<Void> insert(@RequestBody Base_GSSOnLine_ServicosTMA obj) {
        obj = this.service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/{id}").buildAndExpand(new Object[] { obj.getIdosp() }).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Base_GSSOnLine_ServicosTMA obj, @PathVariable final Integer id) {
        obj.setIdosp(id);
        obj = this.service.update(obj);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
