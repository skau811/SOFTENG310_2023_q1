package backend;

import java.util.List;

public class User {
    private String username;
    private String password;
    private UserAccessLevel accessLevel;
    private List<models.Course> classList;

    public User(String username, String password, UserAccessLevel accessLevel, List<models.Course> classList) {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.classList = classList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(UserAccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public List<models.Course> getClassList(){
        return this.classList;
    }

    public void setClassList(List<models.Course> classList){
        this.classList = classList;
    }

    public void applyForClass(models.Course course){
        this.classList.add(course);
    }
}

