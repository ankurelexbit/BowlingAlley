package model;

public enum Tries {

	TRY1(1), TRY2(2), TRY3(3);
	
	private int count;
	
	private Tries(int count){
		this.count = count;
	}

	public int getCount() {
		return count;
	}
	
}
