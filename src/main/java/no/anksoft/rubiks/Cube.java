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

	public void turnLeftClock() {
		doMove(new FaceMove(GREEN,LEFT),new FaceMove(WHITE,LEFT),new FaceMove(BLUE,RIGHT),new FaceMove(YELLOW,LEFT));
	}

	public void turnLeftAnti() {
		doMove(new FaceMove(GREEN,LEFT),new FaceMove(YELLOW,LEFT),new FaceMove(BLUE,RIGHT),new FaceMove(WHITE,LEFT));
		
	}

	public void turnFrontClockWise() {
		doMove(new FaceMove(WHITE,DOWN_MIDDLE),new FaceMove(ORANGE,RIGHT),new FaceMove(YELLOW,UP_MIDDLE),new FaceMove(RED,LEFT));
		
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

}
