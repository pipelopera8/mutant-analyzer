package co.com.mutantanalyzer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import co.com.mutantanalyzer.general.util.BooleanToStringConverter;

@Entity
@Table(name = "TMA_ADN")
public class Mutant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CDADN")
	private Long id;
	
	@Convert(converter = BooleanToStringConverter.class)
	@Column(name = "SNMUTANTE")
	private Boolean mutant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getMutant() {
		return mutant;
	}

	public void setMutant(Boolean mutant) {
		this.mutant = mutant;
	}

	
}