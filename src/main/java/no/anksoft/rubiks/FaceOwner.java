package no.anksoft.rubiks;

public interface FaceOwner {
	public Face face(Color color);
	
	public FaceOwner cloneTarget();
}
