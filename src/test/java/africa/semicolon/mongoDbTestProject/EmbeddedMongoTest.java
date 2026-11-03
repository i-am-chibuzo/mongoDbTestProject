package africa.semicolon.mongoDbTestProject;

import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.*;

/** Drop-in replacement for {@link DataMongoTest} that runs against a shared embedded MongoDB. */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@DataMongoTest
@ContextConfiguration(initializers = EmbeddedMongoTest.Initializer.class)
public @interface EmbeddedMongoTest {

    class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        private static String uri;

        private static synchronized String uri() {
            if (uri == null) {
                var mongod = Mongod.instance().start(Version.Main.V7_0);
                Runtime.getRuntime().addShutdownHook(new Thread(mongod::close));
                var address = mongod.current().getServerAddress();
                uri = "mongodb://" + address.getHost() + ":" + address.getPort() + "/test";
            }
            return uri;
        }

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of("spring.mongodb.uri=" + uri()).applyTo(context);
        }
    }
}
