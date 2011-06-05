package formater;

import java.io.IOException;

public interface FormaterFactory {

	AccFormater getAccFormater(String mimetyp) throws IOException;

}
