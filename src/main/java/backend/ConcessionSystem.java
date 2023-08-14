package backend;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ConcessionSystem {
    public List<Concession> concessions;
    private Clock clock;

    public ConcessionSystem(Clock clock) {
        this.clock = clock;
        this.concessions = new ArrayList<>();
    }

    public String SubmitConcession(Concession _concession) {
        _concession.setReadDate(ZonedDateTime.now());
        _concession.setSubmitted(true);
        this.concessions.add(_concession);
        _concession.setStatus("submitted");
        return _concession.getStatus();
    }

    public String InformStudentOfSubmission(Student student) {
        Concession concession = getConcession(student);
        if (concession.getStatus() == "approved"){
            student.applyForClass(concession.getCourse());
            return "concession approved";
        }else if (concession.getStatus() == "declined"){
            return "concession declined";
        }else {
            LocalDate concessionReadTime = concession.getReadDate().toLocalDate();
            LocalDate currentTime = clock.instant().atZone(ZoneId.systemDefault()).toLocalDate();
            long difference = currentTime.until(concessionReadTime, ChronoUnit.DAYS);

            if (difference >= 14) {
                return "student notified";
            }
            return "error";
        }
    }

    public String InformUniversityStaff(Concession concession){
        return "submitted";
    }

    public Concession getConcession(Student student) {
        if (student == null) return null;

        for (Concession concession :
                concessions) {
            Student sender = concession.getSender();

            if (student == sender) return concession;
        }

        return null;
    }
}
