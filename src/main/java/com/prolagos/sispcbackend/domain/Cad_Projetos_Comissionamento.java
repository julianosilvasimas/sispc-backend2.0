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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "comissionamentoId")
public class Cad_Projetos_Comissionamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer comissionamentoId;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double avancofisico;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate inicioprevisto;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate terminoprevisto;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate inicioreplanejado;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate terminoreplanejado;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate iniciorealizado;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate terminorealizado;
	
	@OneToOne
	@JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_obra"))
	@MapsId
	@Setter private Cad_SisPC_Projetos projeto;


}
