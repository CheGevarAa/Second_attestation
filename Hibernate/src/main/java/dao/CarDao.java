package dao;

import models.Auto;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.SessionUtil;
import java.util.List;

public class CarDao {

    public Auto findById(int id) {
        return SessionUtil.getSessionFactory().openSession().get(Auto.class, id);
    }

    public void save(Auto auto) {
        Session session = SessionUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(auto);
        tx1.commit();
        session.close();
    }

    public void update(Auto auto) {
        Session session = SessionUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(auto);
        tx1.commit();
        session.close();
    }

    public void delete(Auto auto) {
        Session session = SessionUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(auto);
        tx1.commit();
        session.close();
    }


    public List<Auto> findAll() {
        List<Auto> auto = (List<Auto>)  SessionUtil.getSessionFactory().openSession().createQuery("From Auto").list();
        return auto;
    }
}
