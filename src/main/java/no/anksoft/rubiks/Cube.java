package no.anksoft.rubiks;

import static no.anksoft.rubiks.Color.GREEN;
import static no.anksoft.rubiks.Color.*;

import java.util.HashMap;
import java.util.Map;

public class Cube {
	
	private Map<Color, Face> faces = new HashMap<Color, Face>();
	private Face refFace;

	public static Cube finished() {
		Cube cube = new Cube();
		for (Color color : Color.values()) {
			Face face = Face.finished(color);
			cube.faces.put(color,face);
		}
		cube.setupNeighbours();
		cube.refFace = cube.faces.get(GREEN);
		return cube;
	}

	private void setupNeighbours() {
		faces.get(GREEN).setupRight(faces.get(RED));
		faces.get(RED).setupRight(faces.get(BLUE));
		faces.get(BLUE).setupRight(faces.get(ORANGE));
		faces.get(ORANGE).setupRight(faces.get(GREEN));
		
		
	}

	public void turnUpClock() {
		refFace.turnUpClock();
	}

	public Color cell(Color color, CellPosition cellPosition) {
		return faces.get(color).cell(cellPosition);
	}

	public void turnDownClock() {
		refFace.turnDownClock();
		
	}

	public void turnUpAnti() {
		refFace.turnUpAnti();
	}

	public void turnDownAnti() {
		refFace.turnDownAnti();
	}

	public void turnRightClock() {
		// TODO Auto-generated method stub
		
	}

}
