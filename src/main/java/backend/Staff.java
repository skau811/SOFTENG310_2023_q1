package backend;

public class Staff {
    public void viewConcession(Concession concession) {
        concession.setViewed(true);
        concession.addViewer(this);
    }
}
