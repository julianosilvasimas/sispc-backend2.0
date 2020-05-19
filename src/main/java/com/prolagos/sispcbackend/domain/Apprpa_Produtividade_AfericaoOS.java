package com.prolagos.sispcbackend.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor @AllArgsConstructor
@Entity @EqualsAndHashCode(of = "id")
public class Apprpa_Produtividade_AfericaoOS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate ref;
	@Getter @Setter private String descrsetor;
	@Getter @Setter private Integer servico;
	@Getter @Setter private String descricao;
	@Getter @Setter private Integer qtd;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double tme;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double maiortempo;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double menortempo;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double desviopadrao;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double limitesuperior;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double limiteinferior;
	
}
