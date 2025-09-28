package org.example.model;

import org.example.database.ConfigDb;
import org.example.entity.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository<Product>{
    @Override
    public Product create(Product product) {
        Product objProduct = (Product) product;
        String sql = "INSERT INTO products(name, price, stock) VALUES (?, ?, ?)";

        try( Connection connection = ConfigDb.getConnection();
             PreparedStatement objPrepare = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ){
            objPrepare.setString(1, product.getName());
            objPrepare.setDouble(2, product.getPrice());
            objPrepare.setInt(3, product.getStock());
            objPrepare.execute();
            ResultSet rs = objPrepare.getGeneratedKeys();

            while(rs.next()){
                objProduct.setId(rs.getInt(1));
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, "Product creation error: " + error.getMessage());
        }
        return objProduct;

    }

    @Override
    public Product getById(int id) {
        Product objProduct = null;
        String sql = "SELECT * FROM products WHERE id = ?";
        try(Connection connection = ConfigDb.getConnection();
            PreparedStatement objPrepare = connection.prepareStatement(sql)
        ){
            objPrepare.setInt(1, id);
            ResultSet rs = objPrepare.executeQuery();
            if(rs.next()){
                objProduct = new Product();
                objProduct.setId(rs.getInt("id"));
                objProduct.setName(rs.getString("name"));
                objProduct.setPrice(rs.getDouble("price"));
                objProduct.setStock(rs.getInt("stock"));
            }

        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        return objProduct;
    }

    @Override
    public List<Product> getAll() {
        List<Product> listProducts = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try(Connection connection = ConfigDb.getConnection();
            PreparedStatement objPrepare = connection.prepareStatement(sql)
        ){
            ResultSet rs = objPrepare.executeQuery();
            while(rs.next()){
                Product objProduct = new Product();
                objProduct.setId(rs.getInt("id"));
                objProduct.setName(rs.getString("name"));
                objProduct.setPrice(rs.getDouble("price"));
                objProduct.setStock(rs.getInt("stock"));
                listProducts.add(objProduct);
            }

        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        return listProducts;
    }

    @Override
    public boolean update(Product product) {
        Product objProduct = (Product) product;
        String sql = "UPDATE products SET name = ?, price = ?, stock = ? WHERE id = ?";
        boolean isUpdated = false;
        try(Connection connection = ConfigDb.getConnection();
            PreparedStatement objPrepare = connection.prepareStatement(sql);
        ){
            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getStock());
            objPrepare.setInt(4, objProduct.getId());

            int result = objPrepare.executeUpdate();
            if(result > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Product updated successfully");

            }
        } catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Product product) {
        boolean isDeleted = false;
        Product objProduct = (Product) product;
        String sql = "DELETE FROM products WHERE id = ?";
        try(
            Connection connection = ConfigDb.getConnection();
            PreparedStatement objPrepare = connection.prepareStatement(sql);
            ){

            objPrepare.setInt(1, objProduct.getId());
            int result = objPrepare.executeUpdate();
            if(result > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Product deleted successfully");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        return isDeleted;
    }

    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";
        try (Connection connection = ConfigDb.getConnection();
             PreparedStatement objPrepare = connection.prepareStatement(sql)
        ) {
            objPrepare.setString(1, "%" + name + "%");
            ResultSet rs = objPrepare.executeQuery();

            while (rs.next()) {
                Product objProduct = new Product();
                objProduct.setId(rs.getInt("id"));
                objProduct.setName(rs.getString("name"));
                objProduct.setPrice(rs.getDouble("price"));
                objProduct.setStock(rs.getInt("stock"));
                products.add(objProduct);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error searching products: " + error.getMessage());
        }
        return products;
    }

}
