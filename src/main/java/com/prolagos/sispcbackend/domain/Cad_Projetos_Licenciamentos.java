package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "licencaId")
public class Cad_Projetos_Licenciamentos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Integer licencaId;
	@Getter @Setter private String licenca;
	@Getter @Setter private String descricao;
	@Getter @Setter private String tipo;
	@Getter @Setter private String orgao;
	@Getter @Setter private String status;
	@Getter @Setter private String protocolo;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate inicio;
	@Column(nullable=true)
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@Getter @Setter private LocalDate termino;
	@Getter @Setter private String link;
	
	@ManyToOne
	@JoinColumn(name="fk_projetoId",foreignKey = @ForeignKey(name="fk_projeto_licenciamentos"))
	@Getter @Setter private Cad_SisPC_Projetos projetoId;
	
}
