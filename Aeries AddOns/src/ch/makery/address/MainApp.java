package ch.makery.address;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ListIterator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static Stage primaryStage;
	public static BorderPane rootLayout;

	public static ArrayList<String> studentStringList = new ArrayList<String>();
	public static ArrayList<Student> studentList = new ArrayList<Student>();
	public static ArrayList<Student> resultList = new ArrayList<Student>();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Aeries Add-ons");


		initRootLayout();
		initFirstScene();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			rootLayout.setId("pane");

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void initFirstScene() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * @return
	 */

	public static void initFileChooser() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/FileChooser.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set fileChooser into the center of root layout.
			MainApp.rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void initStudentSchedulePane() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StudentSchedulePane.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set fileChooser into the center of root layout.
			MainApp.rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initSMSRoadMapPane() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SMSRoadMap.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set fileChooser into the center of root layout.
			MainApp.rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initAthleticEligibilityPane() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AthleticEligibilityPane.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set fileChooser into the center of root layout.
			MainApp.rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initCalGrantPane() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CalGrantPane.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set fileChooser into the center of root layout.
			MainApp.rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void bubbleMeHours(ArrayList<Student> sortMe) {
		
		for(int i=0; i < sortMe.size(); i++)
		{
			for(int j=0; j < sortMe.size()-1; j++) {
				if(sortMe.get(j).getiD() > sortMe.get(j+1).getiD()) {
					Student temp = sortMe.get(j);
					sortMe.set(j, sortMe.get(j+1));
					sortMe.set(j+1, temp);
				}
			}
		}
	}
	
   public static void bubbleMeHoursLN(ArrayList<Student> sortMe) {
		
		for(int i=0; i < sortMe.size(); i++)
		{
			for(int j=0; j < sortMe.size()-1; j++) {
				if(sortMe.get(j).getLastName().compareTo(sortMe.get(j+1).getLastName()) > 0) {
					Student temp = sortMe.get(j);
					sortMe.set(j, sortMe.get(j+1));
					sortMe.set(j+1, temp);
				}
			}
		}
	   System.out.println("Print student name after bubble me last name");
	   System.out.println("--------------------------------------");
	   printStudentNames(sortMe);

   }

	/**
	 * Parameter allStudents is iterated through, creating a separate Arraylist
	 * of Student objects that are seniors.  The ArrayList resultList field
	 * is assigned this new list of seniors only.
	 * Precondition:	only one entry per student in the allStudents Arraylist.
	 * 					Call combineServiceHours method prior to this method if multiple records per Student exist.
	 * @param allStudents	all the Students (one entry per student)
	 * @author				Eric McElrea
	 */
	public static void createListSeniors(ArrayList<Student> allStudents)
	{
		ArrayList<Student> seniors = new ArrayList<Student>();

		for(Student s: allStudents) {
			if(s.getStudentGrade() == 12) {
				seniors.add(s);
			}
		}

		resultList = seniors;
	}


	//combines duplicate records together, adding their hours
	public static void combineServiceHours(ArrayList<Student> uncombined, ArrayList<Student> combined)
	{
		ArrayList<Student> sorted = uncombined;

		
		bubbleMeHours(sorted);
		printStudentNames(sorted);

		if(sorted.size() > 0)
	        {
	            Student cur = sorted.get(0);

	            ListIterator<Student> iter = sorted.listIterator();
	            if(iter.hasNext())
	                iter.next();
	            while(iter.hasNext())
	            {
	                Student sIter = iter.next();
	                if(cur.getiD() == sIter.getiD())
	                {
	                    cur.setHours(cur.getHours() + sIter.getHours());
	                }
	                else
	                {
	                    combined.add(cur);
	                    cur = sIter;
	                } 
	            }
	            combined.add(cur);
	        }

		System.out.println("Print student name after combineServiceHours");
		System.out.println("--------------------------------------");
		printStudentNames(combined);
		
	}

	public static boolean isSortedForServiceHours(ArrayList<Student> list)
	{
		for(int i = 0; i < list.size()-1; i++)
		{
			if(list.get(i).getiD() > list.get(i++).getiD())
				return false;
		}

		return true;
	}

	public static void printStudentNames(ArrayList<Student> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}
	}

	public static String getFromString(String csv, int n) {

		int commaIndex = 0;
		String info = "";
		for(int i = 0; i < csv.length(); i++) {
			if(csv.substring(i, i+1).equals(",")) {
				commaIndex += 1;
			}
			if(commaIndex == n) {
				if(!csv.substring(i, i+1).equals(","))
					info += csv.substring(i, i+1);
			}
		}


		return info;

	}

	public static void createTableServiceHours(String path, ArrayList<Student> students, String title, String timestamp) throws IOException {

		String output = "";
		File myFile = new File(path + ".html");

		if (!myFile.exists()) {
			myFile.createNewFile();
		}

		Formatter writer = new Formatter(myFile);
		output += "<html>";
		output += "<H1>" + title + "</H1>";
		output += "Timestamp: " + timestamp;
		output += "<hr>";
		output += "<table border='1'>";
		output += "<tr bgcolor='#99FF66'> <td>Last</td><td>First</td><td>Hours</td><td>Grade</td><td>ID</td><td>Email</td><td>Phone</td></tr>";

		for (int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			if(i % 2 == 1)
				output += "<tr bgcolor='#D1DCC2'>";
			else
				output += "<tr>";

			output += "<td>";
			output += s.getLastName();
			output += "</td>";

			output += "<td>";
			output += s.getFirstName();
			output += "</td>";

			if(s.getHours() < 100)
			{
				output += "<td bgcolor='#FF0000'>";
				output += s.getHours();
				output += "</td>";
			}
			else
			{
				output += "<td>";
				output += s.getHours();
				output += "</td>";
			}

			output += "<td>";
			output += s.getStudentGrade();
			output += "</td>";

			output += "<td>";
			output += s.getiD();
			output += "</td>";

			output += "<td>";
			output += s.getPrimaryEmail();
			output += "</td>";

			output += "<td>";
			output += s.getPrimaryPhone();
			output += "</td>";

			output += "</tr>";
		}
		output += "</table><br><br>";
		output += "<a rel='license' href='http://creativecommons.org/licenses/by-nc-sa/4.0/'><img alt='Creative Commons License' style='border-width:0' src='https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png' /></a><br />This work is licensed under a <a rel='license' href='http://creativecommons.org/licenses/by-nc-sa/4.0/'>Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>.";
		output += "<br>Authors: Gabe Marquez, Eric McElrea, Ulises Perez, Garrett Souza, Sean Thorington, Jerry Zhu";
		output += "</html>";
		writer.format(output);
		writer.close();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

