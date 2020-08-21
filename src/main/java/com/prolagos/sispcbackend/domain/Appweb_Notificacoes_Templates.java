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
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appweb_Notificacoes_Templates implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer id;

	@Getter @Setter private String NomeDoTemplate;
	@Getter @Setter private String Assunto;
	@Getter @Setter private String Texto;

	
	
}
