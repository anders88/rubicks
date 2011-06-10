package no.anksoft.rubiks;

import static no.anksoft.rubiks.CellPosition.DOWN_LEFT;
import static no.anksoft.rubiks.CellPosition.DOWN_MIDDLE;
import static no.anksoft.rubiks.CellPosition.DOWN_RIGHT;
import static no.anksoft.rubiks.CellPosition.LEFT;
import static no.anksoft.rubiks.CellPosition.RIGHT;
import static no.anksoft.rubiks.CellPosition.UP_LEFT;
import static no.anksoft.rubiks.CellPosition.UP_MIDDLE;
import static no.anksoft.rubiks.CellPosition.UP_RIGHT;

import java.util.Hashtable;
import java.util.Map;

public class Face implements Cloneable {

	private Hashtable<CellPosition, Color> cells = new Hashtable<CellPosition, Color>();
	private Color color;
	private final FaceOwner faceOwner;
	
	

	private Face(Color color, FaceOwner faceOwner) {
		super();
		this.color = color;
		this.faceOwner = faceOwner;
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
	
	private Face right() {
		switch (color) {
		case GREEN:
			return faceOwner.face(Color.RED);
		case RED:
			return faceOwner.face(Color.BLUE);
		case BLUE:
			return faceOwner.face(Color.ORANGE);
		case ORANGE:
			return faceOwner.face(Color.GREEN);
		}
		throw new IllegalArgumentException("Right not supported for " + color);
	}
	
	private Face left() {
		switch (color) {
		case GREEN:
			return faceOwner.face(Color.ORANGE);
		case RED:
			return faceOwner.face(Color.GREEN);
		case BLUE:
			return faceOwner.face(Color.RED);
		case ORANGE:
			return faceOwner.face(Color.BLUE);
		}
		throw new IllegalArgumentException("Left not supported for " + color);
	}

	private void turnUpClock(Face starter) {
		Color upLeft = right().cells.get(CellPosition.UP_LEFT);
		Color upMiddle = right().cells.get(CellPosition.UP_MIDDLE);
		Color upRight = right().cells.get(CellPosition.UP_RIGHT);
		
		if (right() != starter) right().turnUpClock(starter);
		
		cells.put(CellPosition.UP_LEFT, upLeft);
		cells.put(CellPosition.UP_MIDDLE, upMiddle);
		cells.put(CellPosition.UP_RIGHT, upRight);
	}
	
	private void turnUpAnti(Face starter) {
		Color upLeft = left().cells.get(CellPosition.UP_LEFT);
		Color upMiddle = left().cells.get(CellPosition.UP_MIDDLE);
		Color upRight = left().cells.get(CellPosition.UP_RIGHT);
		
		if (left() != starter) left().turnUpAnti(starter);
		
		cells.put(CellPosition.UP_LEFT, upLeft);
		cells.put(CellPosition.UP_MIDDLE, upMiddle);
		cells.put(CellPosition.UP_RIGHT, upRight);
	}
	
	private void turnDownAnti(Face starter) {
		Color upMiddle = left().cells.get(CellPosition.DOWN_MIDDLE);
		Color upLeft = left().cells.get(CellPosition.DOWN_LEFT);
		Color upRight = left().cells.get(CellPosition.DOWN_RIGHT);
		
		if (left() != starter) left().turnDownAnti(starter);
		
		cells.put(CellPosition.DOWN_LEFT, upLeft);
		cells.put(CellPosition.DOWN_MIDDLE, upMiddle);
		cells.put(CellPosition.DOWN_RIGHT, upRight);
	}
	
	private void turnDownClock(Face starter) {
		Color downLeft = right().cells.get(CellPosition.DOWN_LEFT);
		Color downMiddle = right().cells.get(CellPosition.DOWN_MIDDLE);
		Color downRight = right().cells.get(CellPosition.DOWN_RIGHT);
		
		if (right() != starter) right().turnDownClock(starter);
		
		cells.put(CellPosition.DOWN_LEFT, downLeft);
		cells.put(CellPosition.DOWN_MIDDLE, downMiddle);
		cells.put(CellPosition.DOWN_RIGHT, downRight);
	}

	public Color cell(CellPosition cellPosition) {
		return cells.get(cellPosition);
	}

	public static Face finished(Color color, FaceOwner owner) {
		Face face = new Face(color, owner);
		for (CellPosition cellPosition : CellPosition.values()) {
			face.cells.put(cellPosition, color);
		}
		return face;
	}
	
	public static Face withCells(Color color, Map<CellPosition,Color> cells, FaceOwner faceOwner) {
		Face face = new Face(color,faceOwner);
		face.cells = new Hashtable<CellPosition, Color>();
		face.cells.putAll(cells);
		return face;
	}
	
	public void update(CellPosition cellPosition, Color newValue) {
		cells.put(cellPosition, newValue);
	}

	@Override
	public String toString() {
		return "Face<" + color +">";
	}

	public void rotateClockwise() {
		Hashtable<CellPosition, Color> newCells = new Hashtable<CellPosition, Color>();
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
		Hashtable<CellPosition, Color> newCells = new Hashtable<CellPosition, Color>();
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
	
	@Override
	protected Face clone() {
		Face cloned = new Face(color,faceOwner);
		cloned.cells = new Hashtable<CellPosition, Color>();
		cloned.cells.putAll(cells);
		// TODO Setup right and left
		return cloned;
	}

}
