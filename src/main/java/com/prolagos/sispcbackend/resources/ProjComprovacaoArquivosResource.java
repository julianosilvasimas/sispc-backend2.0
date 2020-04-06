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

import com.prolagos.sispcbackend.domain.Cad_Projetos_ComprovacaoArquivos;
import com.prolagos.sispcbackend.services.ProjComprovacaoArquivosService;

@RestController
@RequestMapping({ "/comprovacaoarquivos" })
public class ProjComprovacaoArquivosResource {

	@Autowired
    private ProjComprovacaoArquivosService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Cad_Projetos_ComprovacaoArquivos> find(@PathVariable Integer id) {
    	Cad_Projetos_ComprovacaoArquivos obj = service.find(id);
        return (ResponseEntity<Cad_Projetos_ComprovacaoArquivos>)ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Cad_Projetos_ComprovacaoArquivos>> findAll() {
        final List<Cad_Projetos_ComprovacaoArquivos> list = this.service.findAll();
        return (ResponseEntity<List<Cad_Projetos_ComprovacaoArquivos>>)ResponseEntity.ok().body(list);
    }
    
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Cad_Projetos_ComprovacaoArquivos obj) {
	obj = service.insert(obj);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(obj.getComprovacaoarquivoId()).toUri();
	return ResponseEntity.created(uri).build();
}
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Cad_Projetos_ComprovacaoArquivos obj, @PathVariable final Integer id) {
        obj.setComprovacaoarquivoId(id);
        obj = this.service.update(obj);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
	
}
