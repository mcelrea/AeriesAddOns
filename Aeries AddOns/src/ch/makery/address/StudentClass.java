package ch.makery.address;

/**
 * Created by Eric on 5/4/2015.
 */
public class StudentClass {

    private int period;
    private String semester;
    private String meetingPattern;
    private String section;
    private String courseID;
    private String courseTitle;
    private int teacherID;
    private String teacherName;
    private String roomNumber;

    public StudentClass(String semester, String meetingPattern, String section, String courseID, String courseTitle, int teacherID, String teacherName, String roomNumber, int period) {
        this.semester = semester;
        this.meetingPattern = meetingPattern;
        this.section = section;
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.roomNumber = roomNumber;
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getMeetingPattern() {
        return meetingPattern;
    }

    public void setMeetingPattern(String meetingPattern) {
        this.meetingPattern = meetingPattern;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "period=" + period +
                ", semester='" + semester + '\'' +
                ", meetingPattern='" + meetingPattern + '\'' +
                ", section='" + section + '\'' +
                ", courseID='" + courseID + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", teacherID=" + teacherID +
                ", teacherName='" + teacherName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }
}
