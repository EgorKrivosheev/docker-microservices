package by.grodno.krivosheev.transformation;

import by.grodno.krivosheev.transformation.controllers.ExceptionHandlerController;
import by.grodno.krivosheev.transformation.controllers.TransformationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TransformationApplicationTests {
    @Autowired
    private TransformationController transformationController;

    @Autowired
    private ExceptionHandlerController exceptionHandlerController;

    @Test
    void contextLoads() {
        assertNotNull(transformationController);
        assertNotNull(exceptionHandlerController);
    }
}
