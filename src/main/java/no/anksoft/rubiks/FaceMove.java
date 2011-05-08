package no.anksoft.rubiks;

import java.util.Arrays;
import java.util.List;

public class FaceMove {


	private final Color color;
	private final CellPosition cellPosition;
	private List<CellPosition> positions;

	public FaceMove(Color color, CellPosition cellPosition) {
		this.color = color;
		this.cellPosition = cellPosition;
		this.positions = initPositions();
		
	}
	
	public List<CellPosition> positions() {
		return positions;
	}
	
	private List<CellPosition> initPositions() {
		switch (cellPosition) {
		case RIGHT:
			return Arrays.asList(CellPosition.UP_RIGHT,CellPosition.RIGHT,CellPosition.DOWN_RIGHT);
		case LEFT:
			return Arrays.asList(CellPosition.DOWN_LEFT,CellPosition.LEFT,CellPosition.UP_LEFT);
		default:
			throw new IllegalArgumentException("Not supported " + cellPosition);
		}
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FaceMove)) {
			return false;
		}
		return color == ((FaceMove) obj).color;
	}
	
	@Override
	public int hashCode() {
		return color == null ? -1 : color.hashCode();
	}
	
	@Override
	public String toString() {
		return "FaceMove<" + color + "," + cellPosition + ">";
	}

}
