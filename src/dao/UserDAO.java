package src.dao;

import src.model.User;
import src.repository.UserRepository;

public class UserDAO {
    // Attributes
    private UserRepository repository;

    // Constructor
    public UserDAO() {
        this.repository = UserRepository.getInstance();
    }

    // Methods
    public void add(User u) {
        this.repository.getUsers().put(u.getUsername(), u);
        this.repository.save();
    }

    public boolean remove(String username) {
        if (!this.repository.getUsers().containsKey(username))
            return false;
        this.repository.getUsers().remove(username);
        this.repository.save();
        return true;
    }

    public User findByUsername(String username) {
        return this.repository.getUsers().get(username);
    }

    public User findByEmail(String email) {
        for (User u : this.repository.getUsers().values()) {
            if (u.getEmail().equals(email))
                return u;
        }
        return null;
    }

    public boolean existsByUsername(String username) {
        return this.findByUsername(username) != null;
    }

    public boolean existsByEmail(String email) {
        return this.findByEmail(email) != null;
    }
}