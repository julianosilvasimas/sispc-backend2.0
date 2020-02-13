package com.prolagos.sispcbackend.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "authId")
public class Cad_Auth_Usuarios {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer authId;
	@Getter @Setter private String auth;
	
	@ManyToOne
	@JoinColumn(name="fk_tagsupervisaoId", foreignKey = @ForeignKey(name="fk_auth_tagsupervisoes"))
	@Getter @Setter private Cad_SisPC_Tagsupervisoes tagsupervisaoId;
	
	@ManyToOne
	@JoinColumn(name="fk_usuarioId", foreignKey = @ForeignKey(name="fk_auth_usuarios"))
	@Getter @Setter private Cad_SisPC_Usuarios usuarioId;
	
	@ManyToOne
	@JoinColumn(name="fk_moduloId", foreignKey = @ForeignKey(name="fk_auth_modulos"))
	@Getter @Setter private Cad_SisPC_Modulos moduloId;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT) 
	@JoinTable(name = "cad_relc_indicadoresauths",
		joinColumns = @JoinColumn(name = "fk_indicadorId", foreignKey = @ForeignKey(name="fk_auth_indicadores")),
		inverseJoinColumns = @JoinColumn(name = "fk_authId", foreignKey = @ForeignKey(name="fk_indicador_auths")))
	@Getter @Setter private List<Cad_Ind_Indicadores> indicadores;
	
}
