package model;

import java.util.LinkedList;
import java.util.List;

public class Player {

	public String name;
	
	public List<Scorecard> scoreCard;
	
	public int totalScore;

	public Player(String name) {
		this.name = name;
		scoreCard = new LinkedList<>();
	}
	
}
