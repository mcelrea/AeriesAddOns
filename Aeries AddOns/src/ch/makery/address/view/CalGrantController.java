package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.StudentGPAs;
import ch.makery.address.StudentGrades;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;

/**
 * Created by Eric on 2/9/2016.
 */
public class CalGrantController {
    public String openPath;
    public String savePath;
    public File openFile;
    public File saveFile;
    private String reportTitle = "";
    private String timeStamp = "";

    ArrayList<String> allStrings;
    ArrayList<StudentGrades> allGrades;
    ArrayList<StudentGPAs> studentGPAs;

    @FXML
    private Button chooseFile;
    @FXML
    private  Button calcGPAs;
    @FXML
    private  Button produceCSV;
    @FXML
    private Button backButton;

    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public CalGrantController() {
        allStrings = new ArrayList<String>();
        allGrades = new ArrayList<StudentGrades>();
        studentGPAs = new ArrayList<StudentGPAs>();
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

                    System.out.println("START");
                    for (int i = 0; i < allStrings.size(); i++) {

                        String s = allStrings.get(i);

                        String temp2 = MainApp.getFromString(s, 0);
                        int id = Integer.parseInt(MainApp.getFromString(s, 0));

                        String lastName = MainApp.getFromString(s, 1);
                        //System.out.print(firstName + " ");

                        String firstName = MainApp.getFromString(s, 2);
                        //System.out.print(lastName + " ");

                        int grade = Integer.parseInt(MainApp.getFromString(s, 3));
                        //System.out.print("" + grade + " ");

                        String isSport = MainApp.getFromString(s, 9);
                        //System.out.print(sex + " ");

                        String isAcademic = MainApp.getFromString(s, 8);
                        //System.out.print(term + " ");

                        String academicGrade = MainApp.getFromString(s, 4);
                        //System.out.print(meetingPattern + " ");

                        double credit = Double.parseDouble(MainApp.getFromString(s, 5));

                        //String ssn = MainApp.getFromString(s,10);

                        StudentGrades studentGr = new StudentGrades(id, firstName, lastName, grade, "", isSport, isAcademic, academicGrade, credit);
                        //studentGr.setSsn(ssn);
                        allGrades.add(studentGr);
                        System.out.println(i + ". " + studentGr);
                    }
                    System.out.println("END");

                    /*
                    for (int i = 0; i < allGrades.size(); i++) {
                        System.out.println(allGrades.get(i));
                    }*/

                    //JOptionPane.showMessageDialog(null, "Student Grades Imported");
                    //System.out.println(MainApp.studentList.toString());
					/*for(Student s: MainApp.studentList) {
						System.out.println(s);
					}*/
                }

            }
        });


        calcGPAs.setOnAction(new EventHandler<ActionEvent>() {


            public void handle(ActionEvent event) {

                //int reply = JOptionPane.showConfirmDialog(null, "This process will take several minutes.  Proceed?", "Proceed", JOptionPane.YES_NO_OPTION);
                //if (reply == JOptionPane.YES_OPTION) {
                    long startTime = System.currentTimeMillis();

                    combineGradesAndCalcGPAs();

                    long endTime = System.currentTimeMillis();
                    System.out.println("Total time: " + ((endTime - startTime) / 1000) + " seconds");
                    //JOptionPane.showMessageDialog(null, "Operation Successful!  CSV file created.\nTotal Time: " + ((endTime - startTime) / 1000) + " seconds");
                    System.out.println("Finished calculating GPAs");
                //}
            }
        });

        produceCSV.setOnAction(new EventHandler<ActionEvent>() {


            public void handle(ActionEvent event) {

                //int reply = JOptionPane.showConfirmDialog(null, "This process will take several minutes.  Proceed?", "Proceed", JOptionPane.YES_NO_OPTION);
                //if (reply == JOptionPane.YES_OPTION) {
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
                    System.out.println("Total time: " + ((endTime - startTime) / 1000) + " seconds");
                    //JOptionPane.showMessageDialog(null, "Operation Successful!  CSV file created.\nTotal Time: " + ((endTime - startTime) / 1000) + " seconds");
                //}
            }
        });
    }

    public void createCSVOutput(String path, String title, String timestamp) throws IOException {

        String output = "";
        File myFile = new File(path + ".csv");

        if (!myFile.exists()) {
            myFile.createNewFile();
        }

        Formatter writer = new Formatter(myFile);

        output += "id,first,last,GPA,SSN";
        output += "\n";



        for(int i=0; i < studentGPAs.size(); i++) {

            StudentGPAs current = studentGPAs.get(i);

            output += current.getId();
            output += ",";

            output += current.getFirst();
            output += ",";

            output += current.getLast();
            output += ",";

            output += current.getGpaWithPE();
            output += ",";

            output += current.getSsn();
            output += ",";

            output += "\n";
        }

        writer.format(output);
        writer.close();
        long endTime = System.currentTimeMillis();
        System.out.println("FINISHED PRODUCING HTML!!!");

    }


    private void combineGradesAndCalcGPAs() {
        int id;
        String first;
        String last;
        String ssn;
        int grade;
        double creditAttempted=0;
        double creditEarned = 0;
        double credit;
        String academicGrade;
        int scalarGrade=0;

        if(allGrades.size() == 0) {
            return;
        }
        else {
            StudentGrades currentGrade = allGrades.get(0);
            id = currentGrade.getId();
            first = currentGrade.getFirst();
            last = currentGrade.getLast();
            grade = currentGrade.getGrade();
            academicGrade = currentGrade.getAcademicGrade();
            credit = currentGrade.getCredit();
            ssn = currentGrade.getSsn();

            if(academicGrade.contains("A"))
                scalarGrade = 4;
            else if(academicGrade.contains("B"))
                scalarGrade = 3;
            else if(academicGrade.contains("C"))
                scalarGrade = 2;
            else if(academicGrade.contains("D"))
                scalarGrade = 1;
            else if(academicGrade.contains("F")) {
                scalarGrade = 0;
            }
            else if(academicGrade.contains("I")) {
                scalarGrade = 0;
            }


            if(!currentGrade.getAcademicGrade().equals("")) {

                //if the grade corresponds to an academic class
                if (classIsAcademic(currentGrade)) {
                    creditEarned += scalarGrade * credit;
                    creditAttempted += credit;
                }
            }


            //for loop through the rest of the list
            for(int i=1; i < allGrades.size(); i++) {
                currentGrade = allGrades.get(i);

                //if this is a NEW student record
                if(currentGrade.getId() != id) {
                    double gpa = creditEarned/creditAttempted;
                    System.out.println(last + "," + first);
                    System.out.println("----------------------");
                    studentGPAs.add(new StudentGPAs(id, first, last, grade, gpa, 0, 0,ssn));


                    id = currentGrade.getId();
                    first = currentGrade.getFirst();
                    last = currentGrade.getLast();
                    grade = currentGrade.getGrade();
                    ssn = currentGrade.getSsn();
                    creditAttempted=0;
                    creditEarned=0;
                    credit=currentGrade.getCredit();
                    academicGrade = currentGrade.getAcademicGrade();
                    scalarGrade=0;

                    if(academicGrade.contains("A"))
                        scalarGrade = 4;
                    else if(academicGrade.contains("B"))
                        scalarGrade = 3;
                    else if(academicGrade.contains("C"))
                        scalarGrade = 2;
                    else if(academicGrade.contains("D"))
                        scalarGrade = 1;
                    else if(academicGrade.contains("F")) {
                        scalarGrade = 0;
                    }
                    else if(academicGrade.contains("I")) {
                        scalarGrade = 0;
                    }

                    if(!currentGrade.getAcademicGrade().equals("")) {
                        //if the grade corresponds to an academic class
                        if (classIsAcademic(currentGrade)) {
                            creditEarned += scalarGrade * credit;
                            creditAttempted += credit;
                        }
                    }
                }

                //this record is the same student as the last record
                else {
                    grade = currentGrade.getGrade();
                    credit=currentGrade.getCredit();
                    academicGrade = currentGrade.getAcademicGrade();

                    if(academicGrade.contains("A"))
                        scalarGrade = 4;
                    else if(academicGrade.contains("B"))
                        scalarGrade = 3;
                    else if(academicGrade.contains("C"))
                        scalarGrade = 2;
                    else if(academicGrade.contains("D"))
                        scalarGrade = 1;
                    else if(academicGrade.contains("F")) {
                        scalarGrade = 0;
                    }
                    else if(academicGrade.contains("I")) {
                        scalarGrade = 0;
                    }


                    if(!currentGrade.getAcademicGrade().equals("")) {
                        //if the grade corresponds to an academic class
                        if (classIsAcademic(currentGrade)) {
                            creditEarned += scalarGrade * credit;
                            creditAttempted += credit;
                        }
                    }
                }//end else for same record as last kid
            }//end for loop

            //add the last kid to the list
            double gpa = creditEarned/creditAttempted;
            studentGPAs.add(new StudentGPAs(id,first,last,grade,gpa,0,0,ssn));


            for(int i=0;i<studentGPAs.size(); i++) {
                System.out.println(studentGPAs.get(i));
            }

        }
    }

    public boolean stringContainsAGrade(String s) {
        return s.contains("A") || s.contains("B") || s.contains("C") || s.contains("D") || s.contains("F") || s.contains("I");
    }

    //returns true if course contains an academic grade.
    public boolean classIsAcademic(StudentGrades course) {
        return !course.getIsAcademic().contains("N");
    }

    public boolean classIsASport(StudentGrades course) {
        return course.getIsAcademic().contains("N") && course.getIsSport().contains("S");
    }

    public boolean classIsPE(StudentGrades course) {
        return course.getIsAcademic().contains("N") && course.getIsSport().contains("P");
    }

    public boolean classIsTAorZeroCredit (StudentGrades course) {
        return course.getIsAcademic().contains("N") && !course.getIsSport().contains("S") && !course.getIsSport().contains("P");
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
