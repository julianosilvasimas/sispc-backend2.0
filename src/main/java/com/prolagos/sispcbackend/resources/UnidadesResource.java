package com.prolagos.sispcbackend.resources;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Unidades;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.prolagos.sispcbackend.services.SupervisoesService;
import com.prolagos.sispcbackend.services.UnidadesService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/unidades" })
public class UnidadesResource {
	
	@Autowired
    private UnidadesService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Cad_SisPC_Unidades> find(@PathVariable Integer id) {
        Cad_SisPC_Unidades obj = service.find(id);
        return (ResponseEntity<Cad_SisPC_Unidades>)ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Cad_SisPC_Unidades>> findAll() {
        final List<Cad_SisPC_Unidades> list = this.service.findAll();
        return (ResponseEntity<List<Cad_SisPC_Unidades>>)ResponseEntity.ok().body(list);
    }
    

}
