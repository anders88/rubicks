package no.anksoft.rubiks;

import java.util.Random;

public class MoveGenerator {

	private final Cube cube;
	private Random random = new Random();

	public MoveGenerator(Cube cube) {
		this.cube = cube;
	}
	
	public Cube doRandomMove() {
		int randomMove = random.nextInt(Moves.values().length);
		int moveno = 0;
		for (Moves move : Moves.values()) {
			if (randomMove == moveno++) {
				return doMove(move);
			}
		}
		throw new IllegalArgumentException("Unknown random move");
	}

	public Cube doMove(Moves move) {
		switch (move) {
		case RIGHT_CLOCK:
			return cube.turnRightClock();
		case RIGHT_ANTI:
			return cube.turnRightAnti();
		case LEFT_CLOCK:
			return cube.turnLeftClock();
		case LEFT_ANTI:
			return cube.turnLeftAnti();
		case UP_CLOCK:
			return cube.turnUpClock();
		case UP_ANTI:
			cube.turnUpAnti();
			return cube;
		case DOWN_CLOCK:
			return cube.turnDownClock();
		case DOWN_ANTI:
			return cube.turnDownAnti();
		case FRONT_CLOCK:
			return cube.turnFrontClock();
		case FRONT_ANTI:
			return cube.turnFrontAnti();
		case BACK_CLOCK:
			return cube.turnBackClock();
		case BACK_ANTI:
			return cube.turnBackAnti();
		}
		throw new IllegalArgumentException("Unsupported move " + move);

	}
}
