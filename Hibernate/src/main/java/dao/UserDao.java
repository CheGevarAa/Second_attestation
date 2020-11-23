package dao;

import models.Auto;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.SessionUtil;
import java.util.List;

public class UserDao {

    public User findById(int id) {
        return SessionUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = SessionUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = SessionUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = SessionUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Auto findAutoById(int id) {
        return SessionUtil.getSessionFactory().openSession().get(Auto.class, id);
    }

    public List<User> findAll() {
        List<User> users = (List<User>)  SessionUtil.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    public User findUserByAuto(int id){
        return SessionUtil.getSessionFactory().openSession().get(Auto.class, id).getUser();
    }
}
