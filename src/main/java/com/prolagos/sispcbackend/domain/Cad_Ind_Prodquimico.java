package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "produtoId")
public class Cad_Ind_Prodquimico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer produtoId;
	@Getter @Setter private String produto;
	@Getter @Setter private double preco;
	
	@JsonIgnore
	@ManyToMany(mappedBy="produtos", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT) 
	@Getter @ Setter private List<Cad_Ind_Indicadores> indicadores;

	public Cad_Ind_Prodquimico(Integer produtoId, String produto, double preco) {
		super();
		this.produtoId = produtoId;
		this.produto = produto;
		this.preco = preco;
	}	
	
}
