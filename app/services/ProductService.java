package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import daos.ProductDAO;
import models.Product;

import java.util.List;

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
        productDAO.createProduct(product);
    }

    public Product getProductDetails(Integer id) { return productDAO.selectOne(id);}

    public Boolean delete(Integer id) {
        return productDAO.deleteProduct(id);
    }

    public Boolean update(Integer id, Product product) {
        Product existingProduct = productDAO.selectOne(id);
        if (existingProduct == null) {
            return false;
        }
        else {
            existingProduct.setCode(product.getCode());
            existingProduct.setName(product.getName());
            existingProduct.setSpecification(product.getSpecification());
            productDAO.updateProduct(existingProduct);
            return true;
        }
    }
}
