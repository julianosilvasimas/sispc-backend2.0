package com.prolagos.sispcbackend.domain.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class Objeto_EmailInformativoSisPC implements Serializable{
	@Getter @Setter private String Assunto;
	@Getter @Setter private String Texto;
	public Objeto_EmailInformativoSisPC(String assunto, String texto) {
		super();
		Assunto = assunto;
		Texto = texto;
	}
	
	
}
