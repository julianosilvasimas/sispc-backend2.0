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
@EqualsAndHashCode(of = "contratacaoId")
public class Cad_Projetos_Contratacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Getter @Setter private Integer contratacaoId;
	@Getter @Setter private String npedido;
	@Getter @Setter private String requisicao;
	@Getter @Setter private String escopo;
	@Getter @Setter private String contratosistemico;
	@Getter @Setter private String contratofisico;
	@Getter @Setter private String nomeempresa;
	@Getter @Setter private String responsavel;
	@Getter @Setter private String status;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate inicio;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate termino;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double valorcontratado;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate inicioaditivo;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate terminoaditivo;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double valorcontratadoaditivo;
	
	@OneToOne
	@JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_regulatorio"))
	@MapsId
	@Setter private Cad_SisPC_Projetos projeto;
	
	
}
