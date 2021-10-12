package by.grodno.krivosheev.adminserver;

import by.grodno.krivosheev.adminserver.configs.WebSecurityConfig;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdminServerApplicationTests {
	@Autowired
	private WebSecurityConfig webSecurityConfig;

	@Test
	void contextLoads() {
		assertThat(webSecurityConfig).isNotNull();
	}
}
