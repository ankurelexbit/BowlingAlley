package util;

public class MockBowl {

	public static int bowl(int pinsRemaining) {
		return (int) (Math.random() * pinsRemaining);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(bowl(10));
		}
	}
}
