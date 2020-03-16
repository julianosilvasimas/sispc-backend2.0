package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Cad_Projetos_Sesuite_RiscosOperacionais implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer sesuiteRiscoOperacionaisId;
	@Column(length=150)
	@Getter @Setter private String impactointerrupcao;
	@Column(length=150)
	@Getter @Setter private String probabilidadeimpactointerrupcao;
	@Column(length=150)
	@Getter @Setter private String custointerrupcao;
	@Column(length=150)
	@Getter @Setter private String complexidadeexecucao;
	@Column(length=150)
	@Getter @Setter private String impactosubstituicao;
	@Column(length=150)
	@Getter @Setter private String probabilidadeimpactosubstituicao;
	@Column(length=150)
	@Getter @Setter private String impactoambiental;
	@Column(length=150)
	@Getter @Setter private String probabilidadeimpactoambiental;
	@Column(length=150)
	@Getter @Setter private String impactointegridade;
	@Column(length=150)
	@Getter @Setter private String probabilidadeimpactointegridade;
	@Column(length=150)
	@Getter @Setter private String riscoatraso;
	@Column(length=150)
	@Getter @Setter private String possuilicenca;
	@Column(length=150)
	@Getter @Setter private String nlicenca;
	@Column(length=150)
	@Getter @Setter private String prazolicenca;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Getter @Setter private Boolean condicionantelicenca;
	@Column(length=150)
	@Getter @Setter private String condicionantelicencaespec;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate emissaolicenca;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate validadelicenca;
	
	@OneToOne
	@JoinColumn(name="fk_projSesuiteId" ,foreignKey = @ForeignKey(name="fk_projSeseuite_riscoOperacionais"))
	@MapsId
	@Setter private Cad_Projetos_Sesuite sesuite;
	
}
