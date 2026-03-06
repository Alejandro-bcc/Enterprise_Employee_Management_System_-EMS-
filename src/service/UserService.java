package src.service;

import src.dao.UserDAO;
import src.model.User;
import src.session.Session;
import java.util.regex.Pattern;

public class UserService {
    // Attributes
    private UserDAO dao;

    // Constructor
    public UserService() {
        this.dao = new UserDAO();
    }

    // Methods
    public boolean register(String username, String email, String passwordHash) {
        String userRegex = "^[a-zA-Z0-9_-]{3,16}$";
        String emailRegex = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";

        if (!Pattern.matches(userRegex, username) ||
                !Pattern.matches(emailRegex, email) ||
                this.dao.existsByUsername(username) ||
                this.dao.existsByEmail(email)) {
            return false;
        }
        User u = new User(username, email, passwordHash);
        this.dao.add(u);
        return true;
    }

    public boolean remove(String username) {
        if (!this.dao.existsByUsername(username))
            return false;
        return this.dao.remove(username);
    }

    public boolean login(String username, String passwordHash) {
        User u = this.dao.findByUsername(username);
        if (u != null && u.getPasswordHash().equals(passwordHash)) {
            Session.getInstance().login(u);
            return true;
        }
        return false;
    }

    public void logout() {
        Session.getInstance().logout();
    }
}
