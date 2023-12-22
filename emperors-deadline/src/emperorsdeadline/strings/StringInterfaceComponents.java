package emperorsdeadline.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import emperorsdeadline.util.Util;

public class StringInterfaceComponents {

	public static String PURCHASE = "Purchase";
	public static String DESTROY = "Destroy Building";
	public static String BACK = "Back";
	public static String RECRUIT_SOLDIER = "Recruit Soldier";

	public static void load(String language) throws IOException {
		String fileName = String.format("/language/%s/interface-components.txt", language);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringError.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			StringInterfaceComponents.PURCHASE = Util.readLine(reader);
			StringInterfaceComponents.DESTROY = Util.readLine(reader);
			StringInterfaceComponents.BACK = Util.readLine(reader);
			StringInterfaceComponents.RECRUIT_SOLDIER = Util.readLine(reader);
		}
	}
	
}
