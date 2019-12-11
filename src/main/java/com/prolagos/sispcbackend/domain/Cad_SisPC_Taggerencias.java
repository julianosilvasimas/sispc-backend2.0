package com.prolagos.sispcbackend.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "taggerenciaId")
public class Cad_SisPC_Taggerencias {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer taggerenciaId;
	@Getter @Setter private String taggerencia;
	
	@ManyToOne
	@JoinColumn(name="fk_unidadeId", foreignKey = @ForeignKey(name="fk_taggerencia_unidades"))
	@Getter @Setter private Cad_SisPC_Unidades unidadeId;
	
	@ManyToOne
	@JoinColumn(name="fk_diretoriaId", foreignKey = @ForeignKey(name="fk_taggerencia_diretorias"))
	@Getter @Setter private Cad_SisPC_Diretorias diretoriaId;
	
	@ManyToOne
	@JoinColumn(name="fk_gerenciaId", foreignKey = @ForeignKey(name="fk_taggerencia_gerencias"))
	@Getter @Setter private Cad_SisPC_Gerencias gerenciaId;
	
	@JsonIgnore
	@OneToMany(mappedBy="taggerenciaId")
	@Getter @Setter private List<Cad_SisPC_Tagsupervisoes> tagsupervisoes= new ArrayList<>();

	public Cad_SisPC_Taggerencias(Integer taggerenciaId, String taggerencia, Cad_SisPC_Unidades unidadeId,
			Cad_SisPC_Diretorias diretoriaId, Cad_SisPC_Gerencias gerenciaId) {
		super();
		this.taggerenciaId = taggerenciaId;
		this.taggerencia = taggerencia;
		this.unidadeId = unidadeId;
		this.diretoriaId = diretoriaId;
		this.gerenciaId = gerenciaId;
	}
	
	
	
}
