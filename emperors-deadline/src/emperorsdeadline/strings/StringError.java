package emperorsdeadline.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import emperorsdeadline.util.Util;

public class StringError {

	public static String ERROR = "Error";

	public static String ERROR_LOADING_SPRITES = "Error loading sprites";
	public static String ERROR_LOADING_AUDIO = "Error loading audio";

	public static void load(String language) throws IOException {
		String fileName = String.format("/language/%s/error.txt", language);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringError.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			StringError.ERROR = Util.readLine(reader);
			StringError.ERROR_LOADING_SPRITES = Util.readLine(reader);
			StringError.ERROR_LOADING_AUDIO = Util.readLine(reader);
		}
	}

}
