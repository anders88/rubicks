package no.anksoft.rubiks;

import org.apache.log4j.Logger;

public class CubeSolverRunner {
	public static final Logger logger = Logger.getLogger(CubeSolverRunner.class);
	
	
	public static void main(String[] args) {
		logger.info("Starting");
		Cube cube = generateCubeToSolve();
		new CubeSolver(cube).solve();
		logger.info("Done");
	}

	private static Cube generateCubeToSolve() {
		Cube cube = Cube.finished();
		MoveGenerator moveGenerator = new MoveGenerator(cube);
		for (int i=0;i<8;i++) {
			moveGenerator.doRandomMove();
		}
		return cube;
	}
}
