package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "ID_OS")
@Table(name="base_gss_historicoos")
public class Base_GSS_HistoricoOS implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	 @Getter @Setter private Integer ID_OS;
	 @Getter @Setter private Integer ANO_PEDIDO;
	 @Getter @Setter private Integer NUM_PEDIDO;
	 @Getter @Setter private Integer ZONA_LIGACAO;
	 @Getter @Setter private Integer NUM_LIGACAO;
	 @Getter @Setter private String NOM_CLIENTE;
	 @Getter @Setter private String COD_COLABORADOR;
	 @Getter @Setter private Integer COD_DEPARTAMENTO_ATUAL;
	 @Getter @Setter private String NOM_DEPARTAMENTO;
	 @Getter @Setter private Integer COD_FASE_OS;
	 @Getter @Setter private Integer COD_GRUPO;
	 @Getter @Setter private Integer COD_GRUPO_SOLI;
	 @Getter @Setter private Integer COD_ROTA_LEITURA;
	 @Getter @Setter private Integer COD_TPO_SERVICO;
	 @Getter @Setter private String COD_TPO_SERVICO_SLEX;
	 @Getter @Setter private String COD_USUARIO;
	 @Getter @Setter private String COD_USUARIO_ENCER;
	 @Getter @Setter private LocalDateTime DAT_AGENDAMENTO;
	 @Getter @Setter private LocalDateTime DAT_ALERTA;
	 @Getter @Setter private LocalDateTime DAT_EMISSAO_OS;
	 @Getter @Setter private LocalDateTime DAT_ENCERRAMENTO;
	 @Getter @Setter private LocalDateTime DAT_EXECUCAO;
	 @Getter @Setter private LocalDateTime DAT_PEDIDO;
	 @Getter @Setter private LocalDateTime DAT_PREVISTA;
	 @Getter @Setter private String DSC_GRUPO_SOLI;
	 @Getter @Setter private String DSC_SERVICO;
	 @Getter @Setter private String DSC_SITUACAO;
	 @Getter @Setter private String END_LIGACAO;
	 @Getter @Setter private String END_REQUERENTE;
	 @Getter @Setter private String NOM_REQUERENTE;
	 @Getter @Setter private String NUM_CORTE;
	 @Getter @Setter private String NUM_RODADA;
	 @Getter @Setter private String NUM_TELEFONE;
	 @Getter @Setter private String OBS_ITEM_PEDIDO;
	 @Getter @Setter private String OBS_ITEM_TECNICO;
	 @Getter @Setter private Integer SEQ_ROTA;
	 @Getter @Setter private String STA_ELETRONICA;
	 @Getter @Setter private String DSC_ELETRONICA;
	 @Getter @Setter private String STA_EMISSAO_OS;
	 @Getter @Setter private Integer TPO_ENCERRAMENTO;
	 @Getter @Setter private String TPO_OS;

}
