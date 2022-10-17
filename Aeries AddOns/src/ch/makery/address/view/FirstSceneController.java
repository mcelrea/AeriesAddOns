package ch.makery.address.view;

import java.io.IOException;

import ch.makery.address.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FirstSceneController {

	@FXML
	private Button fixHoursButton;
	@FXML
	private Button studentSchedulesButton;
	@FXML
	private Button athleticEligibilityButton;
	@FXML
	private Button calGrantGPAButton;
	@FXML
	private Button smsRoadmapButton;

	private MainApp mainApp;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public FirstSceneController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		//MainApp.initFileChooser() happens when the fixHoursButton is clicked
		fixHoursButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				MainApp.initFileChooser();

			}});

		smsRoadmapButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				MainApp.initSMSRoadMapPane();

			}});



		studentSchedulesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				MainApp.initStudentSchedulePane();

			}});

		athleticEligibilityButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				MainApp.initAthleticEligibilityPane();

			}});

		calGrantGPAButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				MainApp.initCalGrantPane();

			}});
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		
	}	
}


