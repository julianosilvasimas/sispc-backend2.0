package com.prolagos.sispcbackend.resources;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prolagos.sispcbackend.domain.Appweb_Notificacoes_Templates;
import com.prolagos.sispcbackend.domain.models.Objeto_EmailInformativoSisPC;
import com.prolagos.sispcbackend.services.NotificacoesTemplatesService;
import com.prolagos.sispcbackend.services.util.NotificacoesSispcEmails;

@RestController
@RequestMapping(value="/notificacao")
public class NotificacoesTemplatesResource {

	@RequestMapping(value="/enviarEmail", method=RequestMethod.POST)
    public ResponseEntity<Objeto_EmailInformativoSisPC> find(@RequestBody  Objeto_EmailInformativoSisPC obj) throws UnsupportedEncodingException, ClassNotFoundException, EmailException, SQLException {
    	NotificacoesSispcEmails not = new NotificacoesSispcEmails(obj.getUsuarios(), obj.getAssunto(), obj.getTexto());
        return (ResponseEntity<Objeto_EmailInformativoSisPC>)ResponseEntity.ok().body(obj);
    }
    
	@Autowired
	private NotificacoesTemplatesService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Appweb_Notificacoes_Templates> find(@PathVariable Integer id) {
		Appweb_Notificacoes_Templates obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@RequestBody Appweb_Notificacoes_Templates obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Appweb_Notificacoes_Templates obj, @PathVariable Integer id){
		if(id == 0) {
			obj = service.insert(obj);
		}else {
			obj.setId(id);
			obj = service.update(obj);
		}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Appweb_Notificacoes_Templates>> findAll() {
		List<Appweb_Notificacoes_Templates> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Appweb_Notificacoes_Templates>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="ordem") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Appweb_Notificacoes_Templates> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
