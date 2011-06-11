package no.anksoft.rubiks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class CubeSolver {
	private static final Logger logger = Logger.getLogger(CubeSolver.class);
	
	private final Cube finished = Cube.finished();
	private LinkedList<List<Moves>> toTry = new LinkedList<List<Moves>>();
	private Set<Cube> checked = new HashSet<Cube>();
	

	private final Cube startPoint;

	public CubeSolver(Cube cube) {
		this.startPoint = cube;
	}

	public List<Moves> solve() {
		int maxmoves=0;
		while (true) {
			Cube cube = startPoint.clone();
			List<Moves> moves = getNextTry();
			if (logger.isInfoEnabled() && moves.size() > maxmoves) {
				maxmoves = moves.size();
				logger.info("Maxmoves " + maxmoves);
			}
			doMoves(cube,moves);
			if (finished.equals(cube)) {
				return moves;
			}
			if (checked.contains(cube)) continue;
			checked.add(cube);
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