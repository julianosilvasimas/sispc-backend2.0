package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "capexanoId")
@Table(name="cad_projetos_financeiroanos")
public class Cad_Projetos_FinanceiroAnos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer capexanoId;
	@Getter @Setter private Integer ano;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double valoraprovado;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double valorrealizado;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double avancofinanceiro;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double rollingforecast1;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double rollingforecast2;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double rollingforecast3;
	
	@ManyToOne
	@JoinColumn(name="fk_capexId" ,foreignKey = @ForeignKey(name="fk_capex_capexano"))
	@Getter @Setter private Cad_Projetos_Financeiros capex;
	
}
