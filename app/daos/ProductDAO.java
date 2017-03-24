package daos;

import com.google.inject.Singleton;
import models.Product;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by rownak on 3/23/17.
 */
@Singleton
public class ProductDAO {
    public List<Product> selectAllProducts() {
        EntityManager em = JPA.em("default");
        List<Product> result = em.createQuery("SELECT p FROM Product p").getResultList();
        em.close();
        return result;
    }

    public void createProduct(Product product) {
        EntityManager em = JPA.em("default");
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public Boolean deleteProduct(Integer id) {
        EntityManager em = JPA.em("default");
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
        EntityManager em = JPA.em("default");
        Query q = em.createQuery("select p from Product p where p.id=?1", Product.class);
        q.setParameter(1, id);
        try {
            product = (Product) q.getSingleResult();
        } catch (NoResultException ex) {
        }
        em.close();
        return product;
    }

    public Boolean updateProduct(Integer id, Product product) {
        EntityManager em = JPA.em("default");
        em.getTransaction().begin();
        Query q2 = em.createQuery("update Product p set p.categoryId = ?1, p.specification = ?2," +
                "p.code = ?3, p.name = ?4 where p.id=?5");
        q2.setParameter(1, product.getCategoryId()).setParameter(2, product.getSpecification()).setParameter(3,
                product.getCode()).setParameter(4, product.getName()).setParameter(5, id);
        int changed = q2.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return changed > 0;
    }
}
