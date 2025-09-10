package ucg;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;
import ucg.database.DatabaseCollector;
import ucg.database.InitializeDatabase;

@SpringBootApplication()

public class StartUp
        implements
        CommandLineRunner {

    @Autowired
    private ApplicationConfiguration config;
    @SuppressWarnings("unused")
    @Autowired
    private SpringContext context;

    private static Logger logger = (Logger) LoggerFactory.getLogger(StartUp.class);
    private InitializeDatabase initializer;
    private DatabaseCollector collector;

    @Autowired
    public StartUp(final InitializeDatabase initializer, final DatabaseCollector collector) {
        this.initializer = initializer;
        this.collector = collector;
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
        Application.launch(JavaFXApplication.class, args);
    }

    @PostConstruct
    public void init() {
        logger.info(config.toString());
    }
}
