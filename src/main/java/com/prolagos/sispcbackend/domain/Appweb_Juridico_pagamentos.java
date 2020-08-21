package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@NoArgsConstructor
@EqualsAndHashCode(of = "IdPagamento")
public class Appweb_Juridico_pagamentos implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer IdPagamento;
	
	@Column(name = "DataCadastro", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String DataCadastro;

	@Getter @Setter private String UsuarioInsert;
	@Getter @Setter private String Empresa;
	@Getter @Setter private String Autor;
	@Getter @Setter private String Processo;
	@Getter @Setter private String Natureza;
	
	@Column(name = "DataProgramada", nullable = false, updatable = true, insertable = true, columnDefinition = "TIMESTAMP")
	@Getter @Setter private String DataProgramada;
	
	@Getter @Setter private Double Valor;
	@Getter @Setter private String Escritorio;
	@Getter @Setter private String ContaContabil;
	@Getter @Setter private String CentroDeCusto;
	@Getter @Setter private String Fornecedor;
	@Getter @Setter private String Sentenca;
	@Getter @Setter private String MotivoPagamento;
	@Getter @Setter private boolean FalhaConcess;
	@Getter @Setter private Integer EnviadoParaAprovacao;

	@Getter @Setter private String Aprovador1;
	@Getter @Setter private Integer Aprovacao1;

	@Getter @Setter private String Aprovador2;
	@Getter @Setter private Integer Aprovacao2;

	@Getter @Setter private String Aprovador3;
	@Getter @Setter private Integer Aprovacao3;
	
	public Appweb_Juridico_pagamentos(Integer idPagamento, String dataCadastro, String usuarioInsert, String empresa,
			String autor, String processo, String natureza, String dataProgramada, Double valor, String escritorio,
			String contaContabil, String centroDeCusto, String fornecedor, String sentenca, String motivoPagamento,
			boolean falhaConcess, Integer enviadoParaAprovacao, String aprovador1, Integer aprovacao1,
			String aprovador2, Integer aprovacao2, String aprovador3, Integer aprovacao3) {
		super();
		IdPagamento = idPagamento;
		DataCadastro = dataCadastro;
		UsuarioInsert = usuarioInsert;
		Empresa = empresa;
		Autor = autor;
		Processo = processo;
		Natureza = natureza;
		DataProgramada = dataProgramada;
		Valor = valor;
		Escritorio = escritorio;
		ContaContabil = contaContabil;
		CentroDeCusto = centroDeCusto;
		Fornecedor = fornecedor;
		Sentenca = sentenca;
		MotivoPagamento = motivoPagamento;
		FalhaConcess = falhaConcess;
		EnviadoParaAprovacao = enviadoParaAprovacao;
		Aprovador1 = aprovador1;
		Aprovacao1 = aprovacao1;
		Aprovador2 = aprovador2;
		Aprovacao2 = aprovacao2;
		Aprovador3 = aprovador3;
		Aprovacao3 = aprovacao3;
	}
	
	
}
