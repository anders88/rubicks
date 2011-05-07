package no.anksoft.rubiks;

import static no.anksoft.rubiks.Color.*;
import static no.anksoft.rubiks.CellPosition.*;

import java.util.HashMap;
import java.util.Map;

public class Cube {
	
	private Map<Color, Face> faces = new HashMap<Color, Face>();
	private Face greenFace;

	public static Cube finished() {
		Cube cube = new Cube();
		for (Color color : Color.values()) {
			Face face = Face.finished(color);
			cube.faces.put(color,face);
		}
		cube.setupNeighbours();
		cube.greenFace = cube.faces.get(GREEN);
		return cube;
	}

	private void setupNeighbours() {
		faces.get(GREEN).setupRight(faces.get(RED));
		faces.get(RED).setupRight(faces.get(BLUE));
		faces.get(BLUE).setupRight(faces.get(ORANGE));
		faces.get(ORANGE).setupRight(faces.get(GREEN));
		
		
	}

	public void turnUpClock() {
		greenFace.turnUpClock();
	}

	public Color cell(Color color, CellPosition cellPosition) {
		return faces.get(color).cell(cellPosition);
	}

	public void turnDownClock() {
		greenFace.turnDownClock();
		
	}

	public void turnUpAnti() {
		greenFace.turnUpAnti();
	}

	public void turnDownAnti() {
		greenFace.turnDownAnti();
	}

	public void turnRightClock() {
		Color greenUpRight = greenFace.cell(UP_RIGHT);
		Color greenRight = greenFace.cell(RIGHT);
		Color greenDownRight = greenFace.cell(DOWN_RIGHT);

		update(YELLOW, UP_RIGHT,GREEN, UP_RIGHT);
		update(YELLOW, RIGHT,GREEN, RIGHT);
		update(YELLOW, DOWN_RIGHT,GREEN, DOWN_RIGHT);
		
		update(BLUE, DOWN_LEFT, YELLOW, UP_RIGHT);
		update(BLUE, LEFT, YELLOW, RIGHT);
		update(BLUE, UP_LEFT, YELLOW, DOWN_RIGHT);
		
		update(WHITE, UP_RIGHT, BLUE, DOWN_LEFT);
		update(WHITE, RIGHT,BLUE, LEFT);
		update(WHITE, DOWN_RIGHT, BLUE, UP_LEFT);

		faces.get(WHITE).update(UP_RIGHT, greenUpRight);
		faces.get(WHITE).update(RIGHT, greenRight);
		faces.get(WHITE).update(DOWN_RIGHT, greenDownRight);
	}

	private void update(Color from, CellPosition cellPositionFrom, Color to,
			CellPosition cellPositionTo) {
		faces.get(to).update(cellPositionTo , faces.get(from).cell(cellPositionFrom));
	}

	public void turnRightAntiClock() {
		Color greenUpRight = greenFace.cell(UP_RIGHT);
		Color greenRight = greenFace.cell(RIGHT);
		Color greenDownRight = greenFace.cell(DOWN_RIGHT);
		
		update(WHITE,UP_RIGHT,GREEN,UP_RIGHT);
		update(WHITE,RIGHT,GREEN,RIGHT);
		update(WHITE,DOWN_RIGHT,GREEN,DOWN_RIGHT);
		
		update(BLUE, DOWN_LEFT, WHITE, UP_RIGHT);
		update(BLUE, LEFT, WHITE, RIGHT);
		update(BLUE, UP_LEFT, WHITE, DOWN_RIGHT);
		
		update(YELLOW, UP_RIGHT, BLUE, DOWN_LEFT);
		update(YELLOW, RIGHT,BLUE, LEFT);
		update(YELLOW, DOWN_RIGHT, BLUE, UP_LEFT);
		
		faces.get(YELLOW).update(UP_RIGHT, greenUpRight);
		faces.get(YELLOW).update(RIGHT, greenRight);
		faces.get(YELLOW).update(DOWN_RIGHT, greenDownRight);
	}

}
