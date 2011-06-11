package no.anksoft.rubiks;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class CubeSolverTest {
	@Test
	public void shouldSolveWhenOneMoveFromSolution() throws Exception {
		CubeSolver start = new CubeSolver(Cube.finished().turnFrontClock());
		assertThat(start.solve()).containsExactly(Moves.FRONT_ANTI);
	}

	@Test
	public void shouldHandleTwoMoves() throws Exception {
		CubeSolver start = new CubeSolver(Cube.finished().turnBackClock().turnRightAnti());
		assertThat(start.solve()).containsExactly(Moves.RIGHT_CLOCK,Moves.BACK_ANTI);
	}
	
	@Test
	public void shouldSolveFiveRandomMoves() throws Exception {
		Cube cube = Cube.finished();
		MoveGenerator moveGenerator = new MoveGenerator(cube);
		moveGenerator.doRandomMove();
		moveGenerator.doRandomMove();
		moveGenerator.doRandomMove();
		moveGenerator.doRandomMove();
		moveGenerator.doRandomMove();
		CubeSolver start = new CubeSolver(cube);
		assertThat(start.solve()).hasSize(3);
		
	}

}
