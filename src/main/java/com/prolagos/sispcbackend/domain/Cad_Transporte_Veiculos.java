package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "veiculoId")
public class Cad_Transporte_Veiculos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter private Integer veiculoId;
	@Column(
		name = "datCad", 
		nullable = false, 
		updatable = false, 
		insertable = false, 
		columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
	)
	@Getter @Setter private Date datCad;
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

	public Cad_Transporte_Veiculos(
			final Integer veiculoId,final Date datCad,  final String placa, final String chassi, final String modelo,
			final String capacidadem3, final String responsavel, final String tipoVeiculo,
			final String Locadora, final String Gerencia, final String Supervisao,
			final boolean GPS, final boolean Oficina, final boolean Pool, final boolean Devolvido
		) {
		this.veiculoId = veiculoId;
        this.datCad = null;
        this.placa = placa;
        this.chassi = chassi;
        this.modelo = modelo;
        this.capacidadem3 = capacidadem3;
        this.responsavel = responsavel;
        this.tipoVeiculo = tipoVeiculo;
        this.Locadora = Locadora;
        this.Gerencia = Gerencia;
        this.Supervisao = Supervisao;
        this.GPS = GPS;
        this.Oficina = Oficina;
        this.Pool = Pool;
        this.Devolvido = Devolvido;
    }

	
}
