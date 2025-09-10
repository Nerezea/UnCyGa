package ucg.javafx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ucg.database.DatabaseCollector;
import ucg.database.DatabaseMatching;
import ucg.entity.Teams;

@Component
public class MainScreen {

	private final DatabaseCollector	collector;
	private final DatabaseMatching	matching;

	@Autowired
	public MainScreen(DatabaseCollector collector, DatabaseMatching matching) {
		this.collector = collector;
		this.matching = matching;
	}

	public Scene getScreen() {
		//Label
		Label teamHome = new Label("Unknown");
		Label teamAway = new Label("Unknown");

		// TextField
		TextField text = new TextField();
		TextField winsHome = new TextField("0");
		TextField winsAway = new TextField("0");

		// List View
		ListView<String> listView = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList();
		listView.setItems(items);

		// Button
		Button getAllTeams = new Button("Get All Teams");
		getAllTeams.setOnAction(e -> {
			items.clear();
			List<Teams> teamList = collector.collectData();
			for (Teams team : teamList) {
				items.add(team.getName());
			}
		});

		Button createNewTeam = new Button("create New Team");
		createNewTeam.setOnAction(e -> {
			collector.saveTeam(text.getText());
		});

		Button deleteTeam = new Button("delete Team");
		deleteTeam.setOnAction(e -> {
			collector.deleteTeam(text.getText());
		});

		// Center Layout Content
		Button matchdayButton = new Button("matchday");
		Button matchingButton = new Button("matching");

		HBox buttons = new HBox(10, matchingButton, matchdayButton);
		buttons.setAlignment(Pos.CENTER);

		matchingButton.setOnAction(e -> {
			List<Teams> match = matching.matchingTeams();
			teamHome.setText(match.get(0).getName());
			teamAway.setText(match.get(1).getName());
		});

		matchdayButton.setOnAction(e -> {
			matching.matchday(teamHome.getText(), Integer.parseInt(winsHome.getText()), teamAway.getText(), Integer.parseInt(winsAway.getText()));
		});

		winsHome.setMaxWidth(35);
		winsAway.setMaxWidth(35);

		HBox homeAgainstAway = new HBox(10);
		homeAgainstAway.setAlignment(Pos.CENTER);
		homeAgainstAway.getChildren().addAll(teamHome, winsHome, winsAway, teamAway);

		//Right Layout
		VBox rightLayout = new VBox(20);
		rightLayout.getChildren().addAll(getAllTeams, listView);

		//Center Layout
		VBox centerLayout = new VBox(20);
		centerLayout.getChildren().addAll(buttons, homeAgainstAway);
		centerLayout.setAlignment(Pos.TOP_CENTER);

		//Left Layout
		VBox leftLayout = new VBox(60);
		leftLayout.getChildren().addAll(createNewTeam, text, deleteTeam);

		//Main Scene
		BorderPane mainScene = new BorderPane();
		mainScene.setRight(rightLayout);
		mainScene.setLeft(leftLayout);
		mainScene.setCenter(centerLayout);

		return new Scene(mainScene, 1200, 800);
	}
}
