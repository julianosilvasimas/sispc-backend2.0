package com.prolagos.sispcbackend.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
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

@NoArgsConstructor
@Entity
@Table(name="Apprpa_Energia_GestalLog")
@EqualsAndHashCode(of = "IdLog")
public class Apprpa_Energia_GestalLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Integer IdLog;
	
	@Column(name = "Timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Getter @Setter private String Timestamp;

	@Column(name = "DataIndicador", nullable = true, updatable = true, insertable = true, columnDefinition = "DATE")
	@Getter @Setter private String DataIndicador;

	@ManyToOne
	@JoinColumn(name="Registro", foreignKey = @ForeignKey(name="fk_UnidadeGestal2"))
	@Setter private Apprpa_Energia_Gestal Registro;
	
	@Getter @Setter private Double AtivoConsumidoAntigo;
	@Getter @Setter private Double AtivoConsumidoNovo;
	@Getter @Setter private String Usuario;
	
	public Apprpa_Energia_GestalLog(String dataIndicador,
			Apprpa_Energia_Gestal registro, Double ativoConsumidoAntigo, Double ativoConsumidoNovo, String usuario) {
		super();
		DataIndicador = dataIndicador;
		Registro = registro;
		AtivoConsumidoAntigo = ativoConsumidoAntigo;
		AtivoConsumidoNovo = ativoConsumidoNovo;
		Usuario = usuario;
	}
	
	
	
	
}
