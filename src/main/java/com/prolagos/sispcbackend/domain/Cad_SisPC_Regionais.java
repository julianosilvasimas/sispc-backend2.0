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
@EqualsAndHashCode(of = "regionalId")
public class Cad_SisPC_Regionais {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer regionalId;
	@Getter @Setter private String regional;
	@Getter @Setter private String tag;
	
	@ManyToOne
	@JoinColumn(name="fk_empresalId",foreignKey = @ForeignKey(name="fk_empresa_regionais"))
	@Getter @Setter private Cad_SisPC_Unidades empresaId;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="regionalId")
	@Getter @Setter private List<Cad_SisPC_Unidades> unidades= new ArrayList<>();


	public Cad_SisPC_Regionais(Integer regionalId, String regional, String tag, Cad_SisPC_Unidades empresaId) {
		super();
		this.regionalId = regionalId;
		this.regional = regional;
		this.tag = tag;
		this.empresaId = empresaId;
	}
	
	

}
