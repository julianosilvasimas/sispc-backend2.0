package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "comissionamentoId")
public class Cad_Projetos_Comissionamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer comissionamentoId;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double avancofisico;
	@Getter @Setter private LocalDate previsto;
	@Getter @Setter private LocalDate replanejado;
	@Getter @Setter private LocalDate realizado;
	
	@OneToOne
	@JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_obra"))
	@MapsId
	private Cad_SisPC_Projetos projeto;


}
