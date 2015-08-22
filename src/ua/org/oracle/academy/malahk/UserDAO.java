package ua.org.oracle.academy.malahk;

import java.util.List;

/**
 * Created by Admin on 06.07.2015.
 */
public interface UserDAO {


    public boolean create(User user);
    public List<User> getAll();
    public User getUser(long id);
    public boolean update(User user);
    public boolean delete(User user);

}
