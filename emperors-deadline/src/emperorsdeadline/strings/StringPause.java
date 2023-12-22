package emperorsdeadline.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import emperorsdeadline.util.Util;

public class StringPause {

	public static String RESUME = "Resume";
	public static String MENU = "Menu";
	
	public static void load(String language) throws IOException {
		String fileName = String.format("/language/%s/pause.txt", language);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringError.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			StringPause.RESUME = Util.readLine(reader);
			StringPause.MENU = Util.readLine(reader);
		}
	}
	
}
