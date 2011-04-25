package no.anksoft.rubiks;


public class RubikCell {
	
	private final int id;
	private RubikCell down;
	private RubikCell up;
	private RubikCell left;
	private RubikCell right;

	public static RubikCell generateCube() {
		int cellNo=0;
		RubikCell startCell[] = new RubikCell[3];
		RubikCell previous[] = new RubikCell[3];
		for (int j=0;j<3;j++) {
			startCell[j] = new RubikCell(cellNo++);
			previous[j] = startCell[j];
		}
		for (int i=0;i<11;i++) {
			RubikCell prevHorCell = null;
			for (int j=0;j<3;j++) {
				RubikCell cell = new RubikCell(cellNo++);
				previous[j].down = cell;
				cell.up = previous[j];
				previous[j] = cell;
				cell.left = prevHorCell;
				if (prevHorCell != null) prevHorCell.right = cell;
				prevHorCell = cell;
			}
		}
		for (int j=0;j<3;j++) {
			previous[j].down = startCell[j];
			startCell[j].up = previous[j];
		}
		return startCell[0];
	}

	
	
	public RubikCell(int id) {
		super();
		this.id = id;
	}



	@Override
	public String toString() {
		return "Cell<" + id + ">";
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RubikCell)) return false;
		return id == ((RubikCell) obj).id;
	}
	
	@Override
	public int hashCode() {
		return new Integer(id).hashCode();
	}



	public RubikCell down() {
		return down;
	}

	public RubikCell up() {
		return up;
	}

	public RubikCell right() {
		return right;
	}
	
}
