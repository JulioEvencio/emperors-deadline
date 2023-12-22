package emperorsdeadline.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import emperorsdeadline.scenarios.world.World;
import emperorsdeadline.util.Util;

public class StringIntroduction {

	public static String INTRODUCTION = "Introduction";

	public static String LINE_1 = "Assistant:";
	public static String LINE_2 = "Hello, Count, I mean... You're no longer a count since yesterday when";
	public static String LINE_3 = "you declared independence from the Empire. Now the emperor is";
	public static String LINE_4 = String.format("furious and demands our surrender. He has given us a deadline of %d days", World.DAYS);
	public static String LINE_5 = "to surrender, but of course, we're not going to do that.";
	public static String LINE_6 = String.format("We will use these %d days to fortify our defenses", World.DAYS);
	public static String LINE_7 = "and recruit soldiers. Independence or death!";

	public static String LINE_8 = "Objective: Recruit as many soldiers as possible by the day of the attack.";
	public static String LINE_9 = "How to play: Use the mouse to build.";

	public static String CONTINUE = "Press ENTER to continue";

	public static void load(String language) throws IOException {
		String fileName = String.format("/language/%s/introduction.txt", language);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringError.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			StringIntroduction.INTRODUCTION = Util.readLine(reader);
			
			StringIntroduction.LINE_1 = Util.readLine(reader);
			StringIntroduction.LINE_2 = Util.readLine(reader);
			StringIntroduction.LINE_3 = Util.readLine(reader);
			StringIntroduction.LINE_4 = Util.readLine(reader).replace("${WORLD_DAYS}", String.valueOf(World.DAYS));
			StringIntroduction.LINE_5 = Util.readLine(reader);
			StringIntroduction.LINE_6 = Util.readLine(reader).replace("${WORLD_DAYS}", String.valueOf(World.DAYS));
			StringIntroduction.LINE_7 = Util.readLine(reader);
			StringIntroduction.LINE_8 = Util.readLine(reader);
			StringIntroduction.LINE_9 = Util.readLine(reader);
			
			StringIntroduction.CONTINUE = Util.readLine(reader);
		}
	}
	
}
