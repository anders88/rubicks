package no.anksoft.rubiks;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;
import static no.anksoft.rubiks.Color.*;
import static no.anksoft.rubiks.CellPosition.*;
import static org.fest.assertions.Assertions.assertThat;

public class FaceTest {
	@Test
	public void shouldRotateClockwise() throws Exception {
		Map<CellPosition, Color> cells = new Hashtable<CellPosition, Color>();
		cells.put(UP_LEFT,GREEN);
		cells.put(UP_MIDDLE, YELLOW);
		cells.put(UP_RIGHT, WHITE);
		cells.put(LEFT,BLUE);
		cells.put(RIGHT,RED);
		cells.put(DOWN_LEFT,ORANGE);
		cells.put(DOWN_MIDDLE,ORANGE);
		cells.put(DOWN_RIGHT,BLUE);
		Face face = Face.withCells(GREEN, cells );
		
		face.rotateClockwise();
		
		assertThat(face.cell(UP_RIGHT)).isEqualTo(GREEN);
		assertThat(face.cell(RIGHT)).isEqualTo(YELLOW);
		assertThat(face.cell(DOWN_RIGHT)).isEqualTo(WHITE);
		assertThat(face.cell(UP_MIDDLE)).isEqualTo(BLUE);
		assertThat(face.cell(DOWN_MIDDLE)).isEqualTo(RED);
		assertThat(face.cell(UP_LEFT)).isEqualTo(ORANGE);
		assertThat(face.cell(LEFT)).isEqualTo(ORANGE);
		assertThat(face.cell(DOWN_LEFT)).isEqualTo(BLUE);
		
	}
	
	@Test
	public void shouldRotateAntiClockwise() throws Exception {
		Map<CellPosition, Color> cells = new Hashtable<CellPosition, Color>();
		cells.put(UP_LEFT,GREEN);
		cells.put(UP_MIDDLE, YELLOW);
		cells.put(UP_RIGHT, WHITE);
		cells.put(LEFT,BLUE);
		cells.put(RIGHT,RED);
		cells.put(DOWN_LEFT,ORANGE);
		cells.put(DOWN_MIDDLE,ORANGE);
		cells.put(DOWN_RIGHT,BLUE);
		Face face = Face.withCells(GREEN, cells );
		
		face.rotateAnti();
		
		assertThat(face.cell(DOWN_LEFT)).isEqualTo(GREEN);
		assertThat(face.cell(LEFT)).isEqualTo(YELLOW);
		assertThat(face.cell(UP_LEFT)).isEqualTo(WHITE);
		assertThat(face.cell(DOWN_MIDDLE)).isEqualTo(BLUE);
		assertThat(face.cell(UP_MIDDLE)).isEqualTo(RED);
		assertThat(face.cell(DOWN_RIGHT)).isEqualTo(ORANGE);
		assertThat(face.cell(RIGHT)).isEqualTo(ORANGE);
		assertThat(face.cell(UP_RIGHT)).isEqualTo(BLUE);
		
	}
	
	@Test
	public void sameFacesShouldBeEqual() throws Exception {
		Map<CellPosition, Color> cells = new Hashtable<CellPosition, Color>();
		cells.put(UP_LEFT,GREEN);
		cells.put(UP_MIDDLE, YELLOW);
		cells.put(UP_RIGHT, WHITE);
		cells.put(LEFT,BLUE);
		cells.put(RIGHT,RED);
		cells.put(DOWN_LEFT,ORANGE);
		cells.put(DOWN_MIDDLE,ORANGE);
		cells.put(DOWN_RIGHT,BLUE);
		assertThat(Face.finished(GREEN)).isEqualTo(Face.finished(GREEN)).isNotEqualTo(Face.withCells(GREEN, cells ));
	}
}
