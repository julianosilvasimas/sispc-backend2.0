package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@AllArgsConstructor  
@EqualsAndHashCode(of = "matricula")
public class Cad_Transporte_CondutoresTicketLog implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Getter @Setter private Integer matricula;
	@Getter @Setter private String Nome;
	@Getter @Setter private String Gestor;
	@Column(name = "VencimentoHabilitacao", nullable = false, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String VencimentoHabilitacao;
	@Column(name = "Nascimento", nullable = false, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String Nascimento;
	@Getter @Setter private String RG;
	@Getter @Setter private String CPF;
	@Getter @Setter private String Situacao;
	@Column(name = "dataCadastro", nullable = true, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String dataCadastro;

}
