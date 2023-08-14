package backend;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Concession {

    private String status = "neutral";

    public Concession(ConcessionReason _reason, File _concessionFile, String _comment, Student _student, Course _course){
        reasonale = _reason;
        file = _concessionFile;
        comment = _comment;
        sender = _student;
        course = _course;
        viewers = new ArrayList<>();
    }

    public Concession() {
        this.submitted = true;
        this.viewed = false;
        viewers = new ArrayList<>();
    }

    public Course getCourse() {return course;}

    private ConcessionReason reasonale;
    private File file;
    private String comment;
    private Student sender;
    private Course course;
    private boolean submitted;
    private boolean viewed;
    private List<Staff> viewers;
    private ZonedDateTime readDate;

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public Student getSender() {
        return sender;
    }

    public void setSender(Student sender) {
        this.sender = sender;
    }

    public void addViewer(Staff staff) {
        this.viewers.add(staff);
    }

    public List<Staff> getViewers() {
        return this.viewers;
    }

    public ZonedDateTime getReadDate() {
        return readDate;
    }

    public void setReadDate(ZonedDateTime readDate) {
        this.readDate = readDate;
    }

    public void setStatus(String status){
        if (status.equals("declined")) {
            Student st = this.getSender();
            Course c = this.getCourse();

            c.revokeStudent(st);

            if (c.GetWaitListStudents().size() > 0)
                c.enrollStudent(c.GetWaitListStudents().get(0));
        }
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
