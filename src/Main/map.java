package Main;

public class map {
	public enum EntityList {
		GRASS(0), TREE(1), WATER(2), ROCK(3), BRIDGE(4), EARTH(5), NEWLANDDOOR(9);

		private final int value;

		private EntityList(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
