package ch.makery.address;

public class Student implements Comparable<Student> {

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName
				+ ", primaryEmail=" + primaryEmail + ", studentGrade="
				+ studentGrade + ", iD=" + iD + ", primaryPhone="
				+ primaryPhone + ", hours=" + hours + "]";
	}

	private String firstName;
	private String lastName;
	private String primaryEmail;
	private int studentGrade;
	private int iD;
	private String primaryPhone;
	private double hours;
	private String birthDate;
	private String sex;


	private Student() {

	}

	public Student(String firstName, String lastName, String primaryEmail, int studentGrade, int iD, String primaryPhone, double hours) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryEmail = primaryEmail;
		this.studentGrade = studentGrade;
		this.iD = iD;
		this.primaryPhone = primaryPhone;
		this.hours = hours;
	}

	public Student(String firstName, String lastName, int studentGrade, int iD, String birthDate, String sex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentGrade = studentGrade;
		this.iD = iD;
		this.birthDate = birthDate;
		this.sex = sex;
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

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
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

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public int compareTo(Student o) {
		return iD - o.iD;
	}
}