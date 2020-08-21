package com.prolagos.sispcbackend.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import com.prolagos.sispcbackend.domain.Cad_SisPC_Gerencias;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Supervisoes;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Unidades;
import com.prolagos.sispcbackend.domain.Cad_SisPC_Usuarios;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UsuarioDTO2 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private Integer usuarioId;
	@Getter @Setter private String nome;
	@Getter @Setter private String email;
	
	
	

	public UsuarioDTO2(Cad_SisPC_Usuarios obj) {
		
		usuarioId = obj.getUsuarioId();
		nome = obj.getNome();
		email = obj.getEmail();
		
	}
	
}
