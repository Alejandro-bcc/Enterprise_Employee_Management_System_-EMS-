package src.service;

public interface IUserService {

    public abstract boolean register(String username, String email, char[] rawPassword);
    public abstract boolean remove(String username);
    public abstract boolean login(String loginIdentifier, char[] rawPassword);
    public abstract void logout();
}
