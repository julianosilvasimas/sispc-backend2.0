package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "irregularidadeId")
@Entity
@Table(name="base_gss_irregularidades")
public class Base_GSS_Irregularidades implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name=" seq_irregularidade")
	@Getter @Setter private Integer  irregularidadeId;
	@ManyToOne
	@JoinColumn(name="fk_seq_responsavel", foreignKey = @ForeignKey(name="fk_irregularidades_clientes"))
	@Getter @Setter private Base_GSS_Clientes contrato;
	@Getter @Setter private String cod_forma_pagamento;
	@Getter @Setter private Integer cod_ocorrencia_1;
	@Getter @Setter private Integer cod_ocorrencia_2;
	@Getter @Setter private Integer cod_ocorrencia_3;
	@Getter @Setter private Integer cod_sit_notificacao;
	@Getter @Setter private Integer con_recuperado;
	@Getter @Setter private LocalDateTime dat_inclusao;
	@Getter @Setter private LocalDateTime dat_notificacao;
	@Getter @Setter private LocalDateTime dat_tramite;
	@Getter @Setter private String empresa;
	@ManyToOne
	@JoinColumn(name="fk_emp_codigo",foreignKey = @ForeignKey(name="fk_unidade_irregularidade"))
	@Getter @Setter private Cad_SisPC_Unidades undcodigo;
	@Getter @Setter private Integer itm_irregularidade;
	@Getter @Setter private Integer num_ligacao;
	@Getter @Setter private String num_termo;
	@Getter @Setter private String num_termo_ocorrencia;
	@Getter @Setter private Integer qtd_parcelas;
	@Getter @Setter private String sit_notificacao;
	@Getter @Setter private String tpo_forma_pagamento;
	@Getter @Setter private Double val_custos;
	@Getter @Setter private Double val_dif_consumo;
	@Getter @Setter private Double val_entrada;
	@Getter @Setter private Double val_multa;
	@Getter @Setter private Double val_regularizacao;
	@Getter @Setter private Double val_total;
	@Getter @Setter private Double val_troca_hd;

}
