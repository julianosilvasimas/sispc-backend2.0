package com.prolagos.sispcbackend.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UsuarioNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter private Integer usuarioId;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	@Getter @Setter private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	@Getter @Setter private String email;
	@Getter @Setter private String login;
	@Getter @Setter private Boolean ativo;
	@Getter @Setter private String senha;
	@Getter @Setter private String gerenciaId;
	@Getter @Setter private String supervisaoId;
	

}
