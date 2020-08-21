package com.prolagos.sispcbackend.resources;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Classificacoes;
import com.prolagos.sispcbackend.domain.Apprpa_Esgoto_Indicadores;
import com.prolagos.sispcbackend.services.ApprpaEsgotoClassificacaoService;
import com.prolagos.sispcbackend.services.ApprpaEsgotoIndicadoresService;

@RestController
@RequestMapping({ "/appclassificacoesesgoto" })
public class ApprpaEsgotoClassificacaoResource {
	
	@Autowired
    private ApprpaEsgotoClassificacaoService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Apprpa_Esgoto_Classificacoes> find(@PathVariable Integer id) {
        Apprpa_Esgoto_Classificacoes obj = service.find(id);
        return (ResponseEntity<Apprpa_Esgoto_Classificacoes>)ResponseEntity.ok().body(obj);
    }
    

	@Autowired
    private ApprpaEsgotoIndicadoresService service2;

    @RequestMapping(value = { "/porclassficacao/{clas}" },method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Esgoto_Indicadores>> findUnidadesPorClassificacao( @PathVariable Integer clas) {
        final  List<Apprpa_Esgoto_Indicadores> ind = service2.findByClass(clas); 
        return (ResponseEntity<List<Apprpa_Esgoto_Indicadores>>)ResponseEntity.ok().body(ind);
    }
//    
//    @RequestMapping(value = { "/unidades/{unidade}/{de}/{ate}/{clas}" },method = { RequestMethod.GET })
//    public ResponseEntity<List<Apprpa_Esgoto_Classificacoes>> findByUnidadesDeAte(@PathVariable final String unidade, @PathVariable final String de, @PathVariable final String ate, @PathVariable final Integer clas) {
//        final List<Apprpa_Esgoto_Classificacoes> list = this.service.findUnidadeRef(unidade, de, ate, clas);
//        return (ResponseEntity<List<Apprpa_Esgoto_Classificacoes>>)ResponseEntity.ok().body(list);
//    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Apprpa_Esgoto_Classificacoes>> findAll() {
        final List<Apprpa_Esgoto_Classificacoes> list = this.service.findAll();
        return (ResponseEntity<List<Apprpa_Esgoto_Classificacoes>>)ResponseEntity.ok().body(list);
    }

    
    @RequestMapping( method = { RequestMethod.POST })
    public ResponseEntity<Void> insert(@RequestBody Apprpa_Esgoto_Classificacoes obj) throws UnsupportedEncodingException, EmailException {
        obj.setId(null);
        obj = this.service.insert(obj);
    	
    	return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Apprpa_Esgoto_Classificacoes obj, @PathVariable final Integer id) throws UnsupportedEncodingException, EmailException {
    	
    	if(id==0) {
    		obj.setId(null);
    		obj = this.service.insert(obj);
    	}else {
	        obj.setId(id);
	        obj = this.service.update(obj,id);
    	}
    	return ResponseEntity.noContent().build();

    }
    
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    

}
