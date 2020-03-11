package com.prolagos.sispcbackend.resources;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.apache.commons.mail.EmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prolagos.sispcbackend.domain.models.Objeto_EmailInformativoSisPC;
import com.prolagos.sispcbackend.services.util.NotificacoesSispcEmails;

@RestController
@RequestMapping({ "/notificacao" })
public class NotificacoesResource {
	

	@RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Objeto_EmailInformativoSisPC> find(@RequestBody  Objeto_EmailInformativoSisPC obj) throws UnsupportedEncodingException, ClassNotFoundException, EmailException, SQLException {
    	NotificacoesSispcEmails not = new NotificacoesSispcEmails(obj.getAssunto(), obj.getTexto());
        return (ResponseEntity<Objeto_EmailInformativoSisPC>)ResponseEntity.ok().body(obj);
    }

    

}
