package backend;

import java.util.ArrayList;
import java.util.Map;

public class Course {

    private int totalSeatsRemaining = 60;
    private String className;
    private String discipline;
    private int courseId;
    private boolean pending;
    private PendingType pendingType;
    private String classTime;
    private String[] classTimes;
    private Map<String, String> prerequisites;
    private ArrayList<Student> enrolledStudents = new ArrayList<>();
    private ArrayList<Student> WaitListStudents = new ArrayList<>();


    public Course(Map<String, String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Course(Boolean _pending, PendingType _pendingType, int _courseId){
        ConcessionStudents = new ArrayList<Student>();
        AwaitApprovalStudents = new ArrayList<Student>();
        WaitListStudents = new ArrayList<Student>();

        courseId = _courseId;
        pending = _pending;
        if(_pendingType == null){
            pendingType = PendingType.None;
        }
        
        pendingType = _pendingType;
    }

    public Course(Boolean _pending, PendingType _pendingType, int _courseId, Map<String, String> prereqs){
        ConcessionStudents = new ArrayList<Student>();
        AwaitApprovalStudents = new ArrayList<Student>();
        WaitListStudents = new ArrayList<Student>();

        courseId = _courseId;
        pending = _pending;
        if(_pendingType == null){
            pendingType = PendingType.None;
        }

        pendingType = _pendingType;
        this.discipline = prereqs.get("discipline");
        this.prerequisites = prereqs;
    }

    public Course(String classTimes){
        this.classTime = classTimes;
    }

    public Course(){
    }

    public Course(int numberOfSeatsInClss) {
        this.totalSeatsRemaining = numberOfSeatsInClss;
    }

    public Course(String[] classTimes) {
        this.classTimes = classTimes;
    }

    public boolean isPending(){
        return pending;
    }
    public int getCourseId() {return courseId;}
    public PendingType getPendingType() {return pendingType;}

    private ArrayList<Student> ConcessionStudents;
    public void AddConcessionStudent(Student student){
        if(!ConcessionStudents.contains(student)) {
            ConcessionStudents.add(student);
        }
    }
    public ArrayList<Student> GetConcessionStudents(){
        if(ConcessionStudents.isEmpty()){ ConcessionStudents.add(new Student());}
        return ConcessionStudents;
    }
    public void DeleteConcessionStudent(Student student){
        if(ConcessionStudents.contains(student)) {
            ConcessionStudents.remove(student);
        }
    }

    public void AddWaitListStudent(Student student){
        if(!WaitListStudents.contains(student)) {
            WaitListStudents.add(student);
        }
    }
    public ArrayList<Student> GetWaitListStudents(){
        return WaitListStudents;
    }
    public void DeleteWaitListStudent(Student student){
        if(WaitListStudents.contains(student)){
            WaitListStudents.remove(student);
        }
    }

    private ArrayList<Student> AwaitApprovalStudents;
    public void AddAwaitApprovalStudent(Student student){
        if(!AwaitApprovalStudents.contains(student)) {
            AwaitApprovalStudents.add(student);
        }
    }
    public ArrayList<Student> GetAwaitApprovalStudents(){
        return AwaitApprovalStudents;
    }
    public void DeleteAwaitApprovalStudent(Student student){
        if(AwaitApprovalStudents.contains(student)) {
            AwaitApprovalStudents.remove(student);
        }
    }

    public Course(String className, String discipline) {
        this.className = className;
        this.discipline = discipline;
    }

    public int getRemainingSeats() {
        return this.totalSeatsRemaining;
    }

    public void decreaseRemainingNumberOfSeats(){
        this.totalSeatsRemaining = this.totalSeatsRemaining - 1;
    }

    public void increaseRemainingNumberOfSeats(){
        this.totalSeatsRemaining = this.totalSeatsRemaining + 1;
    }

    public String getDiscipline() {
        return this.discipline;
    }

    public String classStatus() {
        if (this.totalSeatsRemaining > 0){
            return "Open";
        }
        return "Full";
    }

    public String getClassTime() {
        return this.classTime;
    }

    public String[] getClassTimes() {
        return this.classTimes;
    }

    public Map<String, String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Map<String, String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setPendingType(PendingType pendingType) {
        this.pendingType = pendingType;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public void enrollStudent(Student student) {
        this.enrolledStudents.add(student);
        this.decreaseRemainingNumberOfSeats();
    }

    public void revokeStudent(Student student) {
        if (this.enrolledStudents.contains(student)) {
            this.enrolledStudents.remove(student);
            this.increaseRemainingNumberOfSeats();
        }
    }

    public ArrayList<Student> getEnrolledStudents() {
        return this.enrolledStudents;
    }
}
