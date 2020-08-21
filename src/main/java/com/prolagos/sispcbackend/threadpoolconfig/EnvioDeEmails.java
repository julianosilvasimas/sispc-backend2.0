package com.prolagos.sispcbackend.threadpoolconfig;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.apache.commons.mail.EmailException;
import org.springframework.scheduling.annotation.Async;


import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.services.util.AgendamentoEmails;


public class EnvioDeEmails implements Callable<String>{
 
    String name;
    ArrayList<Appweb_Transporte_Agendamentos> agendamentos = new ArrayList<>();
    
    public EnvioDeEmails(Appweb_Transporte_Agendamentos obj) {
    	agendamentos.add(obj);
    }

     
    @Override
    public String call() throws Exception {
    	
        agendamentos();
		return name;
    }
     
    @Async
    private void agendamentos() throws InterruptedException, UnsupportedEncodingException, EmailException{ 
    	if(agendamentos.size()>0) {
			Appweb_Transporte_Agendamentos obj = agendamentos.get(0);
			System.out.println("Startando teste para o agendamento = "+obj.getAgendamentoId());
			AgendamentoEmails email = new AgendamentoEmails(obj.getAprovacao(), obj.getEmailsolicitante(), obj.getAgendamentoId(), obj.getCondutor(), obj.getDestino(), obj.getSolicitante(), obj.getTipoVeiculoSolicitado(), obj.getAgendadode(), obj.getAgendadoate(), obj.getJustificativasolicitacao(), obj.getAprovador(), obj.getJustificativa(), obj.getPlaca(), obj.getTipoVeiculoDisponibilizado(),"");
			agendamentos.remove(0);
			System.out.println("Finalizado para o agendamento = "+obj.getAgendamentoId());
    	}
    }

    
}