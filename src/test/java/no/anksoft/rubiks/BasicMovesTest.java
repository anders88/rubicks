package no.anksoft.rubiks;

import static no.anksoft.rubiks.CellPosition.DOWN_LEFT;
import static no.anksoft.rubiks.CellPosition.DOWN_MIDDLE;
import static no.anksoft.rubiks.CellPosition.DOWN_RIGHT;
import static no.anksoft.rubiks.CellPosition.UP_LEFT;
import static no.anksoft.rubiks.CellPosition.UP_MIDDLE;
import static no.anksoft.rubiks.CellPosition.UP_RIGHT;
import static no.anksoft.rubiks.Color.BLUE;
import static no.anksoft.rubiks.Color.GREEN;
import static no.anksoft.rubiks.Color.ORANGE;
import static no.anksoft.rubiks.Color.RED;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class BasicMovesTest {
	private Cube cube = Cube.finished();

	@Test
	public void finishedCubeShouldHaveSameColor() throws Exception {
		assertThat(cube.cell(GREEN,CellPosition.UP_LEFT)).isEqualTo(GREEN);
	}
	
	@Test
	@Ignore
	public void shouldHaveRedIntoGreenWhenTurningUpside() throws Exception {
		cube.turnUpClock();
		
		assertUpMove(GREEN, RED,UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertUpMove(RED, BLUE, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertUpMove(BLUE, ORANGE, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertUpMove(ORANGE, GREEN, UP_LEFT,UP_MIDDLE,UP_RIGHT);
	}
	
	@Test
	public void shouldHaveRedIntoGreenWhenTurningDownside() throws Exception {
		cube.turnDownClock();
		
		assertUpMove(GREEN, RED,DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertUpMove(RED, BLUE, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertUpMove(BLUE, ORANGE, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertUpMove(ORANGE, GREEN, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
	}
	
	@Test
	@Ignore
	public void turnUpAntiClockwise() throws Exception {
		cube.turnUpAnti();
		
	}


	private void assertUpMove(Color baseSide, Color expectedColor,CellPosition... cellPositionsExpectionNew) {
		List<CellPosition> expNew = Arrays.asList(cellPositionsExpectionNew);
		for (CellPosition cellPosition : CellPosition.values()) {
			Color expected = expNew.contains(cellPosition) ? expectedColor : baseSide;
			assertThat(cube.cell(baseSide, cellPosition)).isEqualTo(expected);
		}
	}
}
