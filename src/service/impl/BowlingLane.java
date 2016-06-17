package service.impl;

import java.util.List;

import model.Bonus;
import model.Player;
import model.Scorecard;
import service.ILane;
import util.MockBowl;

public class BowlingLane implements ILane {

	List<Player> players;

	Player winner;

	static final int MAX_TURNS = 10;

	static final int MAX_PINS = 10;

	public int turnCount = 1;
	
	public int laneNumber;

	public BowlingLane(List<Player> players, int laneNumber) {
		this.players = players;
		this.laneNumber = laneNumber;
	}

	@Override
	public void run() {
		startGame();
	}

	@Override
	public void startGame() {

		// for 1st 9 turns
		while (turnCount < MAX_TURNS) {
			for (Player player : players) {
				play(player);
			}
			turnCount++;
		}

		// for 10th turn
		for (Player player : players) {
			play10thTurn(player);
		}
		calculateWinner();
		System.out.println("And the winner is for lane " + laneNumber + " is : " + winner.name);
	}
	
	private void calculateWinner(){
		winner = players.get(0);
		for(Player player : players){
			if(player.totalScore > winner.totalScore){
				winner = player;
			}
		}
	}
	
	private void play(Player player){
		Scorecard score = new Scorecard();
		int attempt1 = MockBowl.bowl(MAX_PINS);
		score.attempt1 = attempt1;
		if (attempt1 == MAX_PINS) {
			score.bonus.add(Bonus.STRIKE);
		} else {
			int attempt2 = MockBowl.bowl(MAX_PINS - attempt1);
			score.attempt2 = attempt2;
			if (attempt1 + attempt2 == MAX_PINS) {
				score.bonus.add(Bonus.SPARE);
			}
		}
		score.updateTotalScore();
		player.scoreCard.add(score);
		player.totalScore += score.total;
	}
	
	private void play10thTurn(Player player){

		Scorecard score = new Scorecard();
		int attempt1 = MockBowl.bowl(MAX_PINS);
		score.attempt1 = attempt1;
		int pinsRemaining = MAX_PINS - attempt1;
		boolean attempt1Strike = false;
		if (attempt1 == MAX_PINS) {
			score.bonus.add(Bonus.STRIKE);
			pinsRemaining = MAX_PINS;
			attempt1Strike = true;
		}
		int attempt2 = MockBowl.bowl(pinsRemaining);
		score.attempt2 = attempt2;
		if (attempt1Strike && attempt2 == MAX_PINS) {
			score.bonus.add(Bonus.STRIKE);
			pinsRemaining = MAX_PINS;
		} else if (attempt1 + attempt2 == MAX_PINS) {
			score.bonus.add(Bonus.SPARE);
			pinsRemaining = MAX_PINS;
		}
		pinsRemaining -= attempt2;
		int attempt3 = 0;
		if (score.bonus.size() > 0) {
			attempt3 = MockBowl.bowl(pinsRemaining);
			if (attempt3 == MAX_PINS) {
				score.bonus.add(Bonus.STRIKE);
			} else if (pinsRemaining - attempt3 == 0) {
				score.bonus.add(Bonus.SPARE);
			}
		}
		score.updateTotalScore();
		score.total += attempt3;
		player.scoreCard.add(score);
		player.totalScore += score.total;
	}
}
