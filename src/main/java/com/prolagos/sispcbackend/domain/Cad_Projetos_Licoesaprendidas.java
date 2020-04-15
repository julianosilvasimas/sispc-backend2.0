package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@EqualsAndHashCode(of = "licoesId")
public class Cad_Projetos_Licoesaprendidas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Getter @Setter private Integer licoesId;
	@Getter @Setter private String objetivosatendidos;
	@Getter @Setter private String entreguenoprazo;
	@Getter @Setter private String noorcamento;
	@Getter @Setter private String atendeuescopo;
	@Getter @Setter private String pfortes;
	@Getter @Setter private String pfracos;
	@Getter @Setter private String questoes;
	@Getter @Setter private String recomendacoes;
	
	@OneToOne
	@JoinColumn(name="fk_projetoId" ,foreignKey = @ForeignKey(name="fk_projeto_licoes"))
	@MapsId
	@Setter private Cad_SisPC_Projetos projeto;

}
