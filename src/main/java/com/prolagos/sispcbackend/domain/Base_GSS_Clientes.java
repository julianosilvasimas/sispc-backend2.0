package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@EqualsAndHashCode(of = "clienteId")
@Entity
@Table(name="base_gss_clientes")
public class Base_GSS_Clientes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="seq_responsavel")
	@Getter @Setter private Integer clienteId;
	
	@Column(name="num_ligacao")
	@Getter @Setter private Integer matricula;
	@Column(length=50)
	@Getter @Setter private String categoria;
	@Column(length=50)
	@Getter @Setter private String cidade;
	@Column(length=50)
	@Getter @Setter private String classe_agua;
	@Column(length=50)
	@Getter @Setter private String classe_esgoto;
	@Column(length=50)
	@Getter @Setter private String classificacao;
	@Column(length=50)
	@Getter @Setter private String cod_cep;
	@Column(length=50)
	@Getter @Setter private String cod_classe_ligacao_agua;
	@Column(length=50)
	@Getter @Setter private String cod_classe_ligacao_esg;
	@Column(length=50)
	@Getter @Setter private String cod_grupo;
	@Column(length=50)
	@Getter @Setter private String cod_latitude;
	@Column(length=50)
	@Getter @Setter private String cod_longitude;
	@Column(length=50)
	@Getter @Setter private String cod_rota_leitura;
	@Column(length=50)
	@Getter @Setter private String cod_setor_comercial;
	@Column(length=50)
	@Getter @Setter private String data_instalacao;
	@Column(length=50)
	@Getter @Setter private String dat_ativacao;
	@Column(length=50)
	@Getter @Setter private String dat_encerramento;
	@Column(length=50)
	@Getter @Setter private String dat_instalacao;
	@Column(length=50)
	@Getter @Setter private String dat_nascimento;
	@Column(length=50)
	@Getter @Setter private String debito_automatico;
	@Column(length=50)
	@Getter @Setter private String diretoria;
	@Column(length=50)
	@Getter @Setter private String dsc_complemento;
	@Getter @Setter private String dsc_email;
	@Column(length=50)
	@Getter @Setter private String dsc_email_2;
	@Column(length=50)
	@Getter @Setter private String empresa;

	@ManyToOne
	@JoinColumn(name="fk_emp_codigo",foreignKey = @ForeignKey(name="fk_unidade_clientes"))
	@Getter @Setter private Cad_SisPC_Unidades undcodigo;
	
	@Getter @Setter private String end_ligacao;
	@Column(length=50)
	@Getter @Setter private String nom_bairro;
	@Getter @Setter private String nom_cliente;
	@Getter @Setter private String nom_logradouro;
	@Getter @Setter private String nom_mae;
	@Getter @Setter private String nom_pai;
	@Column(length=50)
	@Getter @Setter private String nro;
	@Column(length=50)
	@Getter @Setter private String num_celular;
	@Column(length=50)
	@Getter @Setter private String num_celular_2;
	@Column(length=50)
	@Getter @Setter private String num_comercial;
	@Column(length=50)
	@Getter @Setter private String num_doc_1;
	@Column(length=50)
	@Getter @Setter private String num_doc_2;
	@Column(length=50)
	@Getter @Setter private String num_lote;
	@Column(length=50)
	@Getter @Setter private String num_medidor;
	@Column(length=50)
	@Getter @Setter private String num_medidor_master;
	@Column(length=50)
	@Getter @Setter private String num_quadra;
	@Column(length=50)
	@Getter @Setter private String num_recado;
	@Column(length=50)
	@Getter @Setter private String num_residencial;
	@Column(length=50)
	@Getter @Setter private String perfil;
	@Column(length=50)
	@Getter @Setter private String qtd_economias_com;
	@Column(length=50)
	@Getter @Setter private String qtd_economias_ind;
	@Column(length=50)
	@Getter @Setter private String qtd_economias_out;
	@Column(length=50)
	@Getter @Setter private String qtd_economias_pub;
	@Column(length=50)
	@Getter @Setter private String qtd_economias_res;
	@Column(length=50)
	@Getter @Setter private String referencia;
	@Column(length=50)
	@Getter @Setter private String remessa;
	@Column(length=50)
	@Getter @Setter private String retencao_imposto;
	@Column(length=50)
	@Getter @Setter private String seq_rota;
	@Column(length=50)
	@Getter @Setter private String sit_contrato;
	@Column(length=50)
	@Getter @Setter private String sit_lig;
	@Column(length=50)
	@Getter @Setter private String sub_categoria;
	@Column(length=50)
	@Getter @Setter private String tipo_consumidor;
	@Column(length=50)
	@Getter @Setter private String tipo_doc_pri;
	@Column(length=50)
	@Getter @Setter private String tipo_doc_sec;
	@Column(length=50)
	@Getter @Setter private String tipo_entrega;
	@Column(length=50)
	@Getter @Setter private String tipo_faturamento;
	@Column(length=50)
	@Getter @Setter private String tipo_ligacao;
	@Column(length=50)
	@Getter @Setter private String tpo_fonte;
	@Column(length=50)
	@Getter @Setter private String ultimo_contrato;
	@Column(length=50)
	@Getter @Setter private String utilizacao;
	@Column(length=50)
	@Getter @Setter private Integer vl_vol_caixa;
	@Column(length=50)
	@Getter @Setter private Integer vl_vol_cisterna;
	@Column(length=50)
	@Getter @Setter private Integer vl_vol_piscina;
	
	@JsonIgnore
	@OneToMany(mappedBy="contrato")
	@Getter @Setter private List<Base_GSS_Irregularidades> irregularidades= new  ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="matricula")
	@Getter @Setter private List<Base_GSS_EndEntregas> entregas = new  ArrayList<>();



}
