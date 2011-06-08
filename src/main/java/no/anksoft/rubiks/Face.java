package no.anksoft.rubiks;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static no.anksoft.rubiks.CellPosition.*; 

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

	public void turnDownAnti() {
		turnDownAnti(this);
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
	
	private void turnDownAnti(Face starter) {
		Color upMiddle = left.cells.get(CellPosition.DOWN_MIDDLE);
		Color upLeft = left.cells.get(CellPosition.DOWN_LEFT);
		Color upRight = left.cells.get(CellPosition.DOWN_RIGHT);
		
		if (left != starter) left.turnDownAnti(starter);
		
		cells.put(CellPosition.DOWN_LEFT, upLeft);
		cells.put(CellPosition.DOWN_MIDDLE, upMiddle);
		cells.put(CellPosition.DOWN_RIGHT, upRight);
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
	
	public static Face withCells(Color color, Map<CellPosition,Color> cells) {
		Face face = new Face(color);
		face.cells = cells;
		return face;
	}
	
	public void update(CellPosition cellPosition, Color newValue) {
		cells.put(cellPosition, newValue);
	}

	public void setupRight(Face face) {
		this.right = face;
		face.left = this;
	}
	
	@Override
	public String toString() {
		return "Face<" + color +">";
	}

	public void rotateClockwise() {
		Map<CellPosition, Color> newCells = new Hashtable<CellPosition, Color>();
		newCells.put(UP_RIGHT, cell(UP_LEFT));
		newCells.put(RIGHT, cell(UP_MIDDLE));
		newCells.put(DOWN_RIGHT, cell(UP_RIGHT));
		newCells.put(UP_MIDDLE, cell(LEFT));
		newCells.put(DOWN_MIDDLE, cell(RIGHT));
		newCells.put(UP_LEFT, cell(DOWN_LEFT));
		newCells.put(LEFT, cell(DOWN_MIDDLE));
		newCells.put(DOWN_LEFT, cell(DOWN_RIGHT));
		
		cells = newCells;
	}

	public void rotateAnti() {
		Map<CellPosition, Color> newCells = new Hashtable<CellPosition, Color>();
		newCells.put(DOWN_LEFT, cell(UP_LEFT));
		newCells.put(LEFT, cell(UP_MIDDLE));
		newCells.put(UP_LEFT, cell(UP_RIGHT));
		newCells.put(DOWN_MIDDLE, cell(LEFT));
		newCells.put(UP_MIDDLE, cell(RIGHT));
		newCells.put(DOWN_RIGHT, cell(DOWN_LEFT));
		newCells.put(RIGHT, cell(DOWN_MIDDLE));
		newCells.put(UP_RIGHT, cell(DOWN_RIGHT));
		
		cells = newCells;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Face)) {
			return false;
		}
		Face other = (Face) obj;
		if (color != other.color) return false;
		for (CellPosition cellPosition : CellPosition.values()) {
			if (cell(cellPosition) != other.cell(cellPosition)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return color.hashCode() + cell(UP_LEFT).hashCode();
	}

}
