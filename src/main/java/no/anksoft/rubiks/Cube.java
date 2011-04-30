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
		faces.get(GREEN).setRight(faces.get(RED));
		faces.get(RED).setRight(faces.get(BLUE));
		faces.get(BLUE).setRight(faces.get(ORANGE));
		faces.get(ORANGE).setRight(faces.get(GREEN));
	}

	public void turnUpClock() {
		refFace.turnUpClock();
	}

	public Color cell(Color color, CellPosition cellPosition) {
		return faces.get(color).cell(cellPosition);
	}

}
