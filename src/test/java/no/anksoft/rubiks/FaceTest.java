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
import static org.mockito.Mockito.mock;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

public class FaceTest {
	private FaceOwner faceOwner = mock(FaceOwner.class);
	private Map<CellPosition, Color> cells = new Hashtable<CellPosition, Color>();
	
	@Test
	public void shouldRotateClockwise() throws Exception {
		cells.put(UP_LEFT,GREEN);
		cells.put(UP_MIDDLE, YELLOW);
		cells.put(UP_RIGHT, WHITE);
		cells.put(LEFT,BLUE);
		cells.put(RIGHT,RED);
		cells.put(DOWN_LEFT,ORANGE);
		cells.put(DOWN_MIDDLE,ORANGE);
		cells.put(DOWN_RIGHT,BLUE);
		Face face = Face.withCells(GREEN, cells, faceOwner );
		
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
		cells.put(UP_LEFT,GREEN);
		cells.put(UP_MIDDLE, YELLOW);
		cells.put(UP_RIGHT, WHITE);
		cells.put(LEFT,BLUE);
		cells.put(RIGHT,RED);
		cells.put(DOWN_LEFT,ORANGE);
		cells.put(DOWN_MIDDLE,ORANGE);
		cells.put(DOWN_RIGHT,BLUE);
		Face face = Face.withCells(GREEN, cells, faceOwner );
		
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
		cells.put(UP_LEFT,GREEN);
		cells.put(UP_MIDDLE, YELLOW);
		cells.put(UP_RIGHT, WHITE);
		cells.put(LEFT,BLUE);
		cells.put(RIGHT,RED);
		cells.put(DOWN_LEFT,ORANGE);
		cells.put(DOWN_MIDDLE,ORANGE);
		cells.put(DOWN_RIGHT,BLUE);
		assertThat(Face.finished(GREEN,faceOwner)).isEqualTo(Face.finished(GREEN,faceOwner)).isNotEqualTo(Face.withCells(GREEN, cells,faceOwner ));
	}
	
	@Test
	public void shouldClone() throws Exception {
		cells.put(UP_LEFT,GREEN);
		cells.put(UP_MIDDLE, YELLOW);
		cells.put(UP_RIGHT, WHITE);
		cells.put(LEFT,BLUE);
		cells.put(RIGHT,RED);
		cells.put(DOWN_LEFT,ORANGE);
		cells.put(DOWN_MIDDLE,ORANGE);
		cells.put(DOWN_RIGHT,BLUE);
		
		Face face = Face.withCells(GREEN, cells,faceOwner);
		Face clone = face.clone();
		assertThat(clone != face).isTrue();
		assertThat(clone).isEqualTo(face);
	}
}
