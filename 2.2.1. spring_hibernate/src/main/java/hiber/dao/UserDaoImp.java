package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import hiber.model.Car.*;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    @Autowired
    private SessionFactory sessionFactory;

    private static final String QUERY = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserList() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> findUserCars(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(QUERY, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList();
    }
}
