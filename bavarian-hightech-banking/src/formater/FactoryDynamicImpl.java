package formater;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
			System.out.println(e);
			return null;
		} catch (SecurityException e) {
			System.out.println(e);
			return null;
		} catch (InstantiationException e) {
			System.out.println(e);
			return null;
		} catch (IllegalAccessException e) {
			System.out.println(e);
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
	}
}
