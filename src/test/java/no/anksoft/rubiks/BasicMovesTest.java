package no.anksoft.rubiks;

import static no.anksoft.rubiks.Color.*;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class BasicMovesTest {
	private Cube cube = Cube.finished();

	@Test
	public void finishedCubeShouldHaveSameColor() throws Exception {
		assertThat(cube.cell(GREEN,CellPosition.UP_LEFT)).isEqualTo(GREEN);
	}
	
	@Test
	public void shouldHaveRedIntoGreenWhenTurningUpside() throws Exception {
		cube.turnUpClock();
		
		assertUpMove(GREEN, RED);
		assertUpMove(RED, BLUE);
		assertUpMove(BLUE, ORANGE);
		assertUpMove(ORANGE, GREEN);
	}

	private void assertUpMove(Color baseSide, Color expectedColor) {
		assertThat(cube.cell(baseSide,CellPosition.UP_LEFT)).isEqualTo(expectedColor);
		assertThat(cube.cell(baseSide,CellPosition.UP_MIDDLE)).isEqualTo(expectedColor);
		assertThat(cube.cell(baseSide,CellPosition.UP_RIGHT)).isEqualTo(expectedColor);
	}
}
