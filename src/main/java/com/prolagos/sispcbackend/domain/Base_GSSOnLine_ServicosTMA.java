package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "idosp")
public class Base_GSSOnLine_ServicosTMA  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Getter @Setter private Integer idosp;
	@Getter @Setter private Integer anoos;
	@Getter @Setter private String anomes;
	@Getter @Setter private Integer matricula;
	@Getter @Setter private String enderecoosp;
	@Getter @Setter private String servico;
	@Getter @Setter private String descricao;
	@Getter @Setter private String eqp;
	@Getter @Setter private String dataemissao;
	@Getter @Setter private String dataprogramacao;
	@Getter @Setter private String datainicio;
	@Getter @Setter private String dataconclusao;
	@Getter @Setter private String status;
	@Getter @Setter private String atraso;
	@Getter @Setter private String nomecor;
	@Getter @Setter private String codexecocor;
	@Getter @Setter private String motivoexecocor;
	@Getter @Setter private String idanomalia;
	@Getter @Setter private String descranomalia;
	@Getter @Setter private String tempopadrao;
	@Getter @Setter private String tempodesloc;
	@Getter @Setter private String tempodeslocsegundos;
	@Getter @Setter private String tempopreparo;
	@Getter @Setter private String tempopreparosegundos;
	@Getter @Setter private String tempoexecucao;
	@Getter @Setter private String tempoexecucaosegundos;
	@Getter @Setter private String tempopreenchimento;
	@Getter @Setter private String tempopreenchimentosegundos;
	@Getter @Setter private String tempoparalisacao;
	@Getter @Setter private String tempoparalisacaosegundos;
	@Getter @Setter private String tempoatendimento;
	@Getter @Setter private String descrsetor;
	@Getter @Setter private String latitude;
	@Getter @Setter private String longitude;
	@Getter @Setter private String fotosmin;
	@Getter @Setter private String qtdefotos;
	@Getter @Setter private String dat_agendamento;
	@Getter @Setter private String sf;
	@Getter @Setter private String latitudeexecucao;
	@Getter @Setter private String longitudeexecucao;
	@Getter @Setter private String id_contrato;
	@Getter @Setter private String cd_sub_regiao;
	@Getter @Setter private String cd_regiao;
	@Getter @Setter private String idservico;
	@Getter @Setter private String idexecucaoservico;
	@Getter @Setter private String servicoprincipal;
	@Getter @Setter private String idexecucaoservicoprincipal;
	@Getter @Setter private String unidade;
	@Getter @Setter private String cd_servico_solicitado;
	@Getter @Setter private String ds_servico_solicitado;
	@Getter @Setter private String executado;
	@Getter @Setter private String nm_tipo_execucao;
	@Getter @Setter private String dtlimiteexecucao;
	@Getter @Setter private String dtservico;
	@Getter @Setter private String dtfechamento;
	@Getter @Setter private String funcionarios;
	@Getter @Setter private String descrsetorsolicitante;
	@Getter @Setter private String terceironomeempresa;
	@Getter @Setter private String terceironomefantasia;
	@Getter @Setter private String terceirocnpj;
	@Getter @Setter private String matrizdeserviços;


}
