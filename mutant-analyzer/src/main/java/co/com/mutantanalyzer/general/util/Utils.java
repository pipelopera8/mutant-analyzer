package co.com.mutantanalyzer.general.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public Object byteToObject(byte[] obj) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(obj);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}
}
