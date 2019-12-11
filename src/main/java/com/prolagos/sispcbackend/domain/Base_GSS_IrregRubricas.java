package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "rubricaId")
@Entity
@Table(name="base_gss_irregrubricas")
public class Base_GSS_IrregRubricas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer rubricaId;
	@Column(name="codGSS")
	@Getter @Setter private Integer codGSS;
	@Column(name="codFAT")
	@Getter @Setter private Integer codFAT;
	@Getter @Setter private String rubrica;
	@Getter @Setter private Double valor;
	
}
