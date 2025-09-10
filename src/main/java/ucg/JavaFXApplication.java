package ucg;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import ucg.javafx.ConfirmBox;
import ucg.javafx.MainScreen;

public class JavaFXApplication extends Application {

	private static ConfigurableApplicationContext springContext; // wird vor launch gesetzt
	private Stage window;

	// Wird von StartUp.run(...) aufgerufen, bevor Application.launch(...) erfolgt
	public static void setSpringContext(ConfigurableApplicationContext ctx) {
		springContext = ctx;
	}

	@Override
	public void start(Stage primaryStage) {
		if (springContext == null) {
			throw new IllegalStateException("Spring context is not set. Call JavaFXApplication.setSpringContext(...) before launching JavaFX.");
		}

		MainScreen main = springContext.getBean(MainScreen.class);
		ConfirmBox confirmBox = springContext.getBean(ConfirmBox.class);

		window = primaryStage;
		window.setTitle("Universe Cyber Games");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram(confirmBox);
		});
		window.setScene(main.getScreen());
		window.show();
	}

	private void closeProgram(ConfirmBox confirmBox) {
		Boolean answer = confirmBox.display("Close Application", "You really want to close the application?");
		if (answer) {
			window.close();
			// Ordnungsgemäß herunterfahren
			if (springContext != null) {
				springContext.close();
			}
			Platform.exit();
			System.exit(0);
		}
	}

	@Override
	public void stop() {
		// Falls der User das Fenster schließt ohne ConfirmBox (z.B. via OS)
		if (springContext != null) {
			springContext.close();
		}
	}
}
