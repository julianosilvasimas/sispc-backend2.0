package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
public class Apprpa_Esgoto_Preenchimentos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@ManyToOne
	@JoinColumn(name="Indicador", foreignKey = @ForeignKey(name="fk_IndicadoresPreenchimentos"))
	@Getter @Setter private Apprpa_Esgoto_Indicadores Indicador;
	
	@ManyToOne
	@JoinColumn(name="Unidade", foreignKey = @ForeignKey(name="fk_IndicadoresUnidades"))
	@Getter @Setter private Apprpa_Esgoto_Unidades Unidade;
	
	@Column(name = "dataDaImportacao", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String dataDaImportacao;

	@Column(name = "DataIndicador", nullable = true, updatable = true, insertable = true, columnDefinition = "TIMESTAMP")
	@Getter @Setter private String DataIndicador;
	
	@Getter @Setter private String Usuario;
	@Getter @Setter private String NCelular;
	@Getter @Setter private String Observacao;
	@Getter @Setter private Double Valor;
	
	@JsonIgnore
	@OneToMany(mappedBy="linhaLog")
	@Getter @Setter private List<Apprpa_Esgoto_PreenchimentosLog> HistoricLog= new ArrayList<>();
}
