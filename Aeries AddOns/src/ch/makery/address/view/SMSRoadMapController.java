package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.Student;
import ch.makery.address.StudentClasses;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by Eric on 6/23/2016.
 */
public class SMSRoadMapController {

    public String openPath;
    public String savePath;
    public File openFile;
    public File saveFile;
    private String reportTitle = "";
    private String timeStamp = "";

    @FXML
    private Button chooseFile;
    @FXML
    private  Button produceHTML;
    @FXML
    private Button chooseFileStudentList;
    @FXML
    private Button produceCSV;
    @FXML
    private Button backButton;

    ContextMenu studentListContextMenu;
    ContextMenu studentClassesContextMenu;


    private MainApp mainApp;

    ArrayList<StudentClasses> studentList;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public SMSRoadMapController() {
        studentList = new ArrayList<StudentClasses>();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        //Choose student file button plus context menu code
        studentListContextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("CSV FORMAT: last,first,id,phone,email,grade");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        MenuItem item4 = new MenuItem("QUERY: LIST STU LN FN ID TL PEM GR");
        item4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        studentListContextMenu.getItems().addAll(item1,item4);
        chooseFileStudentList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initFile();
                createMasterStudentList();
                //JOptionPane.showMessageDialog(null,"Students Imported");
            }
        });
        chooseFileStudentList.setContextMenu(studentListContextMenu);


        //back button code
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

        //Choose student classes button code and context menus
        studentClassesContextMenu = new ContextMenu();
        MenuItem item2 = new MenuItem("CSV FORMAT: id,last,first,courseNum,courseName,period");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        //LIST STU SSS SMS STU.ID STU.LN STU.FN SSS.CN CRS.CO SMS.PD BY STU.ID SMS.PD
        MenuItem item3 = new MenuItem("SMS QUERY: LIST STU SSS SMS STU.ID STU.LN STU.FN SSS.CN CRS.CO SMS.PD BY STU.ID SMS.PD");
        item3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        MenuItem item5 = new MenuItem("MST QUERY: LIST STU SEC MST CRS STU.ID STU.LN STU.FN MST.CN CRS.CO MST.PD BY STU.ID MST.PD");
        item5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        studentClassesContextMenu.getItems().addAll(item2, item3,item5);
        chooseFile.setContextMenu(studentClassesContextMenu);
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
                            System.out.println(sCurrentLine);
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

                        String lastName = MainApp.getFromString(MainApp.studentStringList.get(i), 1);
                        System.out.print(lastName + " ");
                        String firstName = MainApp.getFromString(MainApp.studentStringList.get(i), 2);
                        System.out.print(firstName + " ");
                        int iD = Integer.parseInt(MainApp.getFromString(MainApp.studentStringList.get(i), 0));
                        System.out.print("" + iD + " ");
                        String courseNum = MainApp.getFromString(MainApp.studentStringList.get(i), 3);
                        System.out.print(courseNum + " ");
                        String courseName = MainApp.getFromString(MainApp.studentStringList.get(i), 4);
                        System.out.print(courseName + " ");
                        int period = (Integer.parseInt(MainApp.getFromString(MainApp.studentStringList.get(i), 5)));
                        System.out.print("" + period);

                        StudentClasses studentClasses = getStudentByID(iD);
                        studentClasses.appendClass(courseNum, courseName, period);

                        //MainApp.studentList.add(new Student(firstName, lastName, primaryEmail, grade, iD, primaryPhone, hours));
                        System.out.println(i+1);
                        //System.out.println(lastName + "," + firstName + "," + iD + "," + primaryPhone + "," + primaryEmail + "," + hours);
                    }

                    //System.out.println(MainApp.studentList.toString());
					/*for(Student s: MainApp.studentList) {
						System.out.println(s);
					}*/
                }
                //JOptionPane.showMessageDialog(null,"Student Classes Imported");

            }});


        produceHTML.setOnAction(new EventHandler<ActionEvent>() {


            public void handle(ActionEvent event) {

                initFileSaver();

                if(saveFile != null)
                {
                    savePath = saveFile.getAbsolutePath();

                    try {
                        //MainApp.createTableServiceHours(savePath, MainApp.resultList, reportTitle, timeStamp);
                        createHTMLOutput(savePath, studentList, reportTitle, timeStamp);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                //JOptionPane.showMessageDialog(null,"HTML File Completed");
            }});

        produceCSV.setOnAction(new EventHandler<ActionEvent>() {


            public void handle(ActionEvent event) {

                initFileSaver();

                if(saveFile != null)
                {
                    savePath = saveFile.getAbsolutePath();

                    try {
                        //MainApp.createTableServiceHours(savePath, MainApp.resultList, reportTitle, timeStamp);
                        createCSVOutput(savePath, studentList, reportTitle, timeStamp);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                //JOptionPane.showMessageDialog(null,"CSV File Completed");
            }});

    }

    public static void createCSVOutput(String path, ArrayList<StudentClasses> students, String title, String timestamp) throws IOException {

        String output = "";
        File myFile = new File(path + ".csv");

        if (!myFile.exists()) {
            myFile.createNewFile();
        }

        Formatter writer = new Formatter(myFile);
        output += "Last,First,Grade,ID,Period 0,Period 1,Period 2,Period 3,Period 4,Period 5,Period 6,Period 7,Period 8\r\n";

        for (int i = 0; i < students.size(); i++) {
            StudentClasses s = students.get(i);
            output += s.getLastName();
            output += "," + s.getFirstName();
            output += "," + s.getStudentGrade();
            output += "," + s.getiD();

            for(int j=0; j < 10; j++) {
                output += "," + s.getClass(j);
            }
            output += "\r\n";
        }

        writer.format(output);
        writer.close();
    }

    public static void createHTMLOutput(String path, ArrayList<StudentClasses> students, String title, String timestamp) throws IOException {

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
        output += "<tr bgcolor='#99FF66'> <td>Last</td><td>First</td><td>Grade</td><td>ID</td><td>Period 1</td><td>Period 2</td><td>Period 3</td><td>Period 4</td><td>Period 5</td><td>Period 6</td><td>Period 7</td><td>Period 8</td>";

        for (int i = 0; i < students.size(); i++) {
            StudentClasses s = students.get(i);
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

            output += "<td>";
            output += s.getStudentGrade();
            output += "</td>";

            output += "<td>";
            output += s.getiD();
            output += "</td>";

            for(int j=1; j < 10; j++) {
                if("".equals(s.getClass(j))) {
                    output += "<td bgcolor='#FF6666'>";
                }
                else{
                    output += "<td>";
                }
                output += s.getClass(j);
                output += "</td>";
            }

            output += "</tr>";
        }
        output += "</table><br><br>";
        output += "<a rel='license' href='http://creativecommons.org/licenses/by-nc-sa/4.0/'><img alt='Creative Commons License' style='border-width:0' src='https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png' /></a><br />This work is licensed under a <a rel='license' href='http://creativecommons.org/licenses/by-nc-sa/4.0/'>Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>.";
        output += "<br>Authors: Gabe Marquez, Eric McElrea, Ulises Perez, Garrett Souza, Sean Thorington, Jerry Zhu";
        output += "</html>";
        writer.format(output);
        writer.close();
    }

    public StudentClasses getStudentByID(int id) {
        for(int i=0; i < studentList.size(); i++) {
            if(studentList.get(i).getiD() == id) {
                return studentList.get(i);
            }
        }

        return null;
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

                studentList.add(new StudentClasses(firstName, lastName, grade, iD));
                //System.out.println(i+1);
                //System.out.println(lastName + "," + firstName + "," + iD + "," + primaryPhone + "," + primaryEmail + "," + hours);
            }

            //System.out.println(MainApp.studentList.toString());
            for(int i=0; i < studentList.size(); i++) {
                StudentClasses s = studentList.get(i);
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
