package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dtos.ProductRequestDTO;
import dtos.ProductResponseDTO;
import models.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ProductService;

import java.util.List;

/**
 * Created by rownak on 3/23/17.
 */
public class ProductController extends Controller {
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final FormFactory formFactory;

    @Inject
    public ProductController(ProductService productService, ModelMapper modelMapper, FormFactory formFactory) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.formFactory = formFactory;
    }

    public Result getAllProduct() {
        List<Product> productList = productService.getProductList();
        java.lang.reflect.Type targetListType = new TypeToken<List<ProductResponseDTO>>() {}.getType();
        List<ProductResponseDTO> productResponseDTOList = modelMapper.map(productList, targetListType);

        return ok(Json.toJson(productResponseDTOList));
    }

    public Result createProduct() {
        Form<ProductRequestDTO> productForm = formFactory.form(ProductRequestDTO.class).bindFromRequest();

        if (productForm.hasErrors()) {
            JsonNode jsonError = productForm.errorsAsJson();
            return badRequest(jsonError);
        }
        Product product = modelMapper.map(productForm.get(), Product.class);
        productService.create(product);

        return ok(Json.toJson("Successfully created"));
    }

    public Result showProductDetails(Integer id) {
        if (productService.getProductDetails(id) == null) {
            return ok("Product not found");
        }
        else {
            return ok(Json.toJson(productService.getProductDetails(id)));
        }
    }

    public Result deleteProduct(Integer id) {
        if (productService.delete(id) == true) {
            return ok(Json.toJson("Successfully deleted"));
        }
        else {
            return ok(Json.toJson("Can not be deleted"));
        }
    }

    public Result updateProduct(Integer id) {
        Form<ProductRequestDTO> productForm = formFactory.form(ProductRequestDTO.class).bindFromRequest();

        if (productForm.hasErrors()) {
            JsonNode jsonError = productForm.errorsAsJson();
            return badRequest(jsonError);
        }
        Product product = modelMapper.map(productForm.get(), Product.class);

        if (productService.update(id, product) == true) {
            return ok(Json.toJson("Successfully updated"));
        }
        else {
            return ok(Json.toJson("Can not be updated"));
        }
    }
}
