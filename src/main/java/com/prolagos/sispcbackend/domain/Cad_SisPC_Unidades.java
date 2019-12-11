package com.prolagos.sispcbackend.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@EqualsAndHashCode(of = "unidadeId")
@Entity
public class Cad_SisPC_Unidades {
	
	@Id
	@Getter @Setter private Integer unidadeId;
	@Getter @Setter private String unidade;
	@Getter @Setter private Integer cnpj;
	@Getter @Setter private String tag;
	
	
	@ManyToOne
	@JoinColumn(name="fk_regionalId", foreignKey = @ForeignKey(name="fk_regional_unidades"))
	@Getter @Setter private Cad_SisPC_Regionais regionalId;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="undcodigo")
	@Getter @Setter private List<Base_GSS_Irregularidades> irregularidades= new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="undcodigo")
	@Getter @Setter private List<Base_GSS_Clientes> clientes= new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="unidadeId")
	@Getter @Setter private List<Cad_SisPC_Taggerencias> taggerencias= new ArrayList<>();

	public Cad_SisPC_Unidades(Integer unidadeId, String unidade, Integer cnpj, Cad_SisPC_Regionais regionalId) {
		super();
		this.unidadeId = unidadeId;
		this.unidade = unidade;
		this.cnpj = cnpj;
		this.regionalId = regionalId;
	}
	
}
