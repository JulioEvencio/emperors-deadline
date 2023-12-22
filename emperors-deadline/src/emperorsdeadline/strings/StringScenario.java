package emperorsdeadline.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import emperorsdeadline.util.Util;

public class StringScenario {

	public static String GOLD = "Gold";
	public static String SOLDIERS = "Soldiers";

	public static String FOOD = "Food";
	public static String POPULATION = "Population";

	public static String STONE = "Stone";
	public static String WOOD = "Wood";

	public static String DAYS_REMAINING = "Days remaining";
	public static String TIME = "Time";
	
	public static void load(String language) throws IOException {
		String fileName = String.format("/language/%s/scenario.txt", language);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringError.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			StringScenario.GOLD = Util.readLine(reader);
			StringScenario.SOLDIERS = Util.readLine(reader);
			StringScenario.FOOD = Util.readLine(reader);
			StringScenario.POPULATION = Util.readLine(reader);
			StringScenario.STONE = Util.readLine(reader);
			StringScenario.WOOD = Util.readLine(reader);
			StringScenario.DAYS_REMAINING = Util.readLine(reader);
			StringScenario.TIME = Util.readLine(reader);
		}
	}

}
