package com.prolagos.sispcbackend.bots;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import com.prolagos.sispcbackend.bots.Volumes.Volumes;
import com.prolagos.sispcbackend.domain.Appweb_Transporte_Agendamentos;
import com.prolagos.sispcbackend.resources.CadRpaResource;
import com.prolagos.sispcbackend.services.util.AgendamentoEmails;

import attdb.AttDB;


public class GerenciadorDeBots implements Callable<String>{
	
	public Volumes vol = new Volumes();
    boolean startbotVolumes=false;
    
    private String url;
    private String user;
    private String pass;

    
    public GerenciadorDeBots() {
    	
    }
    
    public GerenciadorDeBots(Integer Bot, String url, String user, String pass) {
    	this.url = url;
    	this.user = user;
    	this.pass = pass;
    	
    	
    	if(Bot==1){
    		this.startbotVolumes= startbotVolumes == false ? true : false;
    	}
    }

    @Override
    public String call() throws Exception {
    	String statusBotVolumes = "";
    	atualizandoVolumes();
		return statusBotVolumes;
    }
     
    CadRpaResource cad;
    @Async
    private void atualizandoVolumes() throws Exception{
    	if(startbotVolumes==true){
	    	vol.setStatus("{ \"status\": \"Executando\" }");
	    	AttDB att = new AttDB(url,user,pass);
	    	System.out.println("CONCLUIDO");
	    	vol.setStatus("{ \"status\": \"Pronto\" }");
			this.startbotVolumes=false;
	    	System.out.println(vol.getStatus());
			
    	}
    }

    
}