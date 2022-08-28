package microdtb.microdb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MicrodbApplicationTests {

	@Autowired
	private MainController main;

	@Test
	void contextLoads() {
	}

	@Test
	void adduser() {
		Respuesta res = main.addNewUser("felipe", "emai123@gmail.com", "arango", 10311);
		assertThat(res.getError()).isNull();
	}
}
