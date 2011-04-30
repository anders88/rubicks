package no.anksoft.rubiks;

import java.util.HashMap;
import java.util.Map;

public class Face {

	private Map<CellPosition, Color> cells = new HashMap<CellPosition, Color>();
	private Face right;

	public void turnUpClock() {
		turnUpClock(this);
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

	public Color cell(CellPosition cellPosition) {
		return cells.get(cellPosition);
	}

	public static Face finished(Color color) {
		Face face = new Face();
		for (CellPosition cellPosition : CellPosition.values()) {
			face.cells.put(cellPosition, color);
		}
		return face;
	}

	public void setRight(Face face) {
		this.right = face;
	}

}
