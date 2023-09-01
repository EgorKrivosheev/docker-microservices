package by.grodno.krivosheev.eurekaserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EurekaServerApplicationTests {

    @Test
    void contextLoads() {
        assertThat(this).isNotNull();
    }
}
