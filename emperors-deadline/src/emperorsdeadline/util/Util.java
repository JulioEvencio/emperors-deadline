package emperorsdeadline.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Util {

	public static int generateRandomNumber(int x, int y) {
		return new Random().nextInt((y - x) + 1) + x;
	}

	public static String readLine(BufferedReader reader) throws IOException {
		String content = reader.readLine();

		if (content == null) {
			throw new IOException();
		}

		return content;
	}

}
