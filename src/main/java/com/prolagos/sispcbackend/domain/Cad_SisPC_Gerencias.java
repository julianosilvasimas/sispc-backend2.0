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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "gerenciaId")
public class Cad_SisPC_Gerencias implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer gerenciaId;
	@Column(name = "gerencia")
	@Getter @Setter private String label;
	@Getter @Setter private String tag;
	@Getter @Setter private String icon;
    @Column(name = "routerlink")
    @Getter @Setter private String routerLink;
    @Column(name = "escopoindicador")
    @Getter @Setter private String escopoIndicador;
	
	@JsonIgnore
	@OneToMany(mappedBy="gerenciaId")
	@Getter @Setter private List<Cad_SisPC_Taggerencias> taggerencias= new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="gerenciaId")
	@Getter @Setter private List<Cad_SisPC_Usuarios> usuarios= new ArrayList<>();	

	public Cad_SisPC_Gerencias(final Integer gerenciaId, final String label, final String tag, final String icon, final String routerLink) {
        this.gerenciaId = gerenciaId;
        this.label = label;
        this.tag = tag;
        this.icon = icon;
        this.routerLink = routerLink;
    }

	
}
