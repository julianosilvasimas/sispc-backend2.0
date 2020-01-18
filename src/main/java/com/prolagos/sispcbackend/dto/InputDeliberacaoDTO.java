package com.prolagos.sispcbackend.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class InputDeliberacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String processo;
	private Integer deliberacao;
	private Integer idIrregularidade;
	private Date dataJulgado;
	private Date dataAviso1;
	private Date dataAviso2;
	private Date dataAviso3;
	private Integer mesRetroativo;
	private String titular;
	private String usuarioPresente;
	private Integer contrato;
	private Integer num_ligacao;
	private String carta;
	private String cartaProcedente;
	private String ro;
	private String num_termo;
	private String colaborador;
	private String protocolo;
	private String cartacedoc;

}

