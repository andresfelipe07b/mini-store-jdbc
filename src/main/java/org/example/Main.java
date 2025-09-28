package org.example;

import org.example.controller.ProductController;
import org.example.model.InventoryService;
import org.example.model.InventoryServiceImpl;
import org.example.model.ProductRepositoryImpl;
import org.example.model.Repository;
import org.example.entity.Product;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Repository<Product> productRepository = new ProductRepositoryImpl();
        InventoryService inventoryService = new InventoryServiceImpl(productRepository);
        ProductController productController = new ProductController(inventoryService);

        boolean running = true;
        while (running) {
            String[] options = {
                    "1. Add product",
                    "2. List inventory",
                    "3. Update price",
                    "4. Update stock",
                    "5. Delete product",
                    "6. Search product by name",
                    "7. Exit"
            };

            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Mini Store JDBC - Select an option",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0:
                    productController.createProduct();
                    break;
                case 1:
                    productController.listAllProducts();
                    break;
                case 2:
                    productController.updateProductPrice();
                    break;
                case 3:
                    productController.updateProductStock();
                    break;
                case 4:
                    productController.deleteProduct();
                    break;
                case 5:
                    productController.findProductsByName();
                    break;
                case 6:
                    productController.showOperationsSummary();
                    running = false;
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }
}
