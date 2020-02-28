package com.prolagos.sispcbackend.resources;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import com.prolagos.sispcbackend.domain.Appweb_Juridico_pagamentos;
import com.prolagos.sispcbackend.domain.Cad_Transporte_Veiculos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import com.prolagos.sispcbackend.services.AgendamentosService;
import com.prolagos.sispcbackend.services.JuridicoPagamentoService;
import com.prolagos.sispcbackend.services.VeiculosService;
import com.prolagos.sispcbackend.services.util.AgendamentoEmails;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/pagamentoJuridico" })
public class JuridicoPagamentoResource {
	
	@Autowired
    private JuridicoPagamentoService service;
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET })
    public ResponseEntity<Appweb_Juridico_pagamentos> find(@PathVariable Integer id) {
        Appweb_Juridico_pagamentos obj = service.find(id);
        return (ResponseEntity<Appweb_Juridico_pagamentos>)ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = { "/editaveis" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Appweb_Juridico_pagamentos>> SemAprovar() {
        final List<Appweb_Juridico_pagamentos> list = this.service.SemAprovar();
        return (ResponseEntity<List<Appweb_Juridico_pagamentos>>)ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = { "/emaprovacao" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Appweb_Juridico_pagamentos>> Aprovacao() {
        final List<Appweb_Juridico_pagamentos> list = this.service.EmAprovacao();
        return (ResponseEntity<List<Appweb_Juridico_pagamentos>>)ResponseEntity.ok().body(list);
    }
        
    @RequestMapping(value = { "/aprovando/{nivel}/{string}" }, method = { RequestMethod.GET })
    public ResponseEntity<List<Appweb_Juridico_pagamentos>> emAprovacao(@PathVariable Integer nivel, @PathVariable String string) {
    	final List<String> dados = Arrays.asList(string.split(","));
        final List<Appweb_Juridico_pagamentos> list = this.service.Aprovacao(nivel,dados);
        return (ResponseEntity<List<Appweb_Juridico_pagamentos>>)ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public ResponseEntity<List<Appweb_Juridico_pagamentos>> findAll() {
        final List<Appweb_Juridico_pagamentos> list = this.service.findAll();
        return (ResponseEntity<List<Appweb_Juridico_pagamentos>>)ResponseEntity.ok().body(list);
    }
    
    @RequestMapping(method = { RequestMethod.POST })
    public ResponseEntity<Void> insert(@RequestBody Appweb_Juridico_pagamentos obj) throws EmailException, UnsupportedEncodingException {


        obj = this.service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(new Object[] { obj.getIdPagamento()}).toUri();
    	
    	return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
    public ResponseEntity<Void> update(@RequestBody Appweb_Juridico_pagamentos obj, @PathVariable final Integer id) throws UnsupportedEncodingException, EmailException {

        obj.setIdPagamento(id);
        obj = this.service.update(obj);
        
    	return ResponseEntity.noContent().build();
    }
    
    
    @RequestMapping(value = { "/{id}" }, method = { RequestMethod.DELETE })
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
