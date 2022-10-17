package ch.makery.address.view;

import ch.makery.address.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

public class AthleticEligibilityController {


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
    ContextMenu chooseFileContextMenu;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AthleticEligibilityController() {
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


        chooseFileContextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("CSV FORMAT: id,last,first,grade,courseName,PE/Sport,Honors/AP,NonAcademic,mark(grade),credit");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        MenuItem item2 = new MenuItem("QUERY: LIST STU GRD CRS STU.ID STU.LN STU.FN STU.GR CRS.CO CRS.UC6 CRS.CL CRS.NA GRD.MX GRD.CR");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        MenuItem item3 = new MenuItem("NOTE 1: GRD.MX, X = grade you want from the GRD table");
        item3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        MenuItem item4 = new MenuItem("NOTE 2: CRS.UC6 needs to be set to P or S correctly for PE/Sports.  Verify this before.");
        item4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        MenuItem item5 = new MenuItem("NOTE 3: CRS.CL needs to be set to 30/34 for AP/Honors courses.  Verify this before.");
        item5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"Test");
            }
        });
        chooseFileContextMenu.getItems().addAll(item1,item2,item3,item4,item5);
        chooseFile.setContextMenu(chooseFileContextMenu);
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

                    for (int i = 0; i < allStrings.size(); i++) {

                        String s = allStrings.get(i);

                        int id = Integer.parseInt(MainApp.getFromString(s, 0));
                        //System.out.print("" + id + " ");

                        String lastName = MainApp.getFromString(s, 1);
                        //System.out.print(firstName + " ");

                        String firstName = MainApp.getFromString(s, 2);
                        //System.out.print(lastName + " ");

                        int grade = Integer.parseInt(MainApp.getFromString(s, 3));
                        //System.out.print("" + grade + " ");

                        String className = MainApp.getFromString(s, 4);
                        //System.out.print(birthDate + " ");

                        String isSport = MainApp.getFromString(s, 5);
                        //System.out.print(sex + " ");

                        String isAcademic = MainApp.getFromString(s, 7);
                        //System.out.print(term + " ");

                        String academicGrade = MainApp.getFromString(s, 8);
                        //System.out.print(meetingPattern + " ");

                        double credit = Double.parseDouble(MainApp.getFromString(s, 9));

                        StudentGrades studentGr = new StudentGrades(id, firstName,lastName,grade,className,isSport,isAcademic,academicGrade,credit);
                        allGrades.add(studentGr);
                        System.out.println(i + ". " + studentGr);
                    }

                    for(int i=0; i < allGrades.size(); i++) {
                        System.out.println(allGrades.get(i));
                    }

                    //JOptionPane.showMessageDialog(null, "Student Grades Imported");
                    //System.out.println(MainApp.studentList.toString());
					/*for(Student s: MainApp.studentList) {
						System.out.println(s);
					}*/
                }

            }
        });

        produceCSV.setOnAction(new EventHandler<ActionEvent>() {


            public void handle(ActionEvent event) {

                //int reply = JOptionPane.showConfirmDialog(null, "This process will take several minutes.  Proceed?", "Proceed", JOptionPane.YES_NO_OPTION);
                if (true) {//reply == JOptionPane.YES_OPTION) {
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
                }
            }
        });

        calcGPAs.setOnAction(new EventHandler<ActionEvent>() {


            public void handle(ActionEvent event) {

                //int reply = JOptionPane.showConfirmDialog(null, "This process will take several minutes.  Proceed?", "Proceed", JOptionPane.YES_NO_OPTION);
                if (true){//reply == JOptionPane.YES_OPTION) {
                    long startTime = System.currentTimeMillis();

                    combineGradesAndCalcGPAs();

                    long endTime = System.currentTimeMillis();
                    System.out.println("Total time: " + ((endTime - startTime) / 1000) + " seconds");
                    //JOptionPane.showMessageDialog(null, "Operation Successful!  CSV file created.\nTotal Time: " + ((endTime - startTime) / 1000) + " seconds");
                }
            }
        });
    }

    private void combineGradesAndCalcGPAs() {
        int id;
        String first;
        String last;
        int grade;
        double creditsEarnedWithPE=0;
        double creditsPossibleWithPE=0;
        double creditsEarnedWithSports=0;
        double creditsPossibleWithSports=0;
        double credit=0;
        int totalFs=0;
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
                if(credit > 0)
                    totalFs++;
            }
            else if(academicGrade.contains("I")) {
                scalarGrade = 0;
                totalFs++;
            }


            if(!currentGrade.getAcademicGrade().equals("")) {

                //if the grade corresponds to an academic class
                if (classIsAcademic(currentGrade)) {
                    creditsEarnedWithPE += scalarGrade * credit;
                    creditsPossibleWithPE += credit;
                    creditsEarnedWithSports += scalarGrade * credit;
                    creditsPossibleWithSports += credit;
                }
                //else if the grade is from a TA class or a 0 credit (no 7th) class
                else if (classIsTAorZeroCredit(currentGrade)) {
                    creditsEarnedWithPE += scalarGrade * credit;
                    creditsPossibleWithPE += credit;
                    creditsEarnedWithSports += scalarGrade * credit;
                    creditsPossibleWithSports += credit;
                }
                //else if the grade is from a PE class
                else if (classIsPE(currentGrade)) {
                    creditsEarnedWithPE += scalarGrade * credit;
                    creditsPossibleWithPE += credit;
                }
                //else if the grade is from a Sport
                else if (classIsASport(currentGrade)) {
                    creditsEarnedWithSports += scalarGrade * credit;
                    creditsPossibleWithSports += credit;
                }
            }


            //for loop through the rest of the list
            for(int i=1; i < allGrades.size(); i++) {
                currentGrade = allGrades.get(i);

                //if this is a NEW student record
                if(currentGrade.getId() != id) {
                    double gpaWithPE = creditsEarnedWithPE/creditsPossibleWithPE;
                    double gpaWithSports = creditsEarnedWithSports/creditsPossibleWithSports;
                    System.out.println(last + "," + first);
                    System.out.println("----------------------");
                    System.out.println("creditsEarnedWithPE=        " + creditsEarnedWithPE);
                    System.out.println("creditsPossibleWithPE=      " + creditsPossibleWithPE);
                    System.out.println("creditsEarnedWithSports=    " + creditsEarnedWithSports);
                    System.out.println("creditsPossibleWithSports=  " + creditsPossibleWithSports);
                    System.out.println("gpaWithPE=  " + gpaWithPE);
                    System.out.println("gpaWithSports=  " + gpaWithSports);
                    studentGPAs.add(new StudentGPAs(id, first, last, grade, gpaWithPE, gpaWithSports, totalFs));


                    id = currentGrade.getId();
                    first = currentGrade.getFirst();
                    last = currentGrade.getLast();
                    grade = currentGrade.getGrade();
                    creditsEarnedWithPE=0;
                    creditsPossibleWithPE=0;
                    creditsEarnedWithSports=0;
                    creditsPossibleWithSports=0;
                    credit=currentGrade.getCredit();
                    totalFs=0;
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
                        if(credit > 0)
                            totalFs++;
                    }
                    else if(academicGrade.contains("I")) {
                        scalarGrade = 0;
                        totalFs++;
                    }

                    if(!currentGrade.getAcademicGrade().equals("")) {
                        //if the grade corresponds to an academic class
                        if (classIsAcademic(currentGrade)) {
                            System.out.println("Adding credit values for academic class (first record) " + currentGrade.getCourseName());
                            creditsEarnedWithPE += scalarGrade * credit;
                            creditsPossibleWithPE += credit;
                            creditsEarnedWithSports += scalarGrade * credit;
                            creditsPossibleWithSports += credit;
                        }
                        //else if the grade is from a TA class or a 0 credit (no 7th) class
                        //THIS IS A PROBLEM....COUNTING FIRST RECORD??? DEFINITELY COUNTING LEARN ACRS DISC, COUNTING TA???
                        /*
                        else if (classIsTAorZeroCredit(currentGrade)) {
                            System.out.println("Adding credit values for TA/Zero credit (first record) " + currentGrade.getCourseName());
                            creditsEarnedWithPE += scalarGrade * credit;
                            creditsPossibleWithPE += credit;
                            creditsEarnedWithSports += scalarGrade * credit;
                            creditsPossibleWithSports += credit;
                        }
                        */
                        //else if the grade is from a PE class
                        else if (classIsPE(currentGrade)) {
                            System.out.println("Adding credit values for PE class (first record) " + currentGrade.getCourseName());
                            creditsEarnedWithPE += scalarGrade * credit;
                            creditsPossibleWithPE += credit;
                        }
                        //else if the grade is from a Sport
                        else if (classIsASport(currentGrade)) {
                            System.out.println("Adding credit values for sport (first record) " + currentGrade.getCourseName());
                            creditsEarnedWithSports += scalarGrade * credit;
                            creditsPossibleWithSports += credit;
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
                        if(credit > 0)
                            totalFs++;
                    }
                    else if(academicGrade.contains("I")) {
                        scalarGrade = 0;
                        totalFs++;
                    }


                    if(!currentGrade.getAcademicGrade().equals("")) {
                        //if the grade corresponds to an academic class
                        if (classIsAcademic(currentGrade)) {
                            System.out.println("Adding credit values for academic class " + currentGrade.getCourseName());
                            creditsEarnedWithPE += scalarGrade * credit;
                            creditsPossibleWithPE += credit;
                            creditsEarnedWithSports += scalarGrade * credit;
                            creditsPossibleWithSports += credit;
                        }
                        //else if the grade is from a TA class or a 0 credit (no 7th) class
                        /*
                        else if (classIsTAorZeroCredit(currentGrade)) {
                            System.out.println("Adding credit values for TA/0 Credit class " + currentGrade.getCourseName());
                            creditsEarnedWithPE += scalarGrade * credit;
                            creditsPossibleWithPE += credit;
                            creditsEarnedWithSports += scalarGrade * credit;
                            creditsPossibleWithSports += credit;
                        }
                        */

                        //else if the grade is from a PE class
                        else if (classIsPE(currentGrade)) {
                            System.out.println("Adding credit values for PE class " + currentGrade.getCourseName());
                            creditsEarnedWithPE += scalarGrade * credit;
                            creditsPossibleWithPE += credit;
                        }
                        //else if the grade is from a Sport
                        else if (classIsASport(currentGrade)) {
                            System.out.println("Adding credit values for sport class " + currentGrade.getCourseName());
                            creditsEarnedWithSports += scalarGrade * credit;
                            creditsPossibleWithSports += credit;
                        }
                    }
                }//end else for same record as last kid
            }//end for loop

            //add the last kid to the list
            double gpaWithPE = creditsEarnedWithPE/creditsPossibleWithPE;
            double gpaWithSports = creditsEarnedWithSports/creditsPossibleWithSports;
            studentGPAs.add(new StudentGPAs(id,first,last,grade,gpaWithPE,gpaWithSports,totalFs));


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

        /*
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
        */
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

        String output = "";
        File myFile = new File(path + ".csv");

        if (!myFile.exists()) {
            myFile.createNewFile();
        }

        Formatter writer = new Formatter(myFile);

        output += "id,last,first,grade,GPA with PE, GPA with Sports, Total F's";
        output += "\n";



        for(int i=0; i < studentGPAs.size(); i++) {

            StudentGPAs current = studentGPAs.get(i);

            output += current.getId();
            output += ",";

            output += current.getLast();
            output += ",";

            output += current.getFirst();
            output += ",";

            output += current.getGrade();
            output += ",";

            output += current.getGpaWithPE();
            output += ",";

            output += current.getGpaWithSports();
            output += ",";

            output += current.getNumOfFs();
            output += ",";

            output += "\n";
        }

        writer.format(output);
        writer.close();
        long endTime = System.currentTimeMillis();
        System.out.println("FINISHED PRODUCING HTML!!!");

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
