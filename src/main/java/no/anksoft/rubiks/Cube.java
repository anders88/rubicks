package no.anksoft.rubiks;

import static no.anksoft.rubiks.Color.*;
import static no.anksoft.rubiks.CellPosition.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		doMove(new FaceMove(GREEN,RIGHT),new FaceMove(YELLOW,RIGHT), new FaceMove(BLUE,LEFT), new FaceMove(WHITE,RIGHT));
	}

	public void turnRightAnti() {
		doMove(new FaceMove(GREEN,RIGHT),new FaceMove(WHITE,RIGHT),new FaceMove(BLUE,LEFT),new FaceMove(YELLOW,RIGHT));
	}

	private void doMove(FaceMove... faceMoves) {
		FaceMove firstMove = faceMoves[0];
		List<Color> firstSideColors = copyFrom(firstMove);
		for (int num = 1; num < faceMoves.length; num++) {
			List<Color> fromColors = copyFrom(faceMoves[num]);
			FaceMove toFaceMove = faceMoves[num-1];
			copyColorsToFace(fromColors, toFaceMove);
		}
		copyColorsToFace(firstSideColors, faceMoves[faceMoves.length-1]);
		
	}

	private void copyColorsToFace(List<Color> fromColors, FaceMove toFaceMove) {
		Face toFace = faces.get(toFaceMove.getColor());
		for (int i=0;i<3;i++) {
			toFace.update(toFaceMove.positions().get(i), fromColors.get(i));
		}
	}

	private List<Color> copyFrom(FaceMove firstMove) {
		List<Color> result = new ArrayList<Color>();
		Face face = faces.get(firstMove.getColor());
		for (CellPosition cellPosition : firstMove.positions()) {
			result.add(face.cell(cellPosition));
		}
		return result;
	}

	public void turnLeftClock() {
		Color greenUpLeft = greenFace.cell(UP_LEFT);
		Color greenLeft = greenFace.cell(LEFT);
		Color greenDownLeft = greenFace.cell(DOWN_LEFT);
		
		update(WHITE,UP_LEFT,GREEN,UP_LEFT);
		update(WHITE,LEFT,GREEN,LEFT);
		update(WHITE,DOWN_LEFT,GREEN,DOWN_LEFT);
		
		update(BLUE, UP_RIGHT, WHITE, DOWN_LEFT);
		update(BLUE, RIGHT, WHITE, LEFT);
		update(BLUE, DOWN_RIGHT, WHITE, UP_LEFT);
		
		update(YELLOW, UP_LEFT, BLUE, DOWN_RIGHT);
		update(YELLOW, LEFT, BLUE, RIGHT);
		update(YELLOW, DOWN_LEFT, BLUE, UP_RIGHT);
		
		faces.get(YELLOW).update(UP_LEFT, greenUpLeft);
		faces.get(YELLOW).update(LEFT, greenLeft);
		faces.get(YELLOW).update(DOWN_LEFT, greenDownLeft);

	}

	private void update(Color from, CellPosition cellPositionFrom, Color to,
			CellPosition cellPositionTo) {
		Color fromValue = faces.get(from).cell(cellPositionFrom);
		faces.get(to).update(cellPositionTo , fromValue);
	}

}
