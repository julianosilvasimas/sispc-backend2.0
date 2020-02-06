package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.sql.Date;

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
@NoArgsConstructor 
@AllArgsConstructor 
@EqualsAndHashCode(of = "veiculoId")
public class Cad_Transporte_Veiculos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter private Integer veiculoId;

	@Column(name = "datCad", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP")
	@Getter @Setter private String datCad;
	@Getter @Setter private String placa;
	@Getter @Setter private String chassi;
	@Getter @Setter private String modelo;
	@Getter @Setter private String capacidadem3;
	@Getter @Setter private String responsavel;
	@Getter @Setter private String tipoVeiculo;
	@Getter @Setter private String Locadora;
	@Getter @Setter private String Gerencia;
	@Getter @Setter private String Supervisao;
	@Getter @Setter private boolean GPS;
	@Getter @Setter private boolean Oficina;
	@Getter @Setter private boolean Pool;
	@Getter @Setter private boolean Devolvido;
	
//	public Cad_Transporte_Veiculos(Integer veiculoId, String datCad, String placa, String chassi, String modelo,
//			String capacidadem3, String responsavel, String tipoVeiculo, String locadora, String gerencia,
//			String supervisao, boolean gPS, boolean oficina, boolean pool, boolean devolvido) {
//		super();
//		this.veiculoId = veiculoId;
//		this.datCad = datCad;
//		this.placa = placa;
//		this.chassi = chassi;
//		this.modelo = modelo;
//		this.capacidadem3 = capacidadem3;
//		this.responsavel = responsavel;
//		this.tipoVeiculo = tipoVeiculo;
//		Locadora = locadora;
//		Gerencia = gerencia;
//		Supervisao = supervisao;
//		GPS = gPS;
//		Oficina = oficina;
//		Pool = pool;
//		Devolvido = devolvido;
//	}
	
}
