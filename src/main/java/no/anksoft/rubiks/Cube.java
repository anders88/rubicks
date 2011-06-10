package no.anksoft.rubiks;

import static no.anksoft.rubiks.CellPosition.DOWN_MIDDLE;
import static no.anksoft.rubiks.CellPosition.LEFT;
import static no.anksoft.rubiks.CellPosition.RIGHT;
import static no.anksoft.rubiks.CellPosition.UP_MIDDLE;
import static no.anksoft.rubiks.Color.BLUE;
import static no.anksoft.rubiks.Color.GREEN;
import static no.anksoft.rubiks.Color.ORANGE;
import static no.anksoft.rubiks.Color.RED;
import static no.anksoft.rubiks.Color.WHITE;
import static no.anksoft.rubiks.Color.YELLOW;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class Cube implements Cloneable, FaceOwner {
	
	private Hashtable<Color, Face> faces = new Hashtable<Color, Face>();
	private Face greenFace;
	private FaceOwner cloneTarget;

	public static Cube finished() {
		Cube cube = new Cube();
		for (Color color : Color.values()) {
			Face face = Face.finished(color,cube);
			cube.faces.put(color,face);
		}
		cube.greenFace = cube.faces.get(GREEN);
		return cube;
	}
	
	public static Cube withFaces(Map<Color, Face> faces) {
		Cube cube = new Cube();
		cube.faces = new Hashtable<Color, Face>();
		cube.faces.putAll(faces);
		cube.greenFace = faces.get(GREEN);
		return cube;
	}


	public Cube turnUpClock() {
		greenFace.turnUpClock();
		faces.get(WHITE).rotateClockwise();
		return this;
	}

	public Color cell(Color color, CellPosition cellPosition) {
		return faces.get(color).cell(cellPosition);
	}

	public Cube turnDownClock() {
		greenFace.turnDownClock();
		faces.get(YELLOW).rotateClockwise();
		return this;
	}

	public void turnUpAnti() {
		greenFace.turnUpAnti();
		faces.get(WHITE).rotateAnti();
	}

	public Cube turnDownAnti() {
		greenFace.turnDownAnti();
		faces.get(YELLOW).rotateAnti();
		return this;
	}

	public Cube turnRightClock() {
		doMove(new FaceMove(GREEN,RIGHT),new FaceMove(YELLOW,RIGHT), new FaceMove(BLUE,LEFT), new FaceMove(WHITE,RIGHT));
		faces.get(RED).rotateClockwise();
		return this;
	}

	public Cube turnRightAnti() {
		doMove(new FaceMove(GREEN,RIGHT),new FaceMove(WHITE,RIGHT),new FaceMove(BLUE,LEFT),new FaceMove(YELLOW,RIGHT));
		faces.get(RED).rotateAnti();
		return this;
	}

	public Cube turnLeftClock() {
		doMove(new FaceMove(GREEN,LEFT),new FaceMove(WHITE,LEFT),new FaceMove(BLUE,RIGHT),new FaceMove(YELLOW,LEFT));
		faces.get(ORANGE).rotateClockwise();
		return this;
	}

	public Cube turnLeftAnti() {
		doMove(new FaceMove(GREEN,LEFT),new FaceMove(YELLOW,LEFT),new FaceMove(BLUE,RIGHT),new FaceMove(WHITE,LEFT));
		faces.get(ORANGE).rotateAnti();
		return this;
	}

	public Cube turnFrontClock() {
		doMove(new FaceMove(WHITE,DOWN_MIDDLE),new FaceMove(ORANGE,RIGHT),new FaceMove(YELLOW,UP_MIDDLE),new FaceMove(RED,LEFT));
		greenFace.rotateClockwise();
		return this;
	}

	public Cube turnFrontAnti() {
		doMove(new FaceMove(WHITE,DOWN_MIDDLE),new FaceMove(RED,LEFT),new FaceMove(YELLOW,UP_MIDDLE),new FaceMove(ORANGE,RIGHT));
		greenFace.rotateAnti();
		return this;
	}

	public Cube turnBackClock() {
		doMove(new FaceMove(WHITE,UP_MIDDLE),new FaceMove(RED, RIGHT),new FaceMove(YELLOW, DOWN_MIDDLE),new FaceMove(ORANGE, LEFT));
		faces.get(BLUE).rotateClockwise();
		return this;
	}

	public Cube turnBackAnti() {
		doMove(new FaceMove(WHITE,UP_MIDDLE),new FaceMove(ORANGE, LEFT),new FaceMove(YELLOW, DOWN_MIDDLE),new FaceMove(RED, RIGHT));
		faces.get(BLUE).rotateAnti();
		return this;
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
	

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cube)) {
			return false;
		}
		Cube other = (Cube) obj;
		for (Color color : Color.values()) {
			Face face = faces.get(color);
			Face otherFace = other.faces.get(color); 
			if (!face.equals(otherFace)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return greenFace.hashCode();
	}
	
	@Override
	public synchronized Cube clone() {
		Cube cube = new Cube();
		cloneTarget = cube;
		cube.faces = new Hashtable<Color, Face>();
		for (Color color : Color.values()) {
			cube.faces.put(color, faces.get(color).clone());
		}
		cube.greenFace = cube.faces.get(GREEN);
		return cube;
	}

	@Override
	public Face face(Color color) {
		return faces.get(color);
	}

	@Override
	public FaceOwner cloneTarget() {
		return cloneTarget;
	}
}
