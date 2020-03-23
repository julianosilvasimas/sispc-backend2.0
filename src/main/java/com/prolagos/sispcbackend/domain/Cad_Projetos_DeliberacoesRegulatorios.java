package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "deliberacaoId")
@Table(name="cad_projetos_deliberacoesregulatorios")
public class Cad_Projetos_DeliberacoesRegulatorios implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer deliberacaoId;
	@Getter @Setter private String ndeliberacao;
	@Getter @Setter private String assunto;
	@Getter @Setter private String tipo;   // 1.Informativo ou 2.Deliberativo  //Pode ser um Enum de tipo
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate envio;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate retorno;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate aprovado;
	@Getter @Setter private String link;
    
    @ManyToOne
	@JoinColumn(name="fk_regulatorioId",foreignKey = @ForeignKey(name="fk_regulatorio_deliberacoes"))
	@Getter @Setter Cad_Projetos_Regulatorio regulatorio;

	
}
