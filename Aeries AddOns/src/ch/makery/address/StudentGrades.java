package ch.makery.address;

/**
 * Created by Eric on 6/2/2015.
 */
public class StudentGrades {

    private int id;
    private String first;
    private String last;
    private int grade;
    private String courseName;
    private String isSport;
    private String isAcademic;
    private String academicGrade;
    private double credit;
    private String ssn;

    public StudentGrades(int id, String first, String last, int grade, String courseName, String isSport, String isAcademic, String academicGrade, double credit) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.grade = grade;
        this.courseName = courseName;
        this.isSport = isSport;
        this.isAcademic = isAcademic;
        this.academicGrade = academicGrade;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIsSport() {
        return isSport;
    }

    public void setIsSport(String isSport) {
        this.isSport = isSport;
    }

    public String getIsAcademic() {
        return isAcademic;
    }

    public void setIsAcademic(String isAcademic) {
        this.isAcademic = isAcademic;
    }

    public String getAcademicGrade() {
        return academicGrade;
    }

    public void setAcademicGrade(String academicGrade) {
        this.academicGrade = academicGrade;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "StudentGrades{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", grade=" + grade +
                ", courseName='" + courseName + '\'' +
                ", isSport='" + isSport + '\'' +
                ", isAcademic='" + isAcademic + '\'' +
                ", academicGrade='" + academicGrade + '\'' +
                ", credit=" + credit +
                '}';
    }
}
