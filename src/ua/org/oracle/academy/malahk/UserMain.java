package ua.org.oracle.academy.malahk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Admin on 07.07.2015.
 */
public class UserMain {

    public static final String URL = "jdbc:mysql://localhost:3306/test01";
    public static final String USER = "root";
    public static final String PASSWORD = "toor";

    public static void main(String[] args) {

        UserDAOImpl myDAO = new UserDAOImpl();
        User user = new User();
        user.setFirstName("I AM");
        user.setLastName("GROOT");
        user.setAge(500);
        user.setLogin("ALIEN");
        user.setPassword("TREE");

        User user1 = new User();
        user1.setFirstName("TONY");
        user1.setLastName("STARK");
        user1.setAge(35);
        user1.setLogin("IRON");
        user1.setPassword("MAN");

        User user2 = new User();
        user2.setFirstName("HUKL");
        user2.setLastName("HULK");
        user2.setAge(35);
        user2.setLogin("GREEN");
        user2.setPassword("BEAST");

        System.out.println(myDAO.create(user));
        System.out.println(myDAO.create(user1));
        System.out.println(myDAO.create(user2));

        System.out.println(myDAO.getAll());

        System.out.println(myDAO.getUser(1));
        System.out.println(myDAO.getUser(3));

        System.out.println(myDAO.delete(user));
        System.out.println(myDAO.delete(user2));



//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
//
//            connection.close();
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }

    }

}
