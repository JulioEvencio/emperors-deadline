package emperorsdeadline.resources;

import javax.swing.JOptionPane;

import emperorsdeadline.Game;
import emperorsdeadline.strings.StringError;
import emperorsdeadline.strings.StringGame;
import emperorsdeadline.strings.StringInstructions;
import emperorsdeadline.strings.StringInterfaceComponents;
import emperorsdeadline.strings.StringIntroduction;
import emperorsdeadline.strings.StringPause;
import emperorsdeadline.strings.StringScenario;
import emperorsdeadline.strings.StringScreen;
import emperorsdeadline.strings.StringStore;

public class Language {
	
	public static void load() {
		String language = Language.selectLanguage();

		try {
			if (!language.equals("english")) {
				StringError.load(language);
				StringInterfaceComponents.load(language);
				StringIntroduction.load(language);
				StringPause.load(language);
				StringScenario.load(language);
				StringScreen.load(language);
				StringStore.load(language);
				StringInstructions.load(language);
			}
		} catch (Exception e) {
			Game.exitWithError("Error loading files");
		}
	}
	
	private static String selectLanguage() {
		String[] options = { "English", "Português" };

		String language = (String) JOptionPane.showInputDialog(null, "", StringGame.TITLE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (language == null) {
			language = "english";
		} else if (language.equals("Português")) {
			language = "portuguese";
		} else {
			language = "english";
		}

		return language;
	}

}
