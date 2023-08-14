package backend;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private Map<String, String> studentConditions;
    private ArrayList<Course> PendingClasses = new ArrayList<Course>();
    private ArrayList<Course> EnrolledClasses = new ArrayList<Course>();
    private ArrayList<Course> RevocableClasses = new ArrayList<Course>();
    private ArrayList<Concession> ActiveConcessions = new ArrayList<Concession>();
    private ConcessionSystem concessionSystem;

    public Student(){
        studentConditions = new HashMap<>();
    }

    public Student(ConcessionSystem concessionSystem ){
        studentConditions = new HashMap<>();
        this.concessionSystem = concessionSystem;
    }

    public Student(List<Course> courses){
        this.AddClasses((ArrayList<Course>) courses, null);
    }

    public boolean isConcessionRequired(Course course) {
        Map<String, String> prereqs = course.getPrerequisites();

        if (prereqs.isEmpty()) return false;

        for (String key : prereqs.keySet()) {
            String val = prereqs.get(key);

            Map <String, String> studentConds = this.studentConditions;
            for (String sCondKey : studentConds.keySet()) {
                String sCondVal = studentConds.get(sCondKey);

                if (!val.equals(sCondVal)) return true;
            }
        }

        return false;
    }

    public void AddClasses(ArrayList<Course> _enrolledClasses, ArrayList<Concession> activeConcessions){
        for (Course course: _enrolledClasses) {
            if (course.isPending()) {
                switch (course.getPendingType()){
                    case ConcessionActive:
                        if(!activeConcessions.isEmpty()) {
                            for (Concession concession: activeConcessions) {
                                ActiveConcessions.add(concession);
                            }
                        }
                        course.AddConcessionStudent(this);
                        RevocableClasses.add(course);
                    case WaitList:
                        course.AddWaitListStudent(this);
                        RevocableClasses.add(course);
                        break;
                    case AwaitApproval:
                        course.AddAwaitApprovalStudent(this);
                        RevocableClasses.add(course);
                        break;
                    case None:
                        EnrolledClasses.add(course);
                        break;
                }
                PendingClasses.add(course);
            }
            else if(!course.isPending()) {
                EnrolledClasses.add(course);
            }
        }
    }



    public ArrayList<Course> GetEnrolledClasses(){ return EnrolledClasses; }
    public ArrayList<Course> GetPendingClasses() { return PendingClasses;}
    public ArrayList<Course> GetRevocableClasses() { return RevocableClasses;}
    public ArrayList<Concession> GetActiveConcessions(){return ActiveConcessions;}

    public Concession FindConcession(Course concessionCourse){
        for (Concession concession: ActiveConcessions) {
            if(concession.getCourse() == concessionCourse){
                return concession;
            }
        }
        return new Concession();
    }
    public Course FindCourse(int courseId, ClassLists list) {
        switch (list) {
            case PendingClassses:
                for (Course course: PendingClasses) {
                    if(course.getCourseId() == courseId){
                        return course;
                    }
                }
                break;
            case EnrolledClassses:
                for (Course course: EnrolledClasses) {
                    if(course.getCourseId() == courseId){
                        return course;
                    }
                }
                break;
            case RevocableClasses:
                for (Course course: RevocableClasses) {
                    if(course.getCourseId() == courseId){
                        return course;
                    }
                }
                break;
        }
        return null;
    }

        
    public boolean RevokeClassApplication(int courseId) throws Exception {
        Course revocableCourse = FindCourse(courseId, ClassLists.RevocableClasses);
        Course enrolledCourse = FindCourse(courseId, ClassLists.EnrolledClassses);
        if (revocableCourse == null && enrolledCourse == null) throw new Exception("backend.Student is not enrolled in class");

        if (revocableCourse != null) {
            if (ActiveConcessions.contains(revocableCourse)){
                ActiveConcessions.remove(revocableCourse);
                revocableCourse.DeleteConcessionStudent(this);
            }

            RevocableClasses.remove(revocableCourse);
            PendingClasses.remove(revocableCourse);

            revocableCourse.DeleteAwaitApprovalStudent(this);
            revocableCourse.DeleteWaitListStudent(this);
            return true;
        }

        if (enrolledCourse != null) {
            enrolledCourse.setPending(false);
            enrolledCourse.setPendingType(PendingType.None);

            if (enrolledCourse.GetWaitListStudents().size() != 0) {
                Student waitlistedStudent = enrolledCourse.GetWaitListStudents().get(0);

                waitlistedStudent.applyForClass(enrolledCourse);
                enrolledCourse.DeleteWaitListStudent(waitlistedStudent);
            }

            EnrolledClasses.remove(enrolledCourse);
            enrolledCourse.increaseRemainingNumberOfSeats();
            return true;
        }

        return false;
    }

    public String SubmitConcession(Concession concession){
        if(concession != null) {
            if(ActiveConcessions.contains(concession)){
                return "A Concession have already been made";
            }
            if(EnrolledClasses.contains(concession.getCourse())){
                return "Can't apply for concession, for courses already enrolled in";
            } else {
                ActiveConcessions.add(concession);
                RevocableClasses.add(concession.getCourse());
                return concessionSystem.SubmitConcession(concession);
            }
        }
        return "error";
    }

    public boolean sendEnrollmentOutcome(Course course) {
        return this.EnrolledClasses.contains(course);
    }

    public void applyForClass(Course course) {
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course);

        this.AddClasses(courseList, null);

        if (!course.isPending()) {
            course.decreaseRemainingNumberOfSeats();
        }
    }

    public void applyForSelectionOfClasses(List<Course> courses) {
        for (Course course : courses){
            this.applyForClass(course);
        }
    }

    public List<Course> filterAvailableClasses(List<Course> availableClasses, String discipline) {
        List<Course> filteredList = new ArrayList<>();
        for (Course course : availableClasses) {
            if (course.getDiscipline() == discipline) {
                filteredList.add(course);
            }
        }
        return filteredList;
    }

    public void setConditions(String key, String value) {
        studentConditions.put(key, value);
    }
}
