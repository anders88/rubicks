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
	
	
	@Test
	public void leftClockShouldRotateOrange() throws Exception {
		cube.turnLeftClock();
		verify(faces.get(Color.ORANGE)).rotateClockwise();
		verifyNoRotationClockwise(Color.ORANGE);
		verifyNoRotationAnti(null);
	}
	
	@Test
	public void leftAntiShouldRotateOrange() throws Exception {
		cube.turnLeftAnti();
		verify(faces.get(Color.ORANGE)).rotateAnti();
		verifyNoRotationClockwise(null);
		verifyNoRotationAnti(Color.ORANGE);
	}
	
	@Test
	public void upClockShouldRotateWhite() throws Exception {
		cube.turnUpClock();
		verify(faces.get(Color.WHITE)).rotateClockwise();
		verifyNoRotationClockwise(Color.WHITE);
		verifyNoRotationAnti(null);
	}
	
	@Test
	public void upAntiShouldRotateWhite() throws Exception {
		cube.turnUpAnti();
		verify(faces.get(Color.WHITE)).rotateAnti();
		verifyNoRotationClockwise(null);
		verifyNoRotationAnti(Color.WHITE);
	}
	
	@Test
	public void downClockShouldRotateYellow() throws Exception {
		cube.turnDownClock();
		verify(faces.get(Color.YELLOW)).rotateClockwise();
		verifyNoRotationClockwise(Color.YELLOW);
		verifyNoRotationAnti(null);
	}
	
	@Test
	public void downAntiShouldRotateYellow() throws Exception {
		cube.turnDownAnti();
		verify(faces.get(Color.YELLOW)).rotateAnti();
		verifyNoRotationClockwise(null);
		verifyNoRotationAnti(Color.YELLOW);
	}
	
	@Test
	public void frontClockShouldRotateGreen() throws Exception {
		cube.turnFrontClock();
		verify(faces.get(Color.GREEN)).rotateClockwise();
		verifyNoRotationClockwise(Color.GREEN);
		verifyNoRotationAnti(null);
	}
	
	@Test
	public void frontAntiShouldRotateGreen() throws Exception {
		cube.turnFrontAnti();
		verify(faces.get(Color.GREEN)).rotateAnti();
		verifyNoRotationClockwise(null);
		verifyNoRotationAnti(Color.GREEN);
	}
	
	@Test
	public void backClockShouldRotateBlue() throws Exception {
		cube.turnBackClock();
		verify(faces.get(Color.BLUE)).rotateClockwise();
		verifyNoRotationClockwise(Color.BLUE);
		verifyNoRotationAnti(null);
	}
	
	
	@Test
	public void backAntiShouldRotateBlue() throws Exception {
		cube.turnBackAnti();
		verify(faces.get(Color.BLUE)).rotateAnti();
		verifyNoRotationClockwise(null);
		verifyNoRotationAnti(Color.BLUE);
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
