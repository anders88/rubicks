package no.anksoft.rubiks;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestCellGeneration {
	private final RubikCell cube = RubikCell.generateCube();

	@Test
	public void shouldBeTwelweStepsAllAround() throws Exception {
		assertCompleteWalkAround(cube, new DoWalk() {
			@Override
			public RubikCell doIt(RubikCell rubikCell) {
				return rubikCell.down();
			}
		});
		assertCompleteWalkAround(cube, new DoWalk() {
			@Override
			public RubikCell doIt(RubikCell rubikCell) {
				return rubikCell.up();
			}
		});
		assertCompleteWalkAround(cube, new DoWalk() {
			@Override
			public RubikCell doIt(RubikCell rubikCell) {
				return rubikCell.right();
			}
		});
	}

	private void assertCompleteWalkAround(RubikCell cell, DoWalk doWalk) {
		List<RubikCell> allFoundCells = new ArrayList<RubikCell>();
		for (int i=0;i<12;i++) {
			assertThat(allFoundCells.contains(cell)).isFalse();
			allFoundCells.add(cell);
			cell = doWalk.doIt(cell);
		}			
		assertThat(cube).isEqualTo(cell);
	}
	
	@Test
	public void shouldComeBackIfGoingDownAndUp() throws Exception {
		assertThat(cube.down().up()).isEqualTo(cube);
		assertThat(cube.up().down()).isEqualTo(cube);
	}
	
	@Test
	public void testname() throws Exception {
		
	}
}
