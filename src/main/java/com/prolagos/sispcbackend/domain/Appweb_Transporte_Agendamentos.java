package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "agendamentoId")
public class Appweb_Transporte_Agendamentos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter private Integer agendamentoId;
	@Column(name = "DataAgendamento", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String DataAgendamento;

	@Column(name = "Agendadode", nullable = false, updatable = true, insertable = true, columnDefinition = "TIMESTAMP")
	@Getter @Setter private String Agendadode;

	@Column(name = "Agendadoate", nullable = false, updatable = true, insertable = true, columnDefinition = "TIMESTAMP")
	@Getter @Setter private String Agendadoate;

	@Getter @Setter private String  Solicitante;
	@Getter @Setter private Integer fksolicitante;
	@Getter @Setter private String  emailsolicitante;
	@Getter @Setter private String  Condutor;
	@Getter @Setter private Integer QtdPessoas;
	@Getter @Setter private String  TipoVeiculoSolicitado;
	@Getter @Setter private String  Destino;
	@Getter @Setter private String  Placa;
	@Getter @Setter private String  TipoVeiculoDisponibilizado;
	@Getter @Setter private String  Aprovador;
	@Getter @Setter private String  emailaprovador;
	@Getter @Setter private Integer Aprovacao;
	@Getter @Setter private String  Justificativa;
	@Getter @Setter private boolean Emergencial;
	@Getter @Setter private String  justificativasolicitacao;
	
	
		
}
