import static org.fest.assertions.Assertions.assertThat;

import no.anksoft.rubiks.Cube;

import org.junit.Test;


public class CubeTest {
	@Test
	public void sameCubeShouldBeEqual() throws Exception {
		assertThat(Cube.finished()).isEqualTo(Cube.finished()).isNotEqualTo(Cube.finished().turnBackAnti());
	}
}
