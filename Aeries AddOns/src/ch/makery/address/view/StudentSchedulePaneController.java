package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.Student;
import ch.makery.address.StudentClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;


//grab by period - make it return a combined Class is there are two results!!!!
//It is currently grabbing zero period classes (diff, IPS)
    //grab by period maybe????
//it is not combining same period classes (speech and health)
    //grab by period and combine?

public class StudentSchedulePaneController {


    public String openPath;
    public String savePath;
    public File openFile;
    public File saveFile;
    private String reportTitle = "";
    private String timeStamp = "";

    ArrayList<String> allStrings;
    TreeMap<Student, ArrayList<StudentClass>> studentSchedules;

    @FXML
    private Button chooseFile;
    @FXML
    private  Button processFile;
    @FXML
    private  Button produceHTML;
    @FXML
    private Button processSingleStudent;
    @FXML
    private Button backButton;

    private MainApp mainApp;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StudentSchedulePaneController() {
        allStrings = new ArrayList<String>();
        studentSchedules = new TreeMap<Student, ArrayList<StudentClass>>();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        //initFile() happens when myFile is clicked

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

                if (openFile != null) {
                    openPath = openFile.getAbsolutePath();

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(openPath));
                        String sCurrentLine;

                        while ((sCurrentLine = br.readLine()) != null) {
                            allStrings.add(sCurrentLine);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    /*
                    for(String s: allStrings) {
                        System.out.println(s);
                    }*/

                    /*
                    LIST STU SEC MST CRS TCH STU.LN STU.FN STU.GR STU.ID STU.BD STU.GN MST.PD MST.SM MST.MP SEC.SE CRS.CN CRS.CO TCH.TN TCH.TE MST.RM IF MST.SM = Y OR MST.SM = F
                     */

                    for (int i = 0; i < allStrings.size(); i++) {

                        String s = allStrings.get(i);

                        String lastName = MainApp.getFromString(s, 0);
                        //System.out.print(lastName + " ");

                        String firstName = MainApp.getFromString(s, 1);
                        //System.out.print(firstName + " ");

                        int grade = Integer.parseInt(MainApp.getFromString(s, 2));
                        //System.out.print("" + grade + " ");

                        int id = Integer.parseInt(MainApp.getFromString(s, 3));
                        //System.out.print("" + id + " ");

                        String birthDate = MainApp.getFromString(s, 4);
                        //System.out.print(birthDate + " ");

                        String sex = MainApp.getFromString(s, 5);
                        //System.out.print(sex + " ");

                        int period = Integer.parseInt(MainApp.getFromString(s, 6));
                        //System.out.print("" + period + " ");

                        String term = MainApp.getFromString(s, 7);
                        //System.out.print(term + " ");

                        String meetingPattern = MainApp.getFromString(s, 8);
                        //System.out.print(meetingPattern + " ");

                        String section = MainApp.getFromString(s, 9);
                        //System.out.print("" + section + " ");

                        String course = MainApp.getFromString(s, 10);
                        //System.out.print("" + course + " ");

                        String title = MainApp.getFromString(s, 11);
                        //System.out.print(title + " ");

                        int teacherNum = Integer.parseInt(MainApp.getFromString(s, 12));
                        //System.out.print("" + teacherNum + " ");

                        String teacherName = MainApp.getFromString(s, 13);
                        //System.out.print(teacherName + " ");

                        String roomNum = MainApp.getFromString(s, 14);
                        //System.out.print(roomNum + " ");


                        Student student = new Student(firstName, lastName, grade, id, birthDate, sex);
                        System.out.println(student);
                        StudentClass myClass = new StudentClass(term, meetingPattern, section, course, title, teacherNum, teacherName, roomNum, period);
                        System.out.println(myClass);


                        //System.out.print(studentSchedules.get(student));
                        if (studentSchedules.get(student) == null) {
                            studentSchedules.put(student, new ArrayList<StudentClass>());
                            studentSchedules.get(student).add(myClass);
                        } else {
                            studentSchedules.get(student).add(myClass);
                        }

                        //System.out.println(i + 1);

                    }

                    //JOptionPane.showMessageDialog(null, "Student Schedules Imported\n   and Processed");


					/*for(Student s: MainApp.studentList) {
						System.out.println(s);
					}*/
                }

            }
        });

        produceHTML.setOnAction(new EventHandler<ActionEvent>() {


            public void handle(ActionEvent event) {

                //int reply = JOptionPane.showConfirmDialog(null, "This process will take several minutes.  Proceed?", "Proceed", JOptionPane.YES_NO_OPTION);
                //if(reply == JOptionPane.YES_OPTION) {
                    long startTime = System.currentTimeMillis();
                    initFileSaver();

                    if (saveFile != null) {
                        savePath = saveFile.getAbsolutePath();

                        try {
                            createCSVOutput(savePath, reportTitle, timeStamp);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                    long endTime = System.currentTimeMillis();
                    System.out.println("Total time: " + ((endTime-startTime)/1000) + " seconds");
                    //JOptionPane.showMessageDialog(null, "Operation Successful!  CSV file created.\nTotal Time: " + ((endTime - startTime)/1000) + " seconds");
                //}
            }});
    }

    public void createHTMLOutput(String path, String title, String timestamp) throws IOException {

        long startTime = System.currentTimeMillis();
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

        Iterator it = studentSchedules.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Student,ArrayList<StudentClass>> pair = (Map.Entry<Student,ArrayList<StudentClass>>)it.next();
            Student s = pair.getKey();
            ArrayList<StudentClass> classes = pair.getValue();

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


            for(int i=0; i < classes.size(); i++) {
                output += "<td>";
                output += classes.get(i).getPeriod();
                output += "</td>";


                output += "<td>";
                output += classes.get(i).getSemester();
                output += "</td>";

                output += "<td>";
                output += classes.get(i).getMeetingPattern();
                output += "</td>";

                output += "<td>";
                output += classes.get(i).getSection();
                output += "</td>";

                output += "<td>";
                output += classes.get(i).getCourseID();
                output += "</td>";

                output += "<td>";
                output += classes.get(i).getCourseTitle();
                output += "</td>";

                output += "<td>";
                output += classes.get(i).getTeacherID();
                output += "</td>";

                output += "<td>";
                output += classes.get(i).getTeacherName();
                output += "</td>";

                output += "<td>";
                output += classes.get(i).getRoomNumber();
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
        long endTime = System.currentTimeMillis();
        System.out.println("FINISHED PRODUCING HTML!!!");
        System.out.println("Total time: " + ((endTime-startTime)/1000) + " seconds");
    }

    public void createCSVOutput(String path, String title, String timestamp) throws IOException {

        System.out.println("Check 1");
        String output = "";
        File myFile = new File(path + ".csv");

        if (!myFile.exists()) {
            myFile.createNewFile();
        }

        Formatter writer = new Formatter(myFile);

        //output += "last,first,grade,id,birthdate,sex,period1,term1,meeting1,section1,course1,title1,teacher id1,teacher1,room1";
        output += "last,first,grade,id,";
        output += "period 1, room 1, teacher 1,";
        output += "period 2, room 2, teacher 2,";
        output += "period 3, room 3, teacher 3,";
        output += "period 4, room 4, teacher 4,";
        output += "period 5, room 5, teacher 5,";
        output += "period 6, room 6, teacher 6,";
        output += "period 7, room 7, teacher 7";
        output += "\n";

        Iterator it = studentSchedules.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Student,ArrayList<StudentClass>> pair = (Map.Entry<Student,ArrayList<StudentClass>>)it.next();
            Student s = pair.getKey();
            ArrayList<StudentClass> classes = pair.getValue();

            output += s.getLastName();
            output += ",";

            output += s.getFirstName();
            output += ",";

            output += s.getStudentGrade();
            output += ",";

            output += s.getiD();
            output += ",";


            for(int i=1; i <= 7; i++) {

                StudentClass temp = getPeriod(classes,i);

                output += temp.getCourseTitle();
                output += ",";

                output += temp.getRoomNumber();
                output += ",";

                output += temp.getTeacherName();
                output += ",";

            }

            /*
            for(int i=0; i < classes.size(); i++) {

                output += classes.get(i).getCourseTitle();
                output += ",";

                output += classes.get(i).getRoomNumber();
                output += ",";

                output += classes.get(i).getTeacherName();
                output += ",";

            }*/

            output += "\n";
        }
        writer.format(output);
        writer.close();
        long endTime = System.currentTimeMillis();
        System.out.println("FINISHED PRODUCING HTML!!!");

    }

    public StudentClass getPeriod(ArrayList<StudentClass> classes, int period) {
        ArrayList<StudentClass> list = new ArrayList<StudentClass>();
        for(int i=0; i < classes.size(); i++) {
            if(classes.get(i).getPeriod() == period)
                list.add(classes.get(i));
        }

        if(list.size() == 1) {
            return list.get(0);
        }
        else if(list.size() == 2) {
            //public StudentClass(String semester, String meetingPattern, String section, String courseID, String courseTitle, int teacherID, String teacherName, String roomNumber, int period) {
            StudentClass temp = new StudentClass("Year", "na",
                    "1111", "1111",
                    list.get(0).getCourseTitle() + ";" + list.get(1).getCourseTitle(),
                    1,
                    list.get(0).getTeacherName() + ";" + list.get(1).getTeacherName(),
                    list.get(0).getRoomNumber() + ";" + list.get(1).getRoomNumber(),
                    list.get(0).getPeriod());
            return temp;
        }

        return null;
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
