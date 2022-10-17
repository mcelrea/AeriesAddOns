package ch.makery.address.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

import ch.makery.address.MainApp;
import ch.makery.address.Student;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import sun.applet.Main;

import javax.swing.*;

public class FileChooserController {


	public String openPath;
	public String savePath;
	public File openFile;
	public File saveFile;
	private String reportTitle = "";
	private String timeStamp = "";
	
	@FXML
	private  Button chooseFile;
	//@FXML
	//private  Button processFile;
	@FXML
	private  Button produceHTML;
	@FXML
	private Button processSeniors;
	@FXML
	private Button chooseFileStudentList;
	@FXML
	private Button backButton;
	@FXML
	private Button produceCSV;


	private MainApp mainApp;

	ArrayList<Student> studentList;
	ArrayList<Student> hoursList;


	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public FileChooserController() {
		studentList = new ArrayList<Student>();
		hoursList = new ArrayList<Student>();
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		//initFile() happens when myFile is clicked

		chooseFileStudentList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				initFile();
				createMasterStudentList();
			}
		});

		produceCSV.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				initFileSaver();

				if(saveFile != null)
				{
					savePath = saveFile.getAbsolutePath();

					try {
						//MainApp.createTableServiceHours(savePath, MainApp.resultList, reportTitle, timeStamp);
						createCSVOutput(savePath, reportTitle, timeStamp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				//JOptionPane.showMessageDialog(null, "CSV File Completed");
			}
		});

		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Load person overview.
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
					AnchorPane personOverview = (AnchorPane) loader.load();
					MainApp.rootLayout.setCenter(personOverview);
				} catch(Exception e) {e.printStackTrace();}
			}
		});

		chooseFile.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				initFile();

				if(openFile != null)
				{
					openPath = openFile.getAbsolutePath();
					try
					{
						BufferedReader br = new BufferedReader(new FileReader(openPath));
						String sCurrentLine;
						MainApp.studentStringList.clear();
						while ((sCurrentLine = br.readLine()) != null) {
							MainApp.studentStringList.add(sCurrentLine);
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

					/*
					 * Change the String to an actual student, String order follows
					 *  (1) last name
					 *  (2) first name
					 *  (3) id
					 *  (4) primary phone
					 *  (5) primary email
					 *  (6) code
					 *  (7) descriptiorn
					 *  (8) hours
					 *  (9) start date
					 *  (10) end date
					 *  (11) grade
					 */
					for(int i = 0; i < MainApp.studentStringList.size(); i++) {

						String lastName = MainApp.getFromString(MainApp.studentStringList.get(i), 0);
						System.out.print(lastName + " ");
						String firstName = MainApp.getFromString(MainApp.studentStringList.get(i), 1);
						System.out.print(firstName + " ");
						int iD = Integer.parseInt(MainApp.getFromString(MainApp.studentStringList.get(i), 2));
						System.out.print("" + iD + " ");
						String primaryPhone = MainApp.getFromString(MainApp.studentStringList.get(i), 3);
						System.out.print(primaryPhone + " ");
						String primaryEmail = MainApp.getFromString(MainApp.studentStringList.get(i), 4);
						System.out.print(primaryEmail + " ");
						double hours = Double.parseDouble(MainApp.getFromString(MainApp.studentStringList.get(i), 7));
						System.out.print("" + hours + " ");
						int grade = Integer.parseInt(MainApp.getFromString(MainApp.studentStringList.get(i), 10));
						System.out.print("" + grade + " ");

						hoursList.add(new Student(firstName, lastName, primaryEmail, grade, iD, primaryPhone, hours));
						System.out.println(i+1);
						//System.out.println(lastName + "," + firstName + "," + iD + "," + primaryPhone + "," + primaryEmail + "," + hours);
					}

					//System.out.println(MainApp.studentList.toString());
					/*for(Student s: MainApp.studentList) {
						System.out.println(s);
					}*/
				}

			}});


		/*
		processFile.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				MainApp.combineServiceHours(MainApp.studentList, MainApp.resultList);
				MainApp.bubbleMeHoursLN(MainApp.resultList);
				reportTitle = "Service Hours Report (All Students)";
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date date = new Date();
				timeStamp = dateFormat.format(date);
				
				//System.out.println("Watch this Ish");
				//System.out.println("-------------------------------------");
			}});
			*/

		produceHTML.setOnAction(new EventHandler<ActionEvent>() {


			public void handle(ActionEvent event) {

				initFileSaver();
				
				if(saveFile != null)
				{
					savePath = saveFile.getAbsolutePath();
					
					try {
							//MainApp.createTableServiceHours(savePath, MainApp.resultList, reportTitle, timeStamp);
						MainApp.createTableServiceHours(savePath, studentList, reportTitle, timeStamp);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
			}});
		
		processSeniors.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				reportTitle = "Service Hours Report (Seniors Only)";
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date date = new Date();
				timeStamp = dateFormat.format(date);


				for(int i=0; i < hoursList.size(); i++) {
					Student hoursEntry = hoursList.get(i);
					Student currentStudent = getStudentByID(hoursEntry.getiD());
					System.out.println("Found entry for " + hoursEntry.getiD() + " matched to " + currentStudent);
					if(currentStudent != null) {
						currentStudent.setHours(currentStudent.getHours() + hoursEntry.getHours());
					}
					else {
						System.out.println("WARNING: COULDN'T FIND STUDENT ID FOR HOURS ENTRY");
					}
				}
				/*
				MainApp.combineServiceHours(MainApp.studentList, MainApp.resultList);
				MainApp.createListSeniors(MainApp.resultList);
				MainApp.bubbleMeHoursLN(MainApp.resultList);
				reportTitle = "Service Hours Report (Seniors Only)";
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date date = new Date();
				timeStamp = dateFormat.format(date);
				*/
			}});

	}

	public Student getStudentByID(int id) {
		for(int i=0; i < studentList.size(); i++) {
			if(studentList.get(i).getiD() == id) {
				return studentList.get(i);
			}
		}

		return null;
	}

	public void createCSVOutput(String path, String title, String timestamp) throws IOException {

		String output = "";
		File myFile = new File(path + ".csv");

		if (!myFile.exists()) {
			myFile.createNewFile();
		}

		Formatter writer = new Formatter(myFile);
		output += "last,first,hours,grade,id,email,phone\r\n";

		for (int i = 0; i < studentList.size(); i++) {
			Student s = studentList.get(i);
			output += s.getLastName();
			output += "," + s.getFirstName();
			output += "," + s.getHours();
			output += "," + s.getStudentGrade();
			output += "," + s.getiD();
			output += "," + s.getPrimaryEmail();
			output += "," + s.getPrimaryPhone();

			output += "\r\n";
		}

		writer.format(output);
		writer.close();
	}

	public void createMasterStudentList() {
		if(openFile != null)
		{
			openPath = openFile.getAbsolutePath();
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(openPath));
				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					MainApp.studentStringList.add(sCurrentLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

					/*
					 * Change the String to an actual student, String order follows
					 *  (1) last name
					 *  (2) first name
					 *  (3) id
					 *  (4) primary phone
					 *  (5) primary email
					 *  (6) grade
					 */
			for(int i = 0; i < MainApp.studentStringList.size(); i++) {

				String lastName = MainApp.getFromString(MainApp.studentStringList.get(i), 0);
				//System.out.print(lastName + " ");
				String firstName = MainApp.getFromString(MainApp.studentStringList.get(i), 1);
				//System.out.print(firstName + " ");
				int iD = Integer.parseInt(MainApp.getFromString(MainApp.studentStringList.get(i), 2));
				//System.out.print("" + iD + " ");
				String primaryPhone = MainApp.getFromString(MainApp.studentStringList.get(i), 3);
				//System.out.print(primaryPhone + " ");
				String primaryEmail = MainApp.getFromString(MainApp.studentStringList.get(i), 4);
				//System.out.print(primaryEmail + " ");
				int grade = Integer.parseInt(MainApp.getFromString(MainApp.studentStringList.get(i), 5));
				//System.out.print("" + grade + " ");

				studentList.add(new Student(firstName, lastName, primaryEmail, grade, iD, primaryPhone, 0));
				//System.out.println(i+1);
				//System.out.println(lastName + "," + firstName + "," + iD + "," + primaryPhone + "," + primaryEmail + "," + hours);
			}

			//System.out.println(MainApp.studentList.toString());
			for(int i=0; i < studentList.size(); i++) {
				Student s = studentList.get(i);
				System.out.println("" + i + "." + s);
			}
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}	

	//Loads the File Chooser
	public void initFile()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		openFile = fileChooser.showOpenDialog(MainApp.primaryStage);
	}


	public void initFileSaver() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Resource File");
		saveFile = fileChooser.showSaveDialog(MainApp.primaryStage);
	}
}
