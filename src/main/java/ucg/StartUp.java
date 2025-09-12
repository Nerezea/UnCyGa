package ucg;

import javax.sql.DataSource;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ucg.database.DatabaseCollector;
import ucg.database.InitializeDatabase;

@SpringBootApplication
public class StartUp implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartUp.class);

    private final InitializeDatabase initializer;
    private final DatabaseCollector collector;
    private final ConfigurableApplicationContext applicationContext;

    public StartUp(
            InitializeDatabase initializer,
            DatabaseCollector collector,
            DataSource dataSource,
            ConfigurableApplicationContext applicationContext
    ) {
        this.initializer = initializer;
        this.collector = collector;
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(StartUp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            collector.collectData();
        } catch (Exception exception) {
            initializer.initializeDatabase();
        }

        /*
        JavaFX an/aus
        */
        JavaFXApplication.setSpringContext(applicationContext);
        Application.launch(JavaFXApplication.class, args);

    }
}
