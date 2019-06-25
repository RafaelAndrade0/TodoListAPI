package br.com.todolistapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.todolistapi.model.deserializer.LocalDateTimeDeserializer;
import br.com.todolistapi.model.serializer.LocalDateTimeSerializer;

@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarefa")
	private Long id;
	
	@Column(name = "titulo")
	@NotBlank(message = "Título é obrigatório")
	@Size(max = 100, message="Título deve ter no máximo 100 caracteres")
	private String titulo;
	
	@Column(name = "data_hora_inicio")
	@NotNull(message = "Data/Hora de início é obrigatório")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataHoraInicio;
	
	@Column(name = "data_hora_fim")
	@NotNull(message = "Data/Hora de fim é obrigatório")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataHoraFim;
	
	@Column(name = "observacao")
	@Size(max = 200, message="Observação deve ter no máximo 200 caracteres")
	private String observacao;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	/* CONSTRUCTORS */
	public Tarefa() {
		
	}
	
	public Tarefa(Long id, String titulo, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String observacao, BigDecimal valor) {
		this.id = id;
		this.titulo = titulo;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.observacao = observacao;
		this.valor = valor;
	}

	/* GETTERS AND SETTERS */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public LocalDateTime getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(LocalDateTime dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/* EQUALS AND HASHCODE */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/* TO STRING */
	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", titulo=" + titulo + ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim="
				+ dataHoraFim + ", observacao=" + observacao + ", valor=" + valor + "]";
	}
	
}
