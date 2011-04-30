package no.anksoft.rubiks;


public class RubikCell {
	
	private final int id;
	
	public static RubikCell generateCube() {
		Counter counter= new Counter();
		return null;
	}



	private RubikCell(int id) {
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
}
