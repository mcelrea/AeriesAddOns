package ch.makery.address;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Eric on 6/23/2016.
 */
public class StudentClasses implements Comparable<StudentClasses>{
    private String firstName;
    private String lastName;
    private int studentGrade;
    private int iD;;
    String classes[];


    private StudentClasses() {
        classes = new String[9];
    }

    public StudentClasses(String firstName, String lastName, int studentGrade, int iD) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.studentGrade = studentGrade;
        this.iD = iD;
        classes = new String[10];
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public int getStudentGrade(){
        return studentGrade;
    }

    public void setStudentGrade(int studentGrade){
        this.studentGrade = studentGrade;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    @Override
    public int compareTo(StudentClasses o) {
        return iD - o.iD;
    }

    public String getClass(int period) {
        if(classes[period] == null) {
            return "";
        }
        else {
            return classes[period];
        }
    }

    public void appendClass(String courseNum, String courseName, int period) {
        if(classes[period] == null) {
            classes[period] = courseNum + " " + courseName;
        }
        else {
            classes[period] += "; " + courseNum + " " + courseName;
        }
    }

    @Override
    public String toString() {
        return "StudentClasses{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentGrade=" + studentGrade +
                ", iD=" + iD +
                ", classes=" + Arrays.toString(classes) +
                '}';
    }
}
