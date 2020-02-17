package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "IdCad")
@Table(name="cad_rpa_robos")
public class cad_rpa_robos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IdCad")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter private Integer IdCad;
	@Getter @Setter private String Nomebot;
	@Getter @Setter private String Descricao;

//	@JsonIgnore
//	@OneToMany(mappedBy="Bot")
//	@Getter @Setter private List<Apprpa_Rpa_Statusbots> Status= new  ArrayList<>();

	public cad_rpa_robos(Integer id, String nomebot, String descricao) {
		
		this.IdCad = id;
		this.Nomebot = nomebot;
		this.Descricao = descricao;
	}
	
}
