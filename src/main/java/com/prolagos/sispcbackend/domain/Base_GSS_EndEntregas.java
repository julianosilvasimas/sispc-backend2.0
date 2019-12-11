package com.prolagos.sispcbackend.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@EqualsAndHashCode(of = "entregaId")
@Entity
@Table(name="base_gss_endentregas")
public class Base_GSS_EndEntregas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter private Integer entregaId;
	
	@Getter @Setter private String nome;
	@Getter @Setter private String rua;
	@Getter @Setter private String numero;
	@Getter @Setter private String complemento;
	@Getter @Setter private String bairro;
	@Getter @Setter private String cidade;
	@ManyToOne
	@JoinColumn(name="fk_num_ligacao",foreignKey = @ForeignKey(name="fk_num_ligacao"))
	@Getter @Setter private Base_GSS_Clientes matricula;

	
}
