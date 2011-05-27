package no.anksoft.rubiks;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

public class RotationTest {
	private Map<Color, Face> faces = new Hashtable<Color, Face>();
	private Cube cube = setupCube();

	private Cube setupCube() {
		for (Color color : Color.values()) {
			Face face = mock(Face.class);
			when(face.cell(any(CellPosition.class))).thenReturn(Color.GREEN);
			faces.put(color,face);
		}
		return Cube.withFaces(faces);
	}
	
	@Test
	public void rightTurnClockShoudRotateRed() throws Exception {
		cube.turnRightClock();
		verify(faces.get(Color.RED)).rotateClockwise();
		verifyNoRotationClockwise(Color.RED);
		verifyNoRotationAnti(null);
	}
	
	@Test
	public void rightTurnCCShouldCCRotateRed() throws Exception {
		cube.turnRightAnti();
		verify(faces.get(Color.RED)).rotateAnti();
		verifyNoRotationClockwise(null);
		verifyNoRotationAnti(Color.RED);
	}
	

	
	
	private void verifyNoRotationClockwise(Color exceptColor) {
		
		for (Color color : Color.values()) {
			if (color == exceptColor) {
				continue;
			}
			verify(faces.get(color),never()).rotateClockwise();
		}
	}

	private void verifyNoRotationAnti(Color exceptColor) {
		
		for (Color color : Color.values()) {
			if (color == exceptColor) {
				continue;
			}
			verify(faces.get(color),never()).rotateAnti();
		}
	}

	
}
