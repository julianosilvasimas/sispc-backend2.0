package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "deliberacaoId")
@Table(name="cad_projetos_deliberacoesregulatorios")
public class Cad_Projetos_DeliberacoesRegulatorios implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Integer deliberacaoId;
	@Getter @Setter private String ndeliberacao;
	@Getter @Setter private String assunto;
	@Getter @Setter private String tipo;   // 1.Informativo ou 2.Deliberativo  //Pode ser um Enum de tipo
	@Getter @Setter private LocalDate envio;
	@Getter @Setter private LocalDate retorno;
	@Getter @Setter private LocalDate aprovado;
	
	@ManyToMany(mappedBy="deliberacoes")
    private List<Cad_Projetos_Regulatorio> processosregulatorios;

	public Cad_Projetos_DeliberacoesRegulatorios(Integer deliberacaoId, String ndeliberacao, String assunto,
			String tipo, LocalDate envio, LocalDate retorno, LocalDate aprovado) {
		super();
		this.deliberacaoId = deliberacaoId;
		this.ndeliberacao = ndeliberacao;
		this.assunto = assunto;
		this.tipo = tipo;
		this.envio = envio;
		this.retorno = retorno;
		this.aprovado = aprovado;
	}
    
    
	
}
