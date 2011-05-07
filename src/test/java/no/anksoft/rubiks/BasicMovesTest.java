package no.anksoft.rubiks;

import static no.anksoft.rubiks.CellPosition.DOWN_LEFT;
import static no.anksoft.rubiks.CellPosition.DOWN_MIDDLE;
import static no.anksoft.rubiks.CellPosition.DOWN_RIGHT;
import static no.anksoft.rubiks.CellPosition.UP_LEFT;
import static no.anksoft.rubiks.CellPosition.UP_MIDDLE;
import static no.anksoft.rubiks.CellPosition.*;
import static no.anksoft.rubiks.Color.*;
import static no.anksoft.rubiks.Color.GREEN;
import static no.anksoft.rubiks.Color.ORANGE;
import static no.anksoft.rubiks.Color.RED;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

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
		
		assertMove(GREEN, RED,UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertMove(RED, BLUE, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertMove(BLUE, ORANGE, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertMove(ORANGE, GREEN, UP_LEFT,UP_MIDDLE,UP_RIGHT);
	}
	
	@Test
	public void shouldHaveRedIntoGreenWhenTurningDownside() throws Exception {
		cube.turnDownClock();
		
		assertMove(GREEN, RED,DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertMove(RED, BLUE, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertMove(BLUE, ORANGE, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertMove(ORANGE, GREEN, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
	}
	
	@Test
	public void turnUpAntiClockwise() throws Exception {
		cube.turnUpAnti();
		
		assertMove(GREEN, ORANGE,UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertMove(ORANGE, BLUE, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertMove(BLUE, RED, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertMove(RED, GREEN, UP_LEFT,UP_MIDDLE,UP_RIGHT);
	}
	
	@Test
	public void turnDownAntiClockwise() throws Exception {
		cube.turnDownAnti();
		
		assertMove(GREEN, ORANGE,DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertMove(ORANGE, BLUE, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertMove(BLUE, RED, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertMove(RED, GREEN, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
	}
	
	@Test
	public void turnRightClockwise() throws Exception {
		cube.turnRightClock();
		
		assertMove(GREEN,YELLOW,UP_RIGHT,RIGHT,DOWN_RIGHT);
		assertMove(WHITE,GREEN,UP_RIGHT,RIGHT,DOWN_RIGHT);
		assertMove(BLUE,WHITE,UP_LEFT,LEFT,DOWN_LEFT);
		assertMove(YELLOW,BLUE,UP_RIGHT,RIGHT,DOWN_RIGHT);
	}
	
	@Test
	public void turnRightAntiClockWise() throws Exception {
		cube.turnRightAnti();
		
		assertMove(GREEN,WHITE,UP_RIGHT,RIGHT,DOWN_RIGHT);
		assertMove(WHITE,BLUE,UP_RIGHT,RIGHT,DOWN_RIGHT);
		assertMove(BLUE,YELLOW,UP_LEFT,LEFT,DOWN_LEFT);
		assertMove(YELLOW,GREEN,UP_RIGHT,RIGHT,DOWN_RIGHT);
	}
	
	@Test
	public void turnLeftClockWise() throws Exception {
		cube.turnLeftClock();
		
		assertMove(GREEN,WHITE,UP_LEFT,LEFT, DOWN_LEFT);
		assertMove(WHITE,BLUE, UP_LEFT,LEFT, DOWN_LEFT);
		assertMove(BLUE,YELLOW,UP_RIGHT,RIGHT, DOWN_RIGHT);
		assertMove(YELLOW,GREEN,UP_LEFT,LEFT, DOWN_LEFT);
	}
	
	
	


	private void assertMove(Color baseSide, Color expectedColor,CellPosition... cellPositionsExpectionNew) {
		List<CellPosition> expNew = Arrays.asList(cellPositionsExpectionNew);
		for (CellPosition cellPosition : CellPosition.values()) {
			Color expected = expNew.contains(cellPosition) ? expectedColor : baseSide;
			assertThat(cube.cell(baseSide, cellPosition)).isEqualTo(expected);
		}
	}
}
