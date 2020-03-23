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
@Table(name="Apprpa_Energia_Cenario")
@EqualsAndHashCode(of = "id")
public class Apprpa_Energia_Cenario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cadCenario", foreignKey = @ForeignKey(name="fk_cenarioCadastro"))
	@Setter private Apprpa_Energia_cadCenarios cadCenario;
	
	@Column(name = "DataIndicador", nullable = true, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String DataIndicador;
	
	@Getter @Setter private Double VolumeOrcado;
	@Getter @Setter private Double VolumeRealizado;
	
	@Getter @Setter private Double OrcadoIndkWh;
	@Getter @Setter private Double RealIndkWh;
	@Getter @Setter private Double RealGestalkWh;
	@Getter @Setter private Double RealGestalPorcentagemkWh;
	
	@Getter @Setter private Double OrcadoIndRS;
	@Getter @Setter private Double RealIndRS;
	@Getter @Setter private Double RealGestalRS;
	@Getter @Setter private Double RealGestalPorcentagemRS;
	

	@Getter @Setter private Double OrcadokWhM3;
	@Getter @Setter private Double RealizadokWhM3;
	@Getter @Setter private Double OrcadoRSM3;
	@Getter @Setter private Double RealizadoRSM3;
	
	@JsonIgnore
	@OneToMany(mappedBy="cenarioLinha")
	@Getter @Setter private List<Apprpa_Energia_Cenariounidades> Unidades = new ArrayList<>();
}
