package org.example.model;

import org.example.entity.Product;
import java.util.List;


public interface InventoryService {
    void createProduct(Product product);
    void updateProductPrice(int id, double newPrice);
    void updateProductStock(int id, int newStock);
    void deleteProduct(int id);
    Product getProductById(int id);
    List<Product> getAllProducts();
    List<Product> findProductsByName(String name);

}
