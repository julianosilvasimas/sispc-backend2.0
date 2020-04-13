package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "comprovacaoId")
public class Cad_Projetos_Comprovacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer comprovacaoId;
	@Getter @Setter private String nprocesso;
	@Getter @Setter private String status;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate envio;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate retorno;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double valorcomprovado;
	@Getter @Setter private Integer moeda;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double valoratualizado;
	@Getter @Setter private Integer andamento;
	
	@OneToOne
	@JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_comprovacao"))
	@MapsId
	@Setter private Cad_SisPC_Projetos projeto;
	
	@JsonIgnore
	@OneToMany(mappedBy="comprovacaoId")
	@Getter @Setter private List<Cad_Projetos_ComprovacaoArquivos> arquivos= new ArrayList<>();

}
