package com.prolagos.sispcbackend.domain;
// Ao Voltar para o front retirar os campos restrições e riscos e segmentos; objetivo descrição e Premissas trazer das informações Se Suite
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "projetoId")
public class Cad_SisPC_Projetos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer projetoId;
	@Getter @Setter private String projeto;
	@Getter @Setter private String localidade;
	@Getter @Setter private String setor;
	@Getter @Setter private String responsavel;
	@Getter @Setter private String statusgloblal;
	@Getter @Setter private String radar;
	@Getter @Setter private Integer gravidade;
	@Getter @Setter private Integer urgencia;
	@Getter @Setter private Integer tendencia;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate inicioprevisto;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate inicioreplanejado;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate iniciorealizado;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate terminoprevisto;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate terminoreplanejado;
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate terminorealizado;
	@Getter @Setter private String descricao;
	@Getter @Setter private String premissas;
	@Getter @Setter private String restricao;
	@Getter @Setter private String riscos;
	@Getter @Setter private String objetivo;
	
	@JsonIgnore
	@OneToMany(mappedBy="projetoId")
	@Getter @Setter private List<Cad_Projetos_Regulatorio> regulatorio= new ArrayList<>();
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="projeto")
	@Getter @Setter private Cad_Projetos_Financeiros financeiro;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="projeto")
	@Getter @Setter private Cad_Projetos_Contratacao contratacao;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="projeto")
	@Getter @Setter private Cad_Projetos_Obras obra;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="projeto")
	@Getter @Setter private Cad_Projetos_Comissionamento comissionamento;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="projeto")
	@Getter @Setter private Cad_Projetos_Comprovacao comprovacao;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="projeto")
	@Getter @Setter private Cad_Projetos_Licoesaprendidas licoes;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="projeto")
	@Getter @Setter private Cad_Projetos_Sesuite sesuite;
	
	@ManyToMany
    @JoinTable(name="cad_relc_projetospartesinteressadas",
    joinColumns= @JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_partes")),  
    inverseJoinColumns= @JoinColumn(name="fk_orgaoId" ,foreignKey = @ForeignKey(name="fk_parte_projetos")))
	@Getter @Setter private List<Cad_SisPC_PartesInteressadas> partestinteressadas;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="projetoId")
	@Getter @Setter private List<Cad_Projetos_Licenciamentos> licenciamentos= new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="projetoId")
	@Getter @Setter private List<Cad_Projetos_Engenharia> projetosengenharia= new ArrayList<>();
	
}
