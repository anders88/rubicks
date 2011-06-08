package no.anksoft.rubiks;

public class MoveGenerator {

	private final Cube cube;

	public MoveGenerator(Cube cube) {
		this.cube = cube;
	}

	public void doMove(Moves move) {
		switch (move) {
		case RIGHT_CLOCK:
			cube.turnRightClock();
			return;
		case RIGHT_ANTI:
			cube.turnRightAnti();
			return;
		case LEFT_CLOCK:
			cube.turnLeftClock();
			return;
		case LEFT_ANTI:
			cube.turnLeftAnti();
			return;
		case UP_CLOCK:
			cube.turnUpClock();
			return;
		case UP_ANTI:
			cube.turnUpAnti();
			return;
		case DOWN_CLOCK:
			cube.turnDownClock();
			return;
		case DOWN_ANTI:
			cube.turnDownAnti();
			return;
		case FRONT_CLOCK:
			cube.turnFrontClock();
			return;
		case FRONT_ANTI:
			cube.turnFrontAnti();
			return;
		case BACK_CLOCK:
			cube.turnBackClock();
			return;
		case BACK_ANTI:
			cube.turnBackAnti();
			return;
		}
		throw new IllegalArgumentException("Unsupported move " + move);

	}
}
