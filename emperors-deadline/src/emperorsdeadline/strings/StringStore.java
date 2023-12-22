package emperorsdeadline.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import emperorsdeadline.util.Util;

public class StringStore {

	public static String PURCHASED = "Purchased";
	public static String INSUFFICIENT_RESOURCES = "Insufficient resources";

	public static String DO_YOU_WANT_TO_DESTROY_THE_BUILDING = "Do you want to destroy the building?";
	public static String THE_BUILDING_WAS_DESTROYED = "The building was destroyed";

	public static String RECRUITED = "recruited";
	
	private static String PRICE_FARM = "Farm: %d Gold and %d Wood";
	private static String PRICE_HOUSE = "House: %d Gold and %d Wood";
	private static String PRICE_SAWMILL = "Sawmill: %d Gold";
	private static String PRICE_STONE_MINE = "Stone Mine: %d Gold and %d Wood";
	private static String PRICE_BARRACKS = "Barracks: %d Gold, %d Wood and %d stone";
	private static String PRICE_SOLDIERS = "%dx - Soldiers: %d Gold and %d population";

	public static String getPriceFarm(int gold, int wood) {
		return String.format(StringStore.PRICE_FARM, gold, wood);
	}

	public static String getPriceHouse(int gold, int wood) {
		return String.format(StringStore.PRICE_HOUSE, gold, wood);
	}

	public static String getPriceSawmill(int gold) {
		return String.format(StringStore.PRICE_SAWMILL, gold);
	}

	public static String getPriceStoneMine(int gold, int wood) {
		return String.format(StringStore.PRICE_STONE_MINE, gold, wood);
	}

	public static String getPriceBarracks(int gold, int wood, int stone) {
		return String.format(StringStore.PRICE_BARRACKS, gold, wood, stone);
	}

	public static String getPriceSoldiers(int gold, int population) {
		return String.format(StringStore.PRICE_SOLDIERS, population, gold, population);
	}

	public static void load(String language) throws IOException {
		String fileName = String.format("/language/%s/store.txt", language);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(StringError.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			StringStore.PURCHASED = Util.readLine(reader);
			StringStore.INSUFFICIENT_RESOURCES = Util.readLine(reader);
			
			StringStore.DO_YOU_WANT_TO_DESTROY_THE_BUILDING = Util.readLine(reader);
			StringStore.THE_BUILDING_WAS_DESTROYED = Util.readLine(reader);
			
			StringStore.RECRUITED = Util.readLine(reader);
			
			StringStore.PRICE_FARM = Util.readLine(reader);
			StringStore.PRICE_HOUSE = Util.readLine(reader);
			StringStore.PRICE_SAWMILL = Util.readLine(reader);
			StringStore.PRICE_STONE_MINE = Util.readLine(reader);
			StringStore.PRICE_BARRACKS = Util.readLine(reader);
			StringStore.PRICE_SOLDIERS = Util.readLine(reader);
		}
	}
	
}
