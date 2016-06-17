package model;

import java.util.LinkedList;
import java.util.List;

public class Scorecard {

	public int attempt1;
	
	public int attempt2;

	public List<Bonus> bonus;
	
	public int total;
	
	public Scorecard(){
		bonus = new LinkedList<>();
	}
	
	public void updateTotalScore(){
		total += attempt1 + attempt2;
		for(Bonus tmp : bonus){
			total += tmp.getPoints();
		}
	}
}
