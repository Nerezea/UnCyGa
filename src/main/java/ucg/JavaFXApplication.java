package ucg;

import org.springframework.stereotype.Component;

import javafx.application.Application;
import javafx.stage.Stage;
import ucg.javafx.ConfirmBox;
import ucg.javafx.MainScreen;

@Component
public class JavaFXApplication extends Application {

	private Stage		window;

	private MainScreen	main;
	private ConfirmBox	confirmBox;

	public JavaFXApplication() {
		this.main = SpringContext.getBean(MainScreen.class);
		this.confirmBox = SpringContext.getBean(ConfirmBox.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Universe Cyber Games");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		window.setScene(main.getScreen());
		window.show();
	}

	private void closeProgram() {
		Boolean answer = confirmBox.display("Close Application", "You really want to close the application?");
		if (answer) {
			window.close();
			System.exit(0);
		}
	}
}
