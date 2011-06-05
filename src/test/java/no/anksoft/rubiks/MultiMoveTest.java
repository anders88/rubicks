package no.anksoft.rubiks;

import static no.anksoft.rubiks.CellPosition.DOWN_LEFT;
import static no.anksoft.rubiks.CellPosition.DOWN_MIDDLE;
import static no.anksoft.rubiks.CellPosition.DOWN_RIGHT;
import static no.anksoft.rubiks.CellPosition.LEFT;
import static no.anksoft.rubiks.CellPosition.RIGHT;
import static no.anksoft.rubiks.CellPosition.UP_LEFT;
import static no.anksoft.rubiks.CellPosition.UP_MIDDLE;
import static no.anksoft.rubiks.CellPosition.UP_RIGHT;
import static no.anksoft.rubiks.Color.BLUE;
import static no.anksoft.rubiks.Color.GREEN;
import static no.anksoft.rubiks.Color.ORANGE;
import static no.anksoft.rubiks.Color.RED;
import static no.anksoft.rubiks.Color.WHITE;
import static no.anksoft.rubiks.Color.YELLOW;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MultiMoveTest {
	private Cube cube = Cube.finished();
	
	@Test
	public void rightClockUpClock() throws Exception {
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
		assertFaceContent(WHITE, GREEN, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
	}
	
	@Test
	public void frontClockLeftClock() throws Exception {
		cube.turnFrontClock();
		cube.turnLeftClock();
		
		assertThat(cube.cell(GREEN,UP_LEFT)).isEqualTo(WHITE);
		assertThat(cube.cell(GREEN,LEFT)).isEqualTo(WHITE);
		assertThat(cube.cell(GREEN,DOWN_LEFT)).isEqualTo(ORANGE);
		assertThat(cube.cell(GREEN,UP_MIDDLE)).isEqualTo(GREEN);
		assertThat(cube.cell(GREEN,DOWN_MIDDLE)).isEqualTo(GREEN);
		assertThat(cube.cell(GREEN,UP_RIGHT)).isEqualTo(GREEN);
		assertThat(cube.cell(GREEN,RIGHT)).isEqualTo(GREEN);
		assertThat(cube.cell(GREEN,DOWN_RIGHT)).isEqualTo(GREEN);
		
		assertThat(cube.cell(WHITE,UP_LEFT)).isEqualTo(BLUE);
		assertThat(cube.cell(WHITE,LEFT)).isEqualTo(BLUE);
		assertThat(cube.cell(WHITE,DOWN_LEFT)).isEqualTo(BLUE);
		assertThat(cube.cell(WHITE,UP_MIDDLE)).isEqualTo(WHITE);
		assertThat(cube.cell(WHITE,DOWN_MIDDLE)).isEqualTo(ORANGE);
		assertThat(cube.cell(WHITE,UP_RIGHT)).isEqualTo(WHITE);
		assertThat(cube.cell(WHITE,RIGHT)).isEqualTo(WHITE);
		assertThat(cube.cell(WHITE,DOWN_RIGHT)).isEqualTo(ORANGE);
		

		assertThat(cube.cell(BLUE,UP_LEFT)).isEqualTo(BLUE);
		assertThat(cube.cell(BLUE,LEFT)).isEqualTo(BLUE);
		assertThat(cube.cell(BLUE,DOWN_LEFT)).isEqualTo(BLUE);
		assertThat(cube.cell(BLUE,UP_MIDDLE)).isEqualTo(BLUE);
		assertThat(cube.cell(BLUE,DOWN_MIDDLE)).isEqualTo(BLUE);
		assertThat(cube.cell(BLUE,UP_RIGHT)).isEqualTo(YELLOW);
		assertThat(cube.cell(BLUE,RIGHT)).isEqualTo(YELLOW);
		assertThat(cube.cell(BLUE,DOWN_RIGHT)).isEqualTo(RED);
		

		assertThat(cube.cell(YELLOW,UP_LEFT)).isEqualTo(GREEN);
		assertThat(cube.cell(YELLOW,LEFT)).isEqualTo(GREEN);
		assertThat(cube.cell(YELLOW,DOWN_LEFT)).isEqualTo(GREEN);
		assertThat(cube.cell(YELLOW,UP_MIDDLE)).isEqualTo(RED);
		assertThat(cube.cell(YELLOW,DOWN_MIDDLE)).isEqualTo(YELLOW);
		assertThat(cube.cell(YELLOW,UP_RIGHT)).isEqualTo(RED);
		assertThat(cube.cell(YELLOW,RIGHT)).isEqualTo(YELLOW);
		assertThat(cube.cell(YELLOW,DOWN_RIGHT)).isEqualTo(YELLOW);
		
		assertFaceContent(ORANGE, YELLOW, DOWN_LEFT,DOWN_MIDDLE,DOWN_RIGHT);
		assertFaceContent(RED, WHITE, UP_LEFT,LEFT,DOWN_LEFT);
		
	}
	
	private void assertFaceContent(Color baseSide, Color expectedColor,CellPosition... cellPositionsExpectionNew) {
		List<CellPosition> expNew = Arrays.asList(cellPositionsExpectionNew);
		for (CellPosition cellPosition : CellPosition.values()) {
			Color expected = expNew.contains(cellPosition) ? expectedColor : baseSide;
			assertThat(cube.cell(baseSide, cellPosition)).isEqualTo(expected);
		}
	}

}
