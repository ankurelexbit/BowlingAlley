package model;

public enum Bonus {

	STRIKE(10), SPARE(5), NO_BONUS(0);
	
	private int points;
	
	private Bonus(int points){
		this.points = points;
	}

	public int getPoints() {
		return points;
	}
	
}
