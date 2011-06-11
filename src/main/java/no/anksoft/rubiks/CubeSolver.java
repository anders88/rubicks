package no.anksoft.rubiks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CubeSolver {
	private final Cube finished = Cube.finished();
	private LinkedList<List<Moves>> toTry = new LinkedList<List<Moves>>();
	

	private final Cube startPoint;

	public CubeSolver(Cube cube) {
		this.startPoint = cube;
	}

	public List<Moves> solve() {
		
		while (true) {
			Cube cube = startPoint.clone();
			List<Moves> moves = getNextTry();
			doMoves(cube,moves);
			if (finished.equals(cube)) {
				return moves;
			}
			for (Moves move : Moves.values()) {
				List<Moves> tryMoves = new ArrayList<Moves>();
				tryMoves.addAll(moves);
				tryMoves.add(move);
				toTry.add(tryMoves);
			}
		}
		
	}

	private void doMoves(Cube cube, List<Moves> moves) {
		MoveGenerator moveGenerator = new MoveGenerator(cube);
		for (Moves move : moves) {
			moveGenerator.doMove(move);
		}
	}

	private List<Moves> getNextTry() {
		
		List<Moves> next = toTry.poll();
		if (next == null) return new ArrayList<Moves>();
		return next;
	}
}