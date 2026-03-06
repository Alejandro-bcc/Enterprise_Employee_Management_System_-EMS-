package src.dao;

import src.model.User;
import src.repository.UserRepository;
import java.util.ArrayList;

public class UserDAO {
    // Attributes
    private UserRepository repository;

    // Constructor
    public UserDAO() {
        this.repository = UserRepository.getInstance();
    }

    // Methods
    public void add(User u) {
        this.repository.getUsers().add(u);
    }

    public boolean remove(String username) {
        return this.repository.getUsers().removeIf(u -> u.getUsername() == username);
    }

    public User findByUsername(String username) {
        for (User u : this.repository.getUsers()) {
            if (u.getUsername().equals(username))
                return u;
        }
        return null;
    }

    public User findByEmail(String email) {
        for (User u : this.repository.getUsers()) {
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