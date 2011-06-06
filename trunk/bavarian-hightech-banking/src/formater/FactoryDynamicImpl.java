package formater;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import bavaria.hightech.exceptions.FormaterFailException;

public class FactoryDynamicImpl implements FormaterFactory {

	@Override
	public AccFormater getAccFormater(String mimetyp) throws IOException {

		Properties properties = new Properties();
		BufferedInputStream stream = new BufferedInputStream(
				new FileInputStream("formater.properties"));
		properties.load(stream);
		stream.close();
		String mimeTyp = properties.getProperty(mimetyp);

		try {
			return (AccFormater) Class.forName(mimeTyp).newInstance();
		} catch (IllegalArgumentException e) {
			throw new FormaterFailException("formation failed");
		} catch (SecurityException e) {
			throw new FormaterFailException("formation failed");
		} catch (InstantiationException e) {
			throw new FormaterFailException("formation failed");
		} catch (IllegalAccessException e) {
			throw new FormaterFailException("instance not found");
		} catch (ClassNotFoundException e) {
			throw new FormaterFailException("creat new formater class");
		}
	}
}
