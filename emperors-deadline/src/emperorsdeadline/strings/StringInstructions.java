package emperorsdeadline.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import emperorsdeadline.util.Util;

public class StringInstructions {

	public static String INSTRUCTIONS = "Instructions";

	public static String LINE_1 = "01. Houses increase population";
	public static String LINE_2 = "02. The population generates gold";
	public static String LINE_3 = "03. Farms generate food";
	public static String LINE_4 = "04. Soldiers consume food";
	public static String LINE_5 = "05. Sawmills generate wood";
	public static String LINE_6 = "06. Stone mines generate stone";
	public static String LINE_7 = "07. Barracks are used to train soldiers";

	public static String LINE_8 = "08. Each house increases your population capacity by 50";
	public static String LINE_9 = "09. Each farm increases your food capacity by 50";
	public static String LINE_10 = "10. Each sawmill increases your wood capacity by 100";
	public static String LINE_11 = "11. Each stone mine increases your stone capacity by 100";

	public static void load(String language) throws IOException {
		String fileName = String.format("/language/%s/instructions.txt", language);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringError.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			StringInstructions.INSTRUCTIONS = Util.readLine(reader);

			StringInstructions.LINE_1 = Util.readLine(reader);
			StringInstructions.LINE_2 = Util.readLine(reader);
			StringInstructions.LINE_3 = Util.readLine(reader);
			StringInstructions.LINE_4 = Util.readLine(reader);
			StringInstructions.LINE_5 = Util.readLine(reader);
			StringInstructions.LINE_6 = Util.readLine(reader);
			StringInstructions.LINE_7 = Util.readLine(reader);

			StringInstructions.LINE_8 = Util.readLine(reader);
			StringInstructions.LINE_9 = Util.readLine(reader);
			StringInstructions.LINE_10 = Util.readLine(reader);
			StringInstructions.LINE_11 = Util.readLine(reader);
		}
	}
	
}
