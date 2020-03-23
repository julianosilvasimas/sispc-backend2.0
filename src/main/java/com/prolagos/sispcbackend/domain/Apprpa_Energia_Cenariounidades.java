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
@Table(name="Apprpa_Energia_Cenariounidades")
@EqualsAndHashCode(of = "id")
public class Apprpa_Energia_Cenariounidades implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@ManyToOne
	@JoinColumn(name="CenarioLinha", foreignKey = @ForeignKey(name="fk_cenarioLinha"))
	@Setter private Apprpa_Energia_Cenario cenarioLinha;

	@Column(name = "DataIndicador", nullable = true, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String DataIndicador;
	
	@Getter @Setter private String NomeLocal;
	@Getter @Setter private Double Consumo;
	
}
