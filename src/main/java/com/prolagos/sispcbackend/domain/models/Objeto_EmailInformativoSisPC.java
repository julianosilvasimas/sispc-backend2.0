package com.prolagos.sispcbackend.domain.models;

import java.io.Serializable;

import com.prolagos.sispcbackend.domain.cad_rpa_robos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor @AllArgsConstructor
public class Objeto_EmailInformativoSisPC implements Serializable{
	@Getter @Setter private String Assunto;
	@Getter @Setter private String Texto;
	
	
	
}
