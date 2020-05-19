package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
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

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Base_GSSOnLine_HistoricoDia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;
	@Getter @Setter private String codeqp;
	@Getter @Setter private String nomeeqp;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate dataexpediente;
	@Getter @Setter private String tipoperiodo;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate inicio;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate fim;
	@Getter @Setter private String protoloorigem;
	@Getter @Setter private Integer servico;
	@Getter @Setter private String motivoparada;
	@Getter @Setter private String motivoatraso;
	@Getter @Setter private String satus;
	@Getter @Setter private String motivonaoexecucao;
	@Getter @Setter private String equipamentoutilizado;
	@Getter @Setter private String veiculoutilizado;
	@Getter @Setter private String odometroinicial;
	@Getter @Setter private String odometrofinal;

}
