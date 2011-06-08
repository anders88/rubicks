package no.anksoft.rubiks;

import static no.anksoft.rubiks.Moves.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;

public class MoveGeneratorTest {
	private Cube cube = mock(Cube.class);
	private MoveGenerator moveGenerator = new MoveGenerator(cube);

	@Test
	public void shouldDoRightClock() throws Exception {
		moveGenerator.doMove(RIGHT_CLOCK);
		verify(cube).turnRightClock();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoRightAnti() throws Exception {
		moveGenerator.doMove(RIGHT_ANTI);
		verify(cube).turnRightAnti();
		verifyNoMoreInteractions(cube);
	}

	@Test
	public void shouldDoLeftClock() throws Exception {
		moveGenerator.doMove(Moves.LEFT_CLOCK);
		verify(cube).turnLeftClock();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoLeftAnti() throws Exception {
		moveGenerator.doMove(LEFT_ANTI);
		verify(cube).turnLeftAnti();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoUpClock() throws Exception {
		moveGenerator.doMove(UP_CLOCK);
		verify(cube).turnUpClock();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoUpAnti() throws Exception {
		moveGenerator.doMove(UP_ANTI);
		verify(cube).turnUpAnti();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoDownClock() throws Exception {
		moveGenerator.doMove(Moves.DOWN_CLOCK);
		verify(cube).turnDownClock();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoDownAnti() throws Exception {
		moveGenerator.doMove(DOWN_ANTI);
		verify(cube).turnDownAnti();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoFrontClock() throws Exception {
		moveGenerator.doMove(FRONT_CLOCK);
		verify(cube).turnFrontClock();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoFrontAnti() throws Exception {
		moveGenerator.doMove(FRONT_ANTI);
		verify(cube).turnFrontAnti();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoBackClock() throws Exception {
		moveGenerator.doMove(BACK_CLOCK);
		verify(cube).turnBackClock();
		verifyNoMoreInteractions(cube);
	}
	
	@Test
	public void shouldDoBackAnti() throws Exception {
		moveGenerator.doMove(BACK_ANTI);
		verify(cube).turnBackAnti();
		verifyNoMoreInteractions(cube);
	}










}
