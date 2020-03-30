package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
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
@EqualsAndHashCode(of = "capexId")
public class Cad_Projetos_Financeiros implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable=true)
	@Getter @Setter private Integer capexId;
	@Getter @Setter private String nprojetocognos;
	@Getter @Setter private String descricao;
	@Getter @Setter private String status;
	@Column(scale=2,precision=12)  
	@Getter @Setter private Double valortotal;
	
	@Getter @Setter private String considerarprojcog;
	@Getter @Setter private String tipoprojcognos;
	@Getter @Setter private String perpetuidadeprojcognos;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private String inicioprojcognos;
	@Getter @Setter private String prazopagprojcognos;
	@Getter @Setter private String prazoamorprojcognos;
	@Getter @Setter private String gerareceitaprojcognos;

	
	@OneToOne
	@JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_financeiro"))
	@MapsId
	@Setter private Cad_SisPC_Projetos projeto;
	
	@JsonIgnore
	@OneToMany(mappedBy="capex")
	@Getter @Setter private List<Cad_Projetos_FinanceiroAnos> anos= new ArrayList<>();


}
