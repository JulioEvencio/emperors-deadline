package emperorsdeadline.util;

import java.util.Random;

public class Util {

	public static int generateRandomNumber(int x, int y) {
		return new Random().nextInt((y - x) + 1) + x;
	}

}
