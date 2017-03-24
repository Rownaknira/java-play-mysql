package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import daos.ProductDAO;
import models.Product;

import java.util.List;
import java.util.Random;

/**
 * Created by rownak on 3/23/17.
 */
@Singleton
public class ProductService {
    private final ProductDAO productDAO;

    @Inject
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getProductList() {
        return productDAO.selectAllProducts();
    }

    public void create(Product product) {
        Random random = new Random();
        product.setId(random.nextInt(999999999 + 1 - 1) + 1);
        productDAO.createProduct(product);
    }

    public Boolean delete(Integer id) {
        return productDAO.deleteProduct(id);
    }

    public Boolean update(Integer id, Product product) {
        Product existingProduct = productDAO.selectOne(id);
        if (existingProduct == null) {
            return false;
        }
        else {
            return productDAO.updateProduct(id, product);
        }
    }
}
