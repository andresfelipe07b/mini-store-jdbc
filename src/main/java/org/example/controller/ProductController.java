package org.example.controller;

import org.example.entity.Product;
import org.example.model.InventoryService;

import javax.swing.*;
import java.util.List;

public class ProductController {
    private final InventoryService inventoryService;
    private int addedProducts = 0;
    private int updatedProducts = 0;
    private int deletedProducts = 0;

    public ProductController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void createProduct() {
        try {
            String name = JOptionPane.showInputDialog("Enter the product name:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty");
                return;
            }

            String priceStr = JOptionPane.showInputDialog("Enter the product price:");
            double price = Double.parseDouble(priceStr);
            if (price <= 0) {
                JOptionPane.showMessageDialog(null, "Price must be greater than 0");
                return;
            }

            String stockStr = JOptionPane.showInputDialog("Enter the product stock:");
            int stock = Integer.parseInt(stockStr);
            if (stock < 0) {
                JOptionPane.showMessageDialog(null, "Stock cannot be negative");
                return;
            }

            Product product = new Product(0, name, price, stock);
            inventoryService.createProduct(product);
            addedProducts++;
            JOptionPane.showMessageDialog(null, "Product created successfully");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format. Please enter a valid numeric value.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating product: " + e.getMessage());
        }
    }

    public void listAllProducts() {
        List<Product> products = inventoryService.getAllProducts();
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no products in the inventory");
            return;
        }

        StringBuilder sb = new StringBuilder("List of products:\n\n");
        for (Product product : products) {
            sb.append("ID: ").append(product.getId())
                    .append(" | Name: ").append(product.getName())
                    .append(" | Price: $").append(String.format("%.2f", product.getPrice()))
                    .append(" | Stock: ").append(product.getStock())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void updateProductPrice() {
        try {
            String idStr = JOptionPane.showInputDialog("Enter the ID of the product to update:");
            int id = Integer.parseInt(idStr);

            Product product = inventoryService.getProductById(id);
            if (product == null) {
                JOptionPane.showMessageDialog(null, "Product not found");
                return;
            }

            String priceStr = JOptionPane.showInputDialog("Enter the new price for " + product.getName() + ":");
            double price = Double.parseDouble(priceStr);
            if (price <= 0) {
                JOptionPane.showMessageDialog(null, "Price must be greater than 0");
                return;
            }

            inventoryService.updateProductPrice(id, price);
            updatedProducts++;
            JOptionPane.showMessageDialog(null, "Price updated successfully");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format. Please enter a valid numeric value.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating price: " + e.getMessage());
        }
    }

    public void updateProductStock() {
        try {
            String idStr = JOptionPane.showInputDialog("Enter the ID of the product to update:");
            int id = Integer.parseInt(idStr);

            Product product = inventoryService.getProductById(id);
            if (product == null) {
                JOptionPane.showMessageDialog(null, "Product not found");
                return;
            }

            String stockStr = JOptionPane.showInputDialog("Enter the new stock for " + product.getName() + ":");
            int stock = Integer.parseInt(stockStr);
            if (stock < 0) {
                JOptionPane.showMessageDialog(null, "Stock cannot be negative");
                return;
            }

            inventoryService.updateProductStock(id, stock);
            updatedProducts++;
            JOptionPane.showMessageDialog(null, "Stock updated successfully");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format. Please enter a valid numeric value.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating stock: " + e.getMessage());
        }
    }

    public void deleteProduct() {
        try {
            String idStr = JOptionPane.showInputDialog("Enter the ID of the product to delete:");
            int id = Integer.parseInt(idStr);

            Product product = inventoryService.getProductById(id);
            if (product == null) {
                JOptionPane.showMessageDialog(null, "Product not found");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete the product: " + product.getName() + "?",
                    "Confirm deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                inventoryService.deleteProduct(id);
                deletedProducts++;
                JOptionPane.showMessageDialog(null, "Product deleted successfully");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format. Please enter a valid numeric value.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting product: " + e.getMessage());
        }
    }

    public void findProductsByName() {
        try {
            String name = JOptionPane.showInputDialog("Enter the name to search for:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You must enter text to search");
                return;
            }

            List<Product> products = inventoryService.findProductsByName(name);
            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No products found matching: " + name);
                return;
            }

            StringBuilder sb = new StringBuilder("Products found:\n\n");
            for (Product product : products) {
                sb.append("ID: ").append(product.getId())
                        .append(" | Name: ").append(product.getName())
                        .append(" | Price: $").append(String.format("%.2f", product.getPrice()))
                        .append(" | Stock: ").append(product.getStock())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error searching for products: " + e.getMessage());
        }
    }

    public void showOperationsSummary() {
        StringBuilder summary = new StringBuilder("Operations summary:\n\n");
        summary.append("Products added: ").append(addedProducts).append("\n");
        summary.append("Products updated: ").append(updatedProducts).append("\n");
        summary.append("Products deleted: ").append(deletedProducts).append("\n");

        JOptionPane.showMessageDialog(null, summary.toString(), "Operations Summary", JOptionPane.INFORMATION_MESSAGE);
    }
}
