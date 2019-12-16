package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prolagos.sispcbackend.domain.enums.Classificacao_Indicadores;
import com.prolagos.sispcbackend.domain.enums.Tendencia_Indicadores;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "indicadorId")
public class Cad_Ind_Indicadores implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer indicadorId;
	@JsonIgnoreProperties("classificacao")
	private Integer classificacao;
	@Getter @Setter private String indicador;
	@Getter @Setter private Integer gerencia;
	@Getter @Setter private Integer ordem;
	@Getter @Setter private Integer tipoGrafico;
	@JsonIgnoreProperties("tendencia")
	private Integer tendencia;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT) 
	@JoinTable(name = "cad_relc_prodquimicoindicadores",
		joinColumns = @JoinColumn(name = "fk_indicadorId", foreignKey = @ForeignKey(name="fk_indicador_produtos")),
		inverseJoinColumns = @JoinColumn(name = "fk_produtoId", foreignKey = @ForeignKey(name="fk_produto_indicadores")))
	@Getter @Setter private List<Cad_Ind_Prodquimico> produtos;
	
	@JsonIgnore
	@OneToMany(mappedBy="indicadorId")
	@Getter @Setter private List<AppWeb_Ind_ExeIndicadores> exeindicadores= new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy="indicadores", fetch = FetchType.EAGER)
	@Getter @ Setter private List<Cad_Auth_Usuarios> usuarios;

	public Cad_Ind_Indicadores(final Integer indicadorId, final String indicador, final Integer ordem, final Integer tipoGrafico,
			Classificacao_Indicadores classificacao, Tendencia_Indicadores tendencia) {
		super();
		this.indicadorId = indicadorId;
		this.indicador = indicador;
	    this.tipoGrafico = tipoGrafico;
	    this.ordem = ordem;
	    this.classificacao = ((classificacao == null) ? null : Integer.valueOf(classificacao.getCod()));
	    this.tendencia = ((tendencia == null) ? null : Integer.valueOf(tendencia.getCod()));
	}
	
	@JsonIgnoreProperties({ "classificacao" })
    public Classificacao_Indicadores getClassificacao() {
        return Classificacao_Indicadores.toEnum(this.classificacao);
    }
    
    @JsonIgnoreProperties({ "classificacao" })
    public void setClassificacao(final Classificacao_Indicadores classificacao) {
        this.classificacao = classificacao.getCod();
    }
    
    @JsonIgnoreProperties({ "tendencia" })
    public Tendencia_Indicadores getTendencia() {
        return Tendencia_Indicadores.toEnum(this.tendencia);
    }
    
    @JsonIgnoreProperties({ "tendencia" })
    public void setTendencia(final Tendencia_Indicadores tendencia) {
        this.tendencia = tendencia.getCod();
    }

}
