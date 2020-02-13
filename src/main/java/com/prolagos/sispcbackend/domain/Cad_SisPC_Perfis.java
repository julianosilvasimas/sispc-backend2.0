package com.prolagos.sispcbackend.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "perfilId")
public class Cad_SisPC_Perfis implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer perfilId;
	@Getter @Setter private String perfil;
	@Getter @Setter private String permissao;

	
	@JsonIgnore
	@Fetch(FetchMode.SUBSELECT) 
	@ManyToMany(mappedBy="perfis", fetch = FetchType.EAGER)
	@Getter @Setter private List<Cad_SisPC_Usuarios> usuarios;

	public Cad_SisPC_Perfis(Integer perfilId, String perfil, String permissao) {
		super();
		this.perfilId = perfilId;
		this.perfil = perfil;
		this.permissao = permissao;
	}
	
	public String getAuthority() {
		return this.perfil;
	}
		
}
