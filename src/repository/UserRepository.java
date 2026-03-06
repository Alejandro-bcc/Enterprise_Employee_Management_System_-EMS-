package src.repository;

import src.model.User;
import java.util.ArrayList;

public class UserRepository {
    // Atributes
    private static UserRepository instance;
    private ArrayList<User> users;
    
    // Constructor 
    private UserRepository(){
        this.users = new ArrayList<User>();
    }

    // Methods 
    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }
}
