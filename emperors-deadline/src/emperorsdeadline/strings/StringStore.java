package emperorsdeadline.strings;

public class StringStore {

	public static String PURCHASED = "Purchased";
	public static String INSUFFICIENT_RESOURCES = "Insufficient resources";

	public static String getPriceFarm(int gold, int wood) {
		return String.format("Farm: %d Gold and %d Wood", gold, wood);
	}

	public static String getPriceHouse(int gold, int wood) {
		return String.format("House: %d Gold and %d Wood", gold, wood);
	}

	public static String getPriceSawmill(int gold) {
		return String.format("Sawmill: %d Gold", gold);
	}

	public static String getPriceStoneMine(int gold, int wood) {
		return String.format("Stone Mine: %d Gold and %d Wood", gold, wood);
	}

	public static String getPriceBarracks(int gold, int wood, int stone) {
		return String.format("Barracks: %d Gold, %d Wood and %d stone", gold, wood, stone);
	}

}
