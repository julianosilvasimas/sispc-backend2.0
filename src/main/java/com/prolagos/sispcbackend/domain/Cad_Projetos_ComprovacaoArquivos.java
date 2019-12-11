package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Integer comprovacaoarquivoId;
	@Getter @Setter private String nomearquivo;
	@Getter @Setter private String caminho;
	@Getter @Setter private LocalDate envio;
	
	@ManyToOne
	@JoinColumn(name="fk_comprovacaoId",foreignKey = @ForeignKey(name="fk_comprovacao_arquivos"))
	@Getter @Setter private Cad_Projetos_Comprovacao comprovacaoId;

}
