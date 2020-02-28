package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

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
	@Getter @Setter private Integer regulatorioId;
	@Getter @Setter private String processo;
	@Getter @Setter private String fluxoinvestimento;
	@Getter @Setter private LocalDate inicio;
	@Getter @Setter private LocalDate termino;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double valorprojeto;
	
	@OneToOne
	@JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_regulatorio"))
	@MapsId
	@Setter private Cad_SisPC_Projetos projeto;
	
	
	@ManyToMany
    @JoinTable(name="cad_relc_regulatoriodeliberacoes",
    joinColumns= @JoinColumn(name="fk_regulatorioId" ,foreignKey = @ForeignKey(name="fk_regulatorio_deliberacoes")),  
    inverseJoinColumns= @JoinColumn(name="fk_deliberacaoId" ,foreignKey = @ForeignKey(name="fk_deliberacao_regulatorios")))
	@Getter @Setter private List<Cad_Projetos_DeliberacoesRegulatorios> deliberacoes;


	public Cad_Projetos_Regulatorio(Integer regulatorioId, String processo, String fluxoinvestimento, LocalDate inicio,
			LocalDate termino, Double valorprojeto) {
		super();
		this.regulatorioId = regulatorioId;
		this.processo = processo;
		this.fluxoinvestimento = fluxoinvestimento;
		this.inicio = inicio;
		this.termino = termino;
		this.valorprojeto = valorprojeto;
	}
	
	
	
}
