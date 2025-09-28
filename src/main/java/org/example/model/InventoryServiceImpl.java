package org.example.model;

import org.example.entity.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {
    private final Repository<Product> productRepository;

    public InventoryServiceImpl(Repository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(Product product) {
        try {
            productRepository.create(product);
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("for key 'name'")) {
                JOptionPane.showMessageDialog(null, "A product with that name already exists");
            } else {
                JOptionPane.showMessageDialog(null, "Error creating product: " + e.getMessage());
            }
        }
    }

    @Override
    public void updateProductPrice(int id, double newPrice) {
        Product product = productRepository.getById(id);
        if (product != null) {
            product.setPrice(newPrice);
            productRepository.update(product);
        } else {
            JOptionPane.showMessageDialog(null, "Product with ID " + id + " not found");
        }
    }

    @Override
    public void updateProductStock(int id, int newStock) {
        Product product = productRepository.getById(id);
        if (product != null) {
            product.setStock(newStock);
            productRepository.update(product);
        } else {
            JOptionPane.showMessageDialog(null, "Product with ID " + id + " not found");
        }
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.getById(id);
        if (product != null) {
            productRepository.delete(product);
        } else {
            JOptionPane.showMessageDialog(null, "Product with ID " + id + " not found");
        }
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public List<Product> findProductsByName(String name) {
        List<Product> allProducts = productRepository.getAll();
        List<Product> matchingProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }
}