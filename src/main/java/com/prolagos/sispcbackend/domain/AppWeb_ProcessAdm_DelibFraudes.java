package com.prolagos.sispcbackend.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "processo")
@Entity
@Table(name="appweb_processadm_delibfraudes")
public class AppWeb_ProcessAdm_DelibFraudes {

	// Chave = ano.mes.matricula.deliberacao
	//         0000.00-000000/000000
	
	@Id
	@Getter @Setter private String processo;
	@Getter @Setter private Integer deliberacao;
	@Column(name="seq_irregularidade")
	@Getter @Setter private Integer idIrregularidade;
	@Getter @Setter private LocalDateTime dataJulgado;
	@Getter @Setter private LocalDateTime dataAviso1;
	@Getter @Setter private LocalDateTime dataAviso2;
	@Getter @Setter private LocalDateTime dataAviso3;
	@Getter @Setter private Integer mesRetroativo;
	@Getter @Setter private String titular;
	@Getter @Setter private String usuarioPresente;
	@Column(name="seq_responsavel")
	@Getter @Setter private Integer contrato;
	@Getter @Setter private Integer num_ligacao;
	@Getter @Setter private String carta;
	@Getter @Setter private String cartaProcedente;
	@Getter @Setter private String ro;
	@Getter @Setter private String num_termo;
	@Getter @Setter private String colaborador;
	
}
