package no.anksoft.rubiks;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class CubeSolverTest {
	@Test
	public void shouldSolveWhenOneMoveFromSolution() throws Exception {
		CubeSolver start = new CubeSolver(Cube.finished().turnFrontClock());
		assertThat(start.solve()).containsExactly(Moves.FRONT_ANTI);
	}
	
}
