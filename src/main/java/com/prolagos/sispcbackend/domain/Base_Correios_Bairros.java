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
@EqualsAndHashCode(of = "BAI_NU")
public class Base_Correios_Bairros implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer BAI_NU;
	@Getter @Setter private String UFE_SG;
	
	@ManyToOne
	@JoinColumn(name="LOC_NU", foreignKey = @ForeignKey(name="fk_LOC_BAI"))
	@Getter @Setter private Base_Correios_Localidades LOC_NU;
	
	@Getter @Setter private String BAI_NO;
	@Getter @Setter private String BAI_NO_ABREV;

}
