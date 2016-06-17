package driver;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.Player;
import service.ILane;
import service.impl.BowlingLane;

public class BowlingAlley {

	List<ILane> lanes;
	
	public BowlingAlley(){
		
		lanes = new LinkedList<>();
		Player pl11 = new Player("Ankur");
		Player pl12 = new Player("Ankit");
		Player pl13 = new Player("Anuj");
		List<Player> pl1 = new LinkedList<>();
		pl1.add(pl13);
		pl1.add(pl12);
		pl1.add(pl11);
		ILane l1 = new BowlingLane(pl1, 1);
		lanes.add(l1);
		
		Player pl21 = new Player("Ram");
		Player pl22 = new Player("Rajesh");
		Player pl23 = new Player("Rohan");
		List<Player> pl2 = new LinkedList<>();
		pl2.add(pl23);
		pl2.add(pl22);
		pl2.add(pl21);
		ILane l2 = new BowlingLane(pl2, 2);
		lanes.add(l2);
		
		Player pl31 = new Player("karan");
		Player pl32 = new Player("komal");
		Player pl33 = new Player("Vijay");
		List<Player> pl3 = new LinkedList<>();
		pl3.add(pl33);
		pl3.add(pl32);
		pl3.add(pl31);
		ILane l3 = new BowlingLane(pl3, 3);
		lanes.add(l3);
		
	}
	public static void main(String[] args) {
		BowlingAlley alley = new BowlingAlley();
		alley.startGame();
	}
	
	public void startGame(){
		ExecutorService cachedPool = Executors.newCachedThreadPool();
		for(ILane lane : lanes){
			cachedPool.submit(lane);
		}
	}
}
