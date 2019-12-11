package com.prolagos.sispcbackend.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@EqualsAndHashCode(of = "engenhariaId")
public class Cad_Projetos_Engenharia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Integer engenhariaId;
	@Getter @Setter private String tipo;
	@Getter @Setter private String pep;
	@Getter @Setter private String status;
	@Getter @Setter private LocalDate previsto;
	@Getter @Setter private LocalDate replanejado;
	@Getter @Setter private LocalDate realizado;
	
	@ManyToOne
	@JoinColumn(name="fk_projetoId",foreignKey = @ForeignKey(name="fk_projeto_engenharia"))
	@Getter @Setter private Cad_SisPC_Projetos projetoId;

}
