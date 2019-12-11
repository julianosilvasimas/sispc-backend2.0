package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.List;

//import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "orgaoId")
@Table(name="cad_sispc_partesinteressadas")
public class Cad_SisPC_PartesInteressadas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Integer orgaoId;
	@Getter @Setter private String orgao;
	@Getter @Setter private String ambito;
	
	@ManyToMany(mappedBy="partestinteressadas")
    private List<Cad_SisPC_Projetos> projetos;

	public Cad_SisPC_PartesInteressadas(Integer orgaoId, String orgao, String ambito) {
		super();
		this.orgaoId = orgaoId;
		this.orgao = orgao;
		this.ambito = ambito;
	}

}
