package ua.org.oracle.academy.malahk;



import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 * Created by Admin on 06.07.2015.
 */
public class UserDAOImpl implements UserDAO {

    public static final String CREATE_USER = "insert user set firstName = ?, lastName = ?, age = ?, login = ?, password = ?";
    public static final String GET_ALL = "select * from user";
    public static final String GET_BY_ID = "select * from user where id = ?";
    public static final String UPDATE_USER = "update user set firstName = ?, lastName = ?, age = ?, login = ?, password = ? where id = ?";
    public static final String DELETE_USER = "delete from user where id = ?";

    private static Connection connection;

    public UserDAOImpl () {
        connection = Connector.getConn();

    }

    private List<User> usersList;

    @Override
    public boolean create(User user) {

        boolean result = false;

        try {

            PreparedStatement createUser = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            createUser.setString(1, user.getFirstName());
            createUser.setString(2, user.getLastName());
            createUser.setInt(3, user.getAge());
            createUser.setString(4, user.getLogin());
            createUser.setString(5, user.getPassword());

            result = createUser.execute();

            ResultSet createdUsersRS = createUser.getGeneratedKeys();
            createdUsersRS.next();
            user.setId(createdUsersRS.getLong(1));

            createUser.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public List<User> getAll() {

        try {

            Statement getAll  = connection.createStatement();
            ResultSet allRS = getAll.executeQuery(GET_ALL);
            User user;
            usersList = new ArrayList<>();

            while (allRS.next()) {

                user = new User();

                Long id = allRS.getLong(1);
                String firstName = allRS.getString(2);
                String lastName = allRS.getString(3);
                String login = allRS.getString(4);
                String password = allRS.getString(5);
                Integer age = allRS.getInt(6);

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAge(age);
                user.setLogin(login);
                user.setPassword(password);
                usersList.add(user);
            }

            getAll.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return usersList;
    }

    @Override
    public User getUser(long id) {

        try {

            PreparedStatement getById = connection.prepareStatement(GET_BY_ID);
            getById.setLong(1, id);

            ResultSet allDB = getById.executeQuery();
            User user = null;
            while (allDB.next()) {

                user = new User();

                String firstName = allDB.getString(2);
                String lastName = allDB.getString(3);
                String login = allDB.getString(4);
                String password = allDB.getString(5);
                Integer age = allDB.getInt(6);

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAge(age);
                user.setLogin(login);
                user.setPassword(password);

            }

            getById.close();

            return user;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(User user) {

        boolean result = false;

        try {

            PreparedStatement updateUser = connection.prepareStatement(UPDATE_USER);
            updateUser.setString(1, user.getFirstName());
            updateUser.setString(2, user.getLastName());
            updateUser.setInt(3, user.getAge());
            updateUser.setString(4, user.getLogin());
            updateUser.setString(5, user.getPassword());
            updateUser.setLong(6, user.getId());

            result = updateUser.execute();

            updateUser.close();

            return result;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(User user) {

        boolean result = false;

        try {

            PreparedStatement deleteUser = connection.prepareStatement(DELETE_USER);
            deleteUser.setLong(1, user.getId());

            result = deleteUser.execute();

            deleteUser.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}