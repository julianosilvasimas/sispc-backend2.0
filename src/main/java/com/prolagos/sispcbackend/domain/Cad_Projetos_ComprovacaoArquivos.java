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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "comprovacaoarquivoId")
@Table(name="cad_projetos_comprovacaoarquivos")
public class Cad_Projetos_ComprovacaoArquivos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer comprovacaoarquivoId;
	@Getter @Setter private String nomearquivo;
	@Getter @Setter private String caminho;
	@Getter @Setter private String tipoarquivo;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate envio;
	
	@ManyToOne
	@JoinColumn(name="fk_comprovacaoId",foreignKey = @ForeignKey(name="fk_comprovacao_arquivos"))
	@Getter @Setter private Cad_Projetos_Comprovacao comprovacaoId;

}
