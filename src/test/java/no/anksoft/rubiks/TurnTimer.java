package no.anksoft.rubiks;

public class TurnTimer {
	
	private static final int NUM_TURNS = 1000000;
	private static final int REPORT_INTERVAL = 50000;

	public static void main(String[] args) {
		Cube cube = Cube.finished();
		long startedAt = System.currentTimeMillis();
		MoveGenerator moveGenerator = new MoveGenerator(cube);
		for (int moveno=0;moveno<NUM_TURNS;moveno++) {
			for (Moves move : Moves.values()) {
				moveGenerator.doMove(move);
			}
			if (moveno % REPORT_INTERVAL == 0) {
				System.out.println("At " + moveno);
			}
		}
		long timed = System.currentTimeMillis() - startedAt;
		System.out.println("Time: " + timed);
	}

}
