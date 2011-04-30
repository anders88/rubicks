package no.anksoft.rubiks;

import java.util.HashMap;
import java.util.Map;

public class Face {

	private Map<CellPosition, Color> cells = new HashMap<CellPosition, Color>();
	private Face right;
	private Face left;
	private Color color;
	
	

	private Face(Color color) {
		super();
		this.color = color;
	}

	public void turnUpClock() {
		turnUpClock(this);
	}

	public void turnUpAnti() {
		turnUpAnti(this);
	}

	public void turnDownClock() {
		turnDownClock(this);
	}

	private void turnUpClock(Face starter) {
		Color upLeft = right.cells.get(CellPosition.UP_LEFT);
		Color upMiddle = right.cells.get(CellPosition.UP_MIDDLE);
		Color upRight = right.cells.get(CellPosition.UP_RIGHT);
		
		if (right != starter) right.turnUpClock(starter);
		
		cells.put(CellPosition.UP_LEFT, upLeft);
		cells.put(CellPosition.UP_MIDDLE, upMiddle);
		cells.put(CellPosition.UP_RIGHT, upRight);
	}
	
	private void turnUpAnti(Face starter) {
		Color upLeft = left.cells.get(CellPosition.UP_LEFT);
		Color upMiddle = left.cells.get(CellPosition.UP_MIDDLE);
		Color upRight = left.cells.get(CellPosition.UP_RIGHT);
		
		if (left != starter) left.turnUpAnti(starter);
		
		cells.put(CellPosition.UP_LEFT, upLeft);
		cells.put(CellPosition.UP_MIDDLE, upMiddle);
		cells.put(CellPosition.UP_RIGHT, upRight);
	}
	
	private void turnDownClock(Face starter) {
		Color downLeft = right.cells.get(CellPosition.DOWN_LEFT);
		Color downMiddle = right.cells.get(CellPosition.DOWN_MIDDLE);
		Color downRight = right.cells.get(CellPosition.DOWN_RIGHT);
		
		if (right != starter) right.turnDownClock(starter);
		
		cells.put(CellPosition.DOWN_LEFT, downLeft);
		cells.put(CellPosition.DOWN_MIDDLE, downMiddle);
		cells.put(CellPosition.DOWN_RIGHT, downRight);
	}

	public Color cell(CellPosition cellPosition) {
		return cells.get(cellPosition);
	}

	public static Face finished(Color color) {
		Face face = new Face(color);
		for (CellPosition cellPosition : CellPosition.values()) {
			face.cells.put(cellPosition, color);
		}
		return face;
	}

	public void setupRight(Face face) {
		this.right = face;
		face.left = this;
	}
	
	@Override
	public String toString() {
		return "Face<" + color +">";
	}
	
	

}
