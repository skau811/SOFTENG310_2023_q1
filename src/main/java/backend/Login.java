package backend;

import java.util.ArrayList;

public class Login {
    private User[] users;
    private ArrayList<User> authenticatedUsers;

    public Login() {

    }

    public Login(User[] users) {
        this.users = users;
        authenticatedUsers = new ArrayList<User>();
    }

    public User authenticate(String username, String password) {
        if (this.users == null) return null;

        for (User user :
                this.users) {
            if (username.equals(user.getUsername())
                    && password.equals(user.getPassword())){
                authenticatedUsers.add(user);
                return user;
        }
        }

        return null;
    }

    public String logOut(User user){
        if(authenticatedUsers.contains(user)) {
            authenticatedUsers.remove(user);
            return "Success";
        } else {
            return "Error";
        }
    }
}
