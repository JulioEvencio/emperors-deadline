package emperorsdeadline.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import emperorsdeadline.util.Util;

public class StringScreen {

	public static String NEW_GAME = "New Game";
	public static String TUTORIAL = "Tutorial";
	public static String CREDITS = "Credits";
	public static String EXIT = "Exit";

	public static String PAUSE = "1. ESC or P -> use to pause the game";
	public static String SHOW_HIDE_FPS = "2. F3 -> to show/hide FPS";
	public static String ENABLE_DISABLE_MUSIC = "3. F4 -> to enable/disable music";

	public static String PROGRAMMER = "-> Programmer: Júlio Igreja";
	public static String PROGRAMMER_GITHUB = "-> Access: https://github.com/JulioEvencio";

	public static String RETURN_TO_MENU = "Press ENTER to go back to main menu";
	public static String NAVIGATE_THE_MENU = "Use W and S keys to move and ENTER to select";

	public static String VICTORY = "Victory";
	public static String VICTORY_STRING = "Congratulations, you defeated the Empire!";

	public static String GAME_OVER = "Game Over";
	public static String GAME_OVER_STRING = "The Empire has defeated you...";
	
	public static void load(String language) throws IOException {
		String fileName = String.format("/language/%s/screen.txt", language);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringError.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			StringScreen.NEW_GAME = Util.readLine(reader);
			StringScreen.TUTORIAL = Util.readLine(reader);
			StringScreen.CREDITS = Util.readLine(reader);
			StringScreen.EXIT = Util.readLine(reader);

			StringScreen.PAUSE = Util.readLine(reader);
			StringScreen.SHOW_HIDE_FPS = Util.readLine(reader);
			StringScreen.ENABLE_DISABLE_MUSIC = Util.readLine(reader);

			StringScreen.PROGRAMMER = Util.readLine(reader);
			StringScreen.PROGRAMMER_GITHUB = Util.readLine(reader);

			StringScreen.RETURN_TO_MENU = Util.readLine(reader);
			StringScreen.NAVIGATE_THE_MENU = Util.readLine(reader);

			StringScreen.VICTORY = Util.readLine(reader);
			StringScreen.VICTORY_STRING = Util.readLine(reader);

			StringScreen.GAME_OVER = Util.readLine(reader);
			StringScreen.GAME_OVER_STRING = Util.readLine(reader);
		}
	}

}
