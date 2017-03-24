package daos;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import models.Product;
import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by rownak on 3/23/17.
 */
@Singleton
public class ProductDAO {
    private final Provider<JPAApi> jpa;

    @Inject
    public ProductDAO(Provider<JPAApi> jpa) {
        this.jpa = jpa;
    }

    public List<Product> selectAllProducts() {
        EntityManager em = jpa.get().em("default");
        List<Product> result = em.createQuery("SELECT p FROM Product p").getResultList();
        em.close();
        return result;
    }

    public void createProduct(Product product) {
        EntityManager em = jpa.get().em("default");
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public Boolean deleteProduct(Integer id) {
        EntityManager em = jpa.get().em("default");
        em.getTransaction().begin();
        Query q = em.createQuery("delete from Product p where p.id=?1");
        q.setParameter(1, id);
        int changed = q.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return changed > 0;
    }

    public Product selectOne(Integer id) {
        Product product = null;
        EntityManager em = jpa.get().em("default");
        Query q = em.createQuery("select p from Product p where p.id=?1", Product.class);
        q.setParameter(1, id);
        try {
            product = (Product) q.getSingleResult();
        } catch (NoResultException ex) {
        }
        em.close();
        return product;
    }

    public void updateProduct(Product product) {
        EntityManager em = jpa.get().em("default");
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }
}
