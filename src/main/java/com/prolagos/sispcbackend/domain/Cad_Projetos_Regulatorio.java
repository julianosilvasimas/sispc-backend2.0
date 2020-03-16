package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "regulatorioId")
public class Cad_Projetos_Regulatorio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer regulatorioId;
	@Getter @Setter private String fluxoinvestimento;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate inicio;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate termino;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double valorprojeto;
	@Getter @Setter private String descricao;
	@Getter @Setter private String aprovacao;
	@Getter @Setter private String moeda;
	
	@ManyToOne
	@JoinColumn(name="fk_projetoId",foreignKey = @ForeignKey(name="fk_projeto_regulatorio"))
	@Getter @Setter private Cad_SisPC_Projetos projetoId;
	
	@JsonIgnore
	@OneToMany(mappedBy="regulatorio")
	@Getter @Setter private List<Cad_Projetos_DeliberacoesRegulatorios> deliberacoes= new ArrayList<>();

	public Cad_Projetos_Regulatorio(Integer regulatorioId,String fluxoinvestimento, LocalDate inicio,
			LocalDate termino, Double valorprojeto, String descricao, String aprovacao, String moeda) {
		super();
		this.regulatorioId = regulatorioId;
		this.fluxoinvestimento = fluxoinvestimento;
		this.inicio = inicio;
		this.termino = termino;
		this.valorprojeto = valorprojeto;
		this.descricao = descricao;
		this.aprovacao = aprovacao;
		this.moeda = moeda;
	}

	
}
