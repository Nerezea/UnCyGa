package ucg;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;

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
    private final DataSource dataSource;
    private final ConfigurableApplicationContext applicationContext;

    public StartUp(
            InitializeDatabase initializer,
            DatabaseCollector collector,
            DataSource dataSource,
            ConfigurableApplicationContext applicationContext
    ) {
        this.initializer = initializer;
        this.collector = collector;
        this.dataSource = dataSource;
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(StartUp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            collector.collectData();
        } catch (Exception e) {
            initializer.initializeDatabase();
        }

        // Den bereits laufenden Spring-Context an die JavaFX-App übergeben
        JavaFXApplication.setSpringContext(applicationContext);

        // JavaFX starten (ohne erneut Spring zu booten)
        Application.launch(JavaFXApplication.class, args);
    }

    @PostConstruct
    public void init() {
        try (Connection c = dataSource.getConnection()) {
            logger.info("Connected to DB: {} as user {}", c.getMetaData().getURL(), c.getMetaData().getUserName());
        } catch (Exception e) {
            logger.warn("DB connection check failed: {}", e.getMessage());
        }
    }
}
