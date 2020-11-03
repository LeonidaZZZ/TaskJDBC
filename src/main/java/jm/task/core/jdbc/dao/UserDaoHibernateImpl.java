package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    private static Statement statement;
    private static final String TABLE_NAME = "USER";
    private static Transaction transaction;

    public UserDaoHibernateImpl() {
        statement = Util.connect();
    }


    @Override
    public void createUsersTable() {
        try  {
            String SQL = "CREATE TABLE " + TABLE_NAME +
                    "(id BIGINT AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " lastName VARCHAR (50), " +
                    " age TINYINT, " +
                    " PRIMARY KEY (id))";
            statement.executeUpdate(SQL);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            util.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            String SQL = "DROP TABLE " + TABLE_NAME;
            statement.executeUpdate(SQL);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            util.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(new User(name,lastName,age));
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.delete(session.find(User.class,id));
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()){
            users = session.createQuery("FROM " + User.class.getSimpleName()).list();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM " + User.class.getSimpleName()).executeUpdate();
            transaction.commit();
        } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

