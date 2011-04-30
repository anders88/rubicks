package no.anksoft.rubiks;

import static no.anksoft.rubiks.Color.GREEN;
import static no.anksoft.rubiks.Color.RED;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class BasicMovesTest {
	@Test
	public void finishedCubeShouldHaveSameColor() throws Exception {
		assertThat(Cube.finished().face(GREEN).cell(CellPosition.UP_LEFT)).isEqualTo(GREEN);
	}
	
	@Test
	public void shouldHaveRedIntoGreenWhenTurningUpside() throws Exception {
		Cube cube = Cube.finished();
		Face greenFace = cube.face(GREEN).turnUpClock();
		assertThat(greenFace.cell(CellPosition.UP_LEFT)).isEqualTo(RED);
		assertThat(greenFace.cell(CellPosition.UP_MIDDLE)).isEqualTo(RED);
		assertThat(greenFace.cell(CellPosition.UP_RIGHT)).isEqualTo(RED);
	}
}
