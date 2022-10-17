package ch.makery.address;

/**
 * Created by Eric on 6/2/2015.
 */
public class StudentGPAs {

    private int id;
    private String first;
    private String last;
    private int grade;
    private double gpaWithPE;
    private double gpaWithSports;
    private int numOfFs;
    private String ssn;

    public StudentGPAs(int id, String first, String last, int grade, double gpaWithPE, double gpaWithSports, int numOfFs) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.gpaWithPE = gpaWithPE;
        this.gpaWithSports = gpaWithSports;
        this.numOfFs = numOfFs;
        this.grade = grade;
    }

    public StudentGPAs(int id, String first, String last, int grade, double gpaWithPE, double gpaWithSports, int numOfFs, String ssn) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.gpaWithPE = gpaWithPE;
        this.gpaWithSports = gpaWithSports;
        this.numOfFs = numOfFs;
        this.grade = grade;
        this.ssn = ssn;
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

    public double getGpaWithPE() {
        return gpaWithPE;
    }

    public void setGpaWithPE(double gpaWithPE) {
        this.gpaWithPE = gpaWithPE;
    }

    public double getGpaWithSports() {
        return gpaWithSports;
    }

    public void setGpaWithSports(double gpaWithSports) {
        this.gpaWithSports = gpaWithSports;
    }

    public int getNumOfFs() {
        return numOfFs;
    }

    public void setNumOfFs(int numOfFs) {
        this.numOfFs = numOfFs;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "StudentGPAs{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", gpaWithPE=" + gpaWithPE +
                ", gpaWithSports=" + gpaWithSports +
                ", numOfFs=" + numOfFs +
                ", ssn=" + ssn +
                '}';
    }
}
