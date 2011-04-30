package no.anksoft.rubiks;

import java.util.HashMap;
import java.util.Map;

import static no.anksoft.rubiks.Color.*;

public class Cube {
	
	private Map<Color, Face> faces = new HashMap<Color, Face>();

	public static Cube finished() {
		Cube cube = new Cube();
		for (Color color : Color.values()) {
			Face face = Face.finished(color);
			cube.faces.put(color,face);
		}
		cube.setupNeighbours();
		return cube;
	}

	private void setupNeighbours() {
		faces.get(GREEN).setRight(faces.get(RED));
	}

	public Face face(Color color) {
		return faces.get(color);
	}

}
