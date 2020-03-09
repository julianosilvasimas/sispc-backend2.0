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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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
public class Cad_Ind_Graficos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer CampoDoGraficoId;

	@Getter @Setter private String estilo;
	@Getter @Setter private String label;
	@Getter @Setter private String tipografico;
	@Getter @Setter private String coreixo;
	@Getter @Setter private String eixo;
	@Getter @Setter private String posicao;
	@Getter @Setter private Integer ordem;

	@ManyToOne
	@JoinColumn(name="fk_cadgraficoEixo",foreignKey = @ForeignKey(name="fk_cadind_indicadores"))
	@Setter private Cad_Ind_Indicadores indicadorId;

	public Cad_Ind_Graficos(Integer campoDoGraficoId, String estilo, String label, String tipografico, String coreixo,
			String eixo, String posicao, Integer ordem, Cad_Ind_Indicadores indicadorId) {
		CampoDoGraficoId = campoDoGraficoId;
		this.estilo = estilo;
		this.label = label;
		this.tipografico = tipografico;
		this.coreixo = coreixo;
		this.posicao = posicao;
		this.eixo = eixo;
		this.ordem = ordem;
		this.indicadorId = indicadorId;
	}
	

}
