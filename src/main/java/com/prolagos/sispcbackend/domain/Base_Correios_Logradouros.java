package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "LOG_NU")
public class Base_Correios_Logradouros implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer LOG_NU;
	@Getter @Setter private String UFE_SG;
	
	@ManyToOne
	@JoinColumn(name="LOC_NU", foreignKey = @ForeignKey(name="fk_LOC_LOG"))
	@Getter @Setter private Base_Correios_Localidades LOC_NU;
	
	@Getter @Setter private Integer BAI_NU_INI;
	@Getter @Setter private String BAI_NU_FIM;
	@Getter @Setter private String LOG_NO;
	@Getter @Setter private String LOG_COMPLEMENTO;
	@Getter @Setter private String CEP;
	@Getter @Setter private String TLO_TX;
	@Getter @Setter private String LOG_STA_TLO;
	@Getter @Setter private String LOG_NO_ABREV;

}
