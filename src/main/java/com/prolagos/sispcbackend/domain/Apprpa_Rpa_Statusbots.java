package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.sql.Date;
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

@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="Apprpa_Rpa_Statusbots")
@EqualsAndHashCode(of = "IdAppWeb")
public class Apprpa_Rpa_Statusbots implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer IdAppWeb;
	
	@Column(name = "Timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String Timestamp;
	@Getter @Setter private String User;
	@Getter @Setter private String Ultimostart;
	@Getter @Setter private String Ultimostartip;
	@Getter @Setter private String Status;
	@Getter @Setter private String Exception;
	
	
	@ManyToOne
	@JoinColumn(name="Bot", foreignKey = @ForeignKey(name="fk_Status_idbot"))
	@Getter @Setter private cad_rpa_robos Bot;
}
