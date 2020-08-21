package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="Apprpa_Energia_Gestal")
@EqualsAndHashCode(of = "IdEnergia")
public class Apprpa_Energia_Gestal implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer IdEnergia;
	
	@Column(name = "Timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String Timestamp;

	@Column(name = "DataIndicador", nullable = true, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String DataIndicador;
	
	@ManyToOne
	@JoinColumn(name="Unidade", foreignKey = @ForeignKey(name="fk_UnidadeGestal1"))
	@Getter @Setter private Cad_Energia_Gestal Unidade;
	
	@JsonIgnore
	@OneToMany(mappedBy="Registro")
	@Getter @Setter private List<Apprpa_Energia_GestalLog> HistoricLog= new ArrayList<>();
	
	@Getter @Setter private Double AtivoFornecido;
	@Getter @Setter private Double AtivoConsumido;
	@Getter @Setter private Double AtivoConsumidoRS;
	@Getter @Setter private Double Reativo;
	@Getter @Setter private Integer Aprovacao;
	@Getter @Setter private String Aprovador;
	@Getter @Setter private String Comentario;
}
