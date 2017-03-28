package daos;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import models.User;
import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by rownak on 3/27/17.
 */
@Singleton
public class UserDAO {
    private final Provider<JPAApi> jpa;

    @Inject
    public UserDAO(Provider<JPAApi> jpa) {
        this.jpa = jpa;
    }

    public void createUser(User user) {
        EntityManager em = jpa.get().em("default");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public User selectByEmail(String email) {
        User user = null;
        EntityManager em = jpa.get().em("default");
        Query q = em.createQuery("select u from User u where u.email=?1", User.class);
        q.setParameter(1, email);
        try {
            user = (User) q.getSingleResult();
        } catch (NoResultException ex) {
        }
        em.close();
        return user;
    }
}
