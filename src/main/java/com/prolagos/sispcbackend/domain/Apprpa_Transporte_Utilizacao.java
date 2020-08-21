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
@EqualsAndHashCode(of = "id")
public class Apprpa_Transporte_Utilizacao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@Column(name = "Timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String Timestamp;
	
	@Getter @Setter private String placa;
	@Getter @Setter private String motorista;
	@Getter @Setter private String distancia_km;
	
	@Getter @Setter private String hodometro_final;
	@Getter @Setter private String hodometro_inicial;


	@Column(name = "fim_do_trecho", nullable = true, updatable = true, insertable = true, columnDefinition = "TIMESTAMP")
	@Getter @Setter private String fim_do_trecho;
	
	@Column(name = "inicio_da_movimentacao", nullable = true, updatable = true, insertable = true, columnDefinition = "TIMESTAMP")
	@Getter @Setter private String inicio_da_movimentacao;
	
	@Column(name = "motor_ligado", nullable = true, updatable = true, insertable = true, columnDefinition = "TIMESTAMP")
	@Getter @Setter private String motor_ligado;
	
	@Getter @Setter private String posicao_final;
	@Getter @Setter private String posicao_inicial;

	@Getter @Setter private Double tempo_em_movimento;
	@Getter @Setter private Double tempo_parado_c_motor_ligado;
	@Getter @Setter private Double tempo_total_de_funcionamento;

	@Getter @Setter private String tempo_reinicio;
	
	@Getter @Setter private Double velocidade_maxima;
	@Getter @Setter private Double velocidade_media;
}
