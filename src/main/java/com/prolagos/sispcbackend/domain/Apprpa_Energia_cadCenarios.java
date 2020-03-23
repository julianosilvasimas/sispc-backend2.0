package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="Apprpa_Energia_cadCenarios")
@EqualsAndHashCode(of = "id")
public class Apprpa_Energia_cadCenarios implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@Column(name = "ImportadoParaIndicadores", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String ImportadoParaIndicadores;
	
	@Column(name = "DataReferencia", nullable = true, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String DataReferencia;

	@Getter @Setter private Integer Classificacao;
	@Getter @Setter private String NomeDoCenario;
	@Getter @Setter private String Usuario;
	
	@Getter @Setter private Double Tarifa;
	@Getter @Setter private Double Aumento;

	@OneToMany(mappedBy="cadCenario")
	@Getter @Setter private List<Apprpa_Energia_Cenario> cenariosLinhas = new ArrayList<>();
}
