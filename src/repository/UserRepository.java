package src.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import src.model.User;

public class UserRepository {
    private static final Path DATA_FILE = Paths.get("data", "users.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static UserRepository instance;
    private final HashMap<String, User> users;

    private UserRepository() {
        this.users = new HashMap<>();
        this.loadFromDisk();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public HashMap<String, User> getUsers() {
        return this.users;
    }

    public synchronized void save() {
        try {
            Files.createDirectories(DATA_FILE.getParent());

            UserStore store = new UserStore();
            for (User u : this.users.values()) {
                UserRecord r = new UserRecord();
                r.username = u.getUsername();
                r.email = u.getEmail();
                r.passwordHash = u.getPasswordHash();
                store.users.add(r);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(DATA_FILE, StandardCharsets.UTF_8)) {
                GSON.toJson(store, writer);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Could not save users.json", ex);
        }
    }

    private synchronized void loadFromDisk() {
        if (!Files.exists(DATA_FILE)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(DATA_FILE, StandardCharsets.UTF_8)) {
            UserStore store = GSON.fromJson(reader, UserStore.class);
            if (store == null || store.users == null) {
                return;
            }

            this.users.clear();
            for (UserRecord r : store.users) {
                User u = new User(r.username, r.email, r.passwordHash);
                this.users.put(u.getUsername(), u);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not load users.json", ex);
        }
    }

    private static class UserStore {
        List<UserRecord> users = new ArrayList<>();
    }

    private static class UserRecord {
        String username;
        String email;
        String passwordHash;
    }
}
