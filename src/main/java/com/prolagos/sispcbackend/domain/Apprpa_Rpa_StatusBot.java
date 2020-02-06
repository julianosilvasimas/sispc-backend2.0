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
@Table(name="base_gss_irregularidades")
public class Apprpa_Rpa_StatusBot implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="seq_status")
	@Getter @Setter private Integer  Id;
	@Column(name = "Timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private Date Timestamp;
	@Getter @Setter private String User;
	@Getter @Setter private String Ultimostart;
	@Getter @Setter private String Ultimostartip;
	@ManyToOne
	@JoinColumn(name="fk_idbot", foreignKey = @ForeignKey(name="fk_Status_idbot"))
	@Getter @Setter private cad_rpa_robos Bot;
	@Getter @Setter private String Status;
	@Getter @Setter private String Exception;


}
