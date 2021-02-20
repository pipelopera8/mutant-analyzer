package co.com.mutantanalyzer.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Mutant implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
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