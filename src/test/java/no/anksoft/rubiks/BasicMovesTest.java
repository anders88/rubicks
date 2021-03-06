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
	public void turnFrontClockWise() throws Exception {
		cube.turnFrontClock();
		
		assertFaceContent(GREEN, GREEN);
		
		assertFaceContent(WHITE, ORANGE, DOWN_LEFT, DOWN_MIDDLE, DOWN_RIGHT);
		assertFaceContent(RED, WHITE, UP_LEFT, LEFT, DOWN_LEFT);
		assertFaceContent(YELLOW, RED, UP_LEFT, UP_MIDDLE, UP_RIGHT);
		assertFaceContent(ORANGE, YELLOW, UP_RIGHT, RIGHT, DOWN_RIGHT);
		
	}
	
	@Test
	public void turnFrontAntiClockwise() throws Exception {
		cube.turnFrontAnti();
		
		assertFaceContent(GREEN, GREEN);
		
		assertFaceContent(WHITE, RED, DOWN_LEFT, DOWN_MIDDLE, DOWN_RIGHT);
		assertFaceContent(RED, YELLOW, UP_LEFT, LEFT, DOWN_LEFT);
		assertFaceContent(YELLOW, ORANGE, UP_LEFT, UP_MIDDLE, UP_RIGHT);
		assertFaceContent(ORANGE, WHITE, UP_RIGHT, RIGHT, DOWN_RIGHT);
		
	}
	
	@Test
	public void turnBackClockwise() throws Exception {
		cube.turnBackClock();
		
		assertFaceContent(GREEN, GREEN);
		assertFaceContent(BLUE, BLUE);
		
		assertFaceContent(WHITE, RED, UP_LEFT, UP_MIDDLE, UP_RIGHT);
		assertFaceContent(RED, YELLOW, UP_RIGHT, RIGHT, DOWN_RIGHT);
		assertFaceContent(YELLOW, ORANGE, DOWN_LEFT, DOWN_MIDDLE, DOWN_RIGHT);
		assertFaceContent(ORANGE, WHITE, UP_LEFT, LEFT, DOWN_LEFT);
		
	}
	
	@Test
	public void turnBackAntiClockwise() throws Exception {
		cube.turnBackAnti();
		
		assertFaceContent(GREEN, GREEN);
		assertFaceContent(BLUE, BLUE);
		
		assertFaceContent(WHITE, ORANGE, UP_LEFT, UP_MIDDLE, UP_RIGHT);
		assertFaceContent(RED, WHITE, UP_RIGHT, RIGHT, DOWN_RIGHT);
		assertFaceContent(YELLOW, RED, DOWN_LEFT, DOWN_MIDDLE, DOWN_RIGHT);
		assertFaceContent(ORANGE, YELLOW, UP_LEFT, LEFT, DOWN_LEFT);
		
	}
	

	private void assertFaceContent(Color baseSide, Color expectedColor,CellPosition... cellPositionsExpectionNew) {
		List<CellPosition> expNew = Arrays.asList(cellPositionsExpectionNew);
		for (CellPosition cellPosition : CellPosition.values()) {
			Color expected = expNew.contains(cellPosition) ? expectedColor : baseSide;
			assertThat(cube.cell(baseSide, cellPosition)).isEqualTo(expected);
		}
	}
}
