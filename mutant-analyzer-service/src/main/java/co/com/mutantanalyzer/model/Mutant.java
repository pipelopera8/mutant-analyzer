package co.com.mutantanalyzer.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import co.com.mutantanalyzer.general.util.BooleanToStringConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "TMA_ADN")
@Data
@Slf4j
public class Mutant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CDADN")
	private Long id;
	
	@Convert(converter = BooleanToStringConverter.class)
	@Column(name = "SNMUTANTE")
	private Boolean mutant;

	public byte[] toByteArray(Mutant mutant) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(mutant);
			out.flush();
			byte[] bytes = bos.toByteArray();
			return bytes;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		} finally {
			try {
				bos.close();
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return null;
	}
}