package africa.semicolon.mongoDbTestProject;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.*;

/** Drop-in replacement for {@link SpringBootTest} that runs against the shared embedded MongoDB. */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootTest
@ContextConfiguration(initializers = EmbeddedMongoTest.Initializer.class)
public @interface EmbeddedMongoSpringBootTest {
}
