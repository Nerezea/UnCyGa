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
        } catch (Exception exception) {
            initializer.initializeDatabase();
        }

        JavaFXApplication.setSpringContext(applicationContext);
        Application.launch(JavaFXApplication.class, args);
    }

    @PostConstruct
    public void init() {
        try (Connection connect = dataSource.getConnection()) {
            logger.info("Connected to DB: {} as user {}", connect.getMetaData().getURL(), connect.getMetaData().getUserName());
        } catch (Exception exception) {
            logger.warn("DB connection check failed: {}", exception.getMessage());
        }
    }
}
