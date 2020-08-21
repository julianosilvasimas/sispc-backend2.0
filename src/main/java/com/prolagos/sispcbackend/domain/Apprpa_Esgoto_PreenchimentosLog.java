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
@EqualsAndHashCode(of = "id")
public class Apprpa_Esgoto_PreenchimentosLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@ManyToOne
	@JoinColumn(name="linhaLog", foreignKey = @ForeignKey(name="fk_preench_log"))
	@Setter private Apprpa_Esgoto_Preenchimentos linhaLog;
	
	@Column(name = "dataDaAlteracao", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String dataDaAlteracao;

	@Getter @Setter private String Usuario;
	@Getter @Setter private Double ValorAntigo;
	@Getter @Setter private Double ValorNovo;
	
}
