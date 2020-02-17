package com.prolagos.sispcbackend.resources;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.SQLException;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.domain.Cad_Transporte_Veiculos;
import com.prolagos.sispcbackend.domain.models.Objeto_EmailInformativoSisPC;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import com.prolagos.sispcbackend.services.AgendamentosService;
import com.prolagos.sispcbackend.services.VeiculosService;
import com.prolagos.sispcbackend.services.util.AgendamentoEmails;
import com.prolagos.sispcbackend.services.util.NotificacoesSispcEmails;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/enviarEmail" })
public class EnvioDeEmailsNotificacoes {
	


    @RequestMapping(method = { RequestMethod.POST })
    public void insert(@RequestBody Objeto_EmailInformativoSisPC obj) throws EmailException, UnsupportedEncodingException, ClassNotFoundException, SQLException {
    	System.out.println(
			obj.getAssunto()+
			obj.getTexto()
		);
    	NotificacoesSispcEmails email = new NotificacoesSispcEmails(
			obj.getAssunto(),
			obj.getTexto()
		);
    }
}
