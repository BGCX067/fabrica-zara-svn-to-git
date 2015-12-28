package helpers;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class XMLSerializationHelper {
	
	public static <T> void Serializing(T entity, String fileName) throws Exception{
		Serializer serializer = new Persister();
		File result = new File(fileName);
		serializer.write(entity, result);
	}
}
