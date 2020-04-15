package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
public class Apprpa_Esgoto_Unidades implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@Column(name = "dataDaCriacao", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String dataDaCriacao;

	@Getter @Setter private String Unidade;
	@Getter @Setter private Double Vazao;
	@Getter @Setter private String TipoDeTratamento;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "apprpa_relc_esgotounidades_esgotoIndicadores",
		joinColumns = @JoinColumn(name = "fk_unidadeId", foreignKey = @ForeignKey(name="fk_unidade_indicador")),
		inverseJoinColumns = @JoinColumn(name = "fk_indicadorId", foreignKey = @ForeignKey(name="fk_indicador_unidade")))
	@Fetch(FetchMode.SUBSELECT) 
	@Getter @Setter private List<Apprpa_Esgoto_Indicadores> Indicadores;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "apprpa_relc_usuarios_etes",
		joinColumns = @JoinColumn(name = "fk_usuarioId", foreignKey = @ForeignKey(name="fk_usuario_ete")),
		inverseJoinColumns = @JoinColumn(name = "fk_eteId", foreignKey = @ForeignKey(name="fk_ete_usuario")))
	@Fetch(FetchMode.SUBSELECT) 
	@Getter @Setter private List<Cad_SisPC_Usuarios> Operadores;
	
	@JsonIgnore
	@OneToMany(mappedBy="Unidade")
	@Setter private List<Apprpa_Esgoto_Preenchimentos> Preenchimentos= new ArrayList<>();
}
