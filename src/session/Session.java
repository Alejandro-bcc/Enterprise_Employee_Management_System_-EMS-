package src.session;

import src.model.User;

public class Session {
    // Attributes
    private static Session instance;
    private User currentUser;

    // Constructor
    private Session() {
    }

    // Methods
    public static Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }

    // Getter
    public User getCurrentUser() {
        return this.currentUser;
    }

    // Setter
    private void setCurrentUser(User u) {
        this.currentUser = u;
    }

    public void login(User u) {
        this.setCurrentUser(u);
    }

    public void logout() {
        this.setCurrentUser(null);
    }

    public boolean isLoggedIn() {
        return this.getCurrentUser() != null;
    }
}
