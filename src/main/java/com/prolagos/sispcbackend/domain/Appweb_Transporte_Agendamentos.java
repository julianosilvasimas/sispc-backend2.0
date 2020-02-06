package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
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
	@Getter @Setter private String  Condutor;
	@Getter @Setter private Integer QtdPessoas;
	@Getter @Setter private String  TipoVeiculoSolicitado;
	@Getter @Setter private String  Destino;
	@Getter @Setter private String  Placa;
	@Getter @Setter private String  TipoVeiculoDisponibilizado;
	@Getter @Setter private String  Aprovador;
	@Getter @Setter private Integer Aprovacao;
	@Getter @Setter private String  Justificativa;
	
	public Appweb_Transporte_Agendamentos(Integer agendamentoId, String dataAgendamento, String agendadode,
			String agendadoate, String solicitante, String condutor, Integer qtdPessoas, String tipoVeiculo,String destino, String placa, String tipodisponibilizado,
			String aprovador,  Integer aprovacao, String justificativa) {
		super();
		
		this.agendamentoId = agendamentoId;
		DataAgendamento = dataAgendamento;
		Agendadode = agendadode;
		Agendadoate = agendadoate;
		Solicitante = solicitante;
		Condutor = condutor;
		QtdPessoas = qtdPessoas;
		TipoVeiculoSolicitado = tipoVeiculo;
		Destino = destino;
		Placa = placa;
		TipoVeiculoDisponibilizado = tipodisponibilizado;
		Aprovador = aprovador;
		Aprovacao = aprovacao;
		Justificativa = justificativa;
	}
}
