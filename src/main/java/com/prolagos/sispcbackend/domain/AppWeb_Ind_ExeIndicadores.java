package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.prolagos.sispcbackend.domain.enums.Periodos_Indicadores;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name="appweb_ind_exeindicadores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppWeb_Ind_ExeIndicadores implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Integer exeindicadorId;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "America/Sao_Paulo")
	@Getter @Setter private LocalDate datareferencia;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "America/Sao_Paulo")
	@Getter @Setter private LocalDate dataindicador;
	
	@Getter @Setter private Integer ciclo;
	
	
	@JsonIgnoreProperties("periodicidade")
	private Integer periodicidade; //Enum de periodo
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double orcado;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double realizado;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double pecld;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double forecast;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double minimo;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double maximo;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double meta;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double previsao;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double dentroprazo;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double foraprazo;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double dentroprazoreg;
	@Column(scale=2,precision=12)
	@Getter @Setter private Double foraprazoreg;
	@Getter @Setter private Integer atendente;
	@Getter @Setter private Integer atendimento;
	@Getter @Setter private String comentario;
	@Getter @Setter private String acao;
	@Getter @Setter private String analise;
	@Getter @Setter private String colaborador;
	

	@ManyToOne
	@JoinColumn(name="fk_indicadorId",foreignKey = @ForeignKey(name="fk_cadindicador_indicadores"))
	@Getter @Setter private Cad_Ind_Indicadores indicadorId;
	
	@ManyToOne
	@JoinColumn(name="fk_emp_codigo",foreignKey = @ForeignKey(name="fk_unidade_indicadores"))
	@Getter @Setter private Cad_SisPC_Unidades undcodigo;
	
	
	public LocalDate avancarDia(LocalDate dataHoje ) {
	    dataHoje = dataHoje.plusDays(1);
	    return dataHoje;
	}
	
	
	
	public AppWeb_Ind_ExeIndicadores(Integer exeindicadorId, LocalDate datareferencia, LocalDate dataindicador, Integer ciclo,
			Periodos_Indicadores periodicidade, Double orcado, Double realizado, Double pecld, Double forecast, Double minimo,
			Double maximo, Double meta, Double previsao, Double dentroprazo, Double foraprazo, Double dentroprazoreg,
			Double foraprazoreg, Integer atendente, Integer atendimento, String comentario, String acao, String analise,
			String colaborador, Cad_Ind_Indicadores indicadorId, Cad_SisPC_Unidades undcodigo) {
		
		
        this.exeindicadorId = exeindicadorId;
		this.datareferencia = datareferencia;
		this.dataindicador = dataindicador;
		this.ciclo = ciclo;
		this.periodicidade = (periodicidade==null) ? null : periodicidade.getCod();
		this.orcado = orcado;
		this.realizado = realizado;
		this.pecld = pecld;
		this.forecast = forecast;
		this.minimo = minimo;
		this.maximo = maximo;
		this.meta = meta;
		this.previsao = previsao;
		this.dentroprazo = dentroprazo;
		this.foraprazo = foraprazo;
		this.dentroprazoreg = dentroprazoreg;
		this.foraprazoreg = foraprazoreg;
		this.atendente = atendente;
		this.atendimento = atendimento;
		this.comentario = comentario;
		this.acao = acao;
		this.analise = analise;
		this.colaborador = colaborador;
		this.indicadorId = indicadorId;
		this.undcodigo = undcodigo;
	}

	@JsonIgnoreProperties("periodicidade")
	public Periodos_Indicadores getPeriodicidade() {
		return Periodos_Indicadores.toEnum(periodicidade);
	}

	@JsonIgnoreProperties("periodicidade")
	public void setPeriodicidade(Periodos_Indicadores periodicidade) {
		this.periodicidade = periodicidade.getCod();
	}

}
