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
		
		assertFaceContent(GREEN, RED,UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertFaceContent(RED, BLUE, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertFaceContent(BLUE, ORANGE, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertFaceContent(ORANGE, GREEN, UP_LEFT,UP_MIDDLE,UP_RIGHT);
	}
	
	@Test
	public void shouldHaveRedIntoGreenWhenTurningDownside() throws Exception {
		cube.turnDownClock();
		
		assertFaceContent(GREEN, RED,DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertFaceContent(RED, BLUE, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertFaceContent(BLUE, ORANGE, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertFaceContent(ORANGE, GREEN, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
	}
	
	@Test
	public void turnUpAntiClockwise() throws Exception {
		cube.turnUpAnti();
		
		assertFaceContent(GREEN, ORANGE,UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertFaceContent(ORANGE, BLUE, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertFaceContent(BLUE, RED, UP_LEFT,UP_MIDDLE,UP_RIGHT);
		assertFaceContent(RED, GREEN, UP_LEFT,UP_MIDDLE,UP_RIGHT);
	}
	
	@Test
	public void turnDownAntiClockwise() throws Exception {
		cube.turnDownAnti();
		
		assertFaceContent(GREEN, ORANGE,DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertFaceContent(ORANGE, BLUE, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertFaceContent(BLUE, RED, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertFaceContent(RED, GREEN, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
	}
	
	@Test
	public void turnRightClockwise() throws Exception {
		cube.turnRightClock();
		
		assertFaceContent(GREEN,YELLOW,UP_RIGHT,RIGHT,DOWN_RIGHT);
		assertFaceContent(WHITE,GREEN,UP_RIGHT,RIGHT,DOWN_RIGHT);
		assertFaceContent(BLUE,WHITE,UP_LEFT,LEFT,DOWN_LEFT);
		assertFaceContent(YELLOW,BLUE,UP_RIGHT,RIGHT,DOWN_RIGHT);
	}
	
	@Test
	public void turnRightAntiClockWise() throws Exception {
		cube.turnRightAnti();
		
		assertFaceContent(GREEN,WHITE,UP_RIGHT,RIGHT,DOWN_RIGHT);
		assertFaceContent(WHITE,BLUE,UP_RIGHT,RIGHT,DOWN_RIGHT);
		assertFaceContent(BLUE,YELLOW,UP_LEFT,LEFT,DOWN_LEFT);
		assertFaceContent(YELLOW,GREEN,UP_RIGHT,RIGHT,DOWN_RIGHT);
	}
	
	@Test
	public void turnLeftClockWise() throws Exception {
		cube.turnLeftClock();
		
		assertFaceContent(GREEN,WHITE,UP_LEFT,LEFT, DOWN_LEFT);
		assertFaceContent(WHITE,BLUE, UP_LEFT,LEFT, DOWN_LEFT);
		assertFaceContent(BLUE,YELLOW,UP_RIGHT,RIGHT, DOWN_RIGHT);
		assertFaceContent(YELLOW,GREEN,UP_LEFT,LEFT, DOWN_LEFT);
	}
	
	@Test
	public void turnLeftAntiClockWise() throws Exception {
		cube.turnLeftAnti();
		
		assertFaceContent(GREEN,YELLOW,UP_LEFT,LEFT, DOWN_LEFT);
		assertFaceContent(WHITE,GREEN,UP_LEFT,LEFT, DOWN_LEFT);
		assertFaceContent(BLUE,WHITE,UP_RIGHT,RIGHT, DOWN_RIGHT);
		assertFaceContent(YELLOW,BLUE,UP_LEFT,LEFT, DOWN_LEFT);
		
	}
	
	
	@Test
	public void twoMovesTest() throws Exception {
		cube.turnRightClock();
		cube.turnUpClock();

		assertThat(cube.cell(GREEN, UP_LEFT)).isEqualTo(RED);
		assertThat(cube.cell(GREEN, LEFT)).isEqualTo(GREEN);
		assertThat(cube.cell(GREEN, DOWN_LEFT)).isEqualTo(GREEN);
		assertThat(cube.cell(GREEN, UP_MIDDLE)).isEqualTo(RED);
		assertThat(cube.cell(GREEN, DOWN_MIDDLE)).isEqualTo(GREEN);
		assertThat(cube.cell(GREEN, UP_RIGHT)).isEqualTo(RED);
		assertThat(cube.cell(GREEN, RIGHT)).isEqualTo(YELLOW);
		assertThat(cube.cell(GREEN, DOWN_RIGHT)).isEqualTo(YELLOW);

		assertThat(cube.cell(RED, UP_LEFT)).isEqualTo(WHITE);
		assertThat(cube.cell(RED, LEFT)).isEqualTo(RED);
		assertThat(cube.cell(RED, DOWN_LEFT)).isEqualTo(RED);
		assertThat(cube.cell(RED, UP_MIDDLE)).isEqualTo(BLUE);
		assertThat(cube.cell(RED, DOWN_MIDDLE)).isEqualTo(RED);
		assertThat(cube.cell(RED, UP_RIGHT)).isEqualTo(BLUE);
		assertThat(cube.cell(RED, RIGHT)).isEqualTo(RED);
		assertThat(cube.cell(RED, DOWN_RIGHT)).isEqualTo(RED);
		
		assertThat(cube.cell(BLUE, UP_LEFT)).isEqualTo(ORANGE);
		assertThat(cube.cell(BLUE, LEFT)).isEqualTo(WHITE);
		assertThat(cube.cell(BLUE, DOWN_LEFT)).isEqualTo(WHITE);
		assertThat(cube.cell(BLUE, UP_MIDDLE)).isEqualTo(ORANGE);
		assertThat(cube.cell(BLUE, DOWN_MIDDLE)).isEqualTo(BLUE);
		assertThat(cube.cell(BLUE, UP_RIGHT)).isEqualTo(ORANGE);
		assertThat(cube.cell(BLUE, RIGHT)).isEqualTo(BLUE);
		assertThat(cube.cell(BLUE, DOWN_RIGHT)).isEqualTo(BLUE);
		
		assertThat(cube.cell(ORANGE, UP_LEFT)).isEqualTo(GREEN);
		assertThat(cube.cell(ORANGE, LEFT)).isEqualTo(ORANGE);
		assertThat(cube.cell(ORANGE, DOWN_LEFT)).isEqualTo(ORANGE);
		assertThat(cube.cell(ORANGE, UP_MIDDLE)).isEqualTo(GREEN);
		assertThat(cube.cell(ORANGE, DOWN_MIDDLE)).isEqualTo(ORANGE);
		assertThat(cube.cell(ORANGE, UP_RIGHT)).isEqualTo(YELLOW);
		assertThat(cube.cell(ORANGE, RIGHT)).isEqualTo(ORANGE);
		assertThat(cube.cell(ORANGE, DOWN_RIGHT)).isEqualTo(ORANGE);

		assertFaceContent(YELLOW, BLUE, UP_RIGHT,RIGHT,DOWN_RIGHT);
	}


	private void assertFaceContent(Color baseSide, Color expectedColor,CellPosition... cellPositionsExpectionNew) {
		List<CellPosition> expNew = Arrays.asList(cellPositionsExpectionNew);
		for (CellPosition cellPosition : CellPosition.values()) {
			Color expected = expNew.contains(cellPosition) ? expectedColor : baseSide;
			assertThat(cube.cell(baseSide, cellPosition)).isEqualTo(expected);
		}
	}
}
