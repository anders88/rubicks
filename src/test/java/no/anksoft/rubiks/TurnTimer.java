package no.anksoft.rubiks;

import java.util.HashSet;
import java.util.Set;

public class TurnTimer {
	
	private static final int NUM_TURNS = 1000000;
	private static final int REPORT_INTERVAL = 1000;

	public static void main(String[] args) {
		Cube cube = Cube.finished();
		long startedAt = System.currentTimeMillis();
		MoveGenerator moveGenerator = new MoveGenerator(cube);
		Set<Cube> allMoves = new HashSet<Cube>();
		for (int moveno=0;moveno<NUM_TURNS;moveno++) {
			moveGenerator.doRandomMove();
			allMoves.add(cube.clone());
			if (moveno % REPORT_INTERVAL == 0) {
				System.out.println("At " + moveno + " have collected " + allMoves.size());
			}
		}
		long timed = System.currentTimeMillis() - startedAt;
		System.out.println("Time: " + timed + " collected " + allMoves.size());
	}

}
