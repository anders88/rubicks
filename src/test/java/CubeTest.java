import static org.fest.assertions.Assertions.assertThat;
import no.anksoft.rubiks.Cube;

import org.junit.Test;


public class CubeTest {
	@Test
	public void sameCubeShouldBeEqual() throws Exception {
		assertThat(Cube.finished()).isEqualTo(Cube.finished()).isNotEqualTo(Cube.finished().turnBackAnti());
	}
	
	@Test
	public void shoulClone() throws Exception {
		Cube cube = Cube.finished();
		Cube clone = cube.clone();
		assertThat(cube).isNotSameAs(clone).isEqualTo(clone);
		cube.turnBackAnti();
		assertThat(cube).isNotEqualTo(clone);
	}
}
