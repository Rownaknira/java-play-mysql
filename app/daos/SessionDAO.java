package daos;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import models.Session;
import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by rownak on 3/28/17.
 */
@Singleton
public class SessionDAO {
    private final Provider<JPAApi> jpa;

    @Inject
    public SessionDAO(Provider<JPAApi> jpa) {
        this.jpa = jpa;
    }

    public String createSession(Session session) {
        EntityManager em = jpa.get().em("default");
        em.getTransaction().begin();
        em.persist(session);
        em.getTransaction().commit();
        em.close();

        return session.getAuthToken();
    }

    public Session selectByAuthToken(String authToken) {
        Session session = null;
        EntityManager em = jpa.get().em("default");
        Query q = em.createQuery("select s from Session s where s.authToken=?1", Session.class);
        q.setParameter(1, authToken);
        try {
            session = (Session) q.getSingleResult();
        } catch (NoResultException ex) {
        }
        em.close();
        return session;
    }
}
