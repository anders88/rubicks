package no.anksoft.rubiks;

import java.util.HashMap;
import java.util.Map;



public class Face {

	private Map<CellPosition, Color> cells = new HashMap<CellPosition, Color>();
	private Face right;


	public Face turnUpClock() {
		cells.put(CellPosition.UP_LEFT, right.cells.get(CellPosition.UP_LEFT));
		cells.put(CellPosition.UP_MIDDLE, right.cells.get(CellPosition.UP_MIDDLE));
		cells.put(CellPosition.UP_RIGHT, right.cells.get(CellPosition.UP_RIGHT));
		return this;
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
