/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ProductItem;

/**
 *
 * @author Blu-Ray
 */
public class AddProductViewModel {
    
    Connection connection;
    private ViewProductsViewModel vpvm = new ViewProductsViewModel();
    ArrayList<ProductItem> productsArrayList = new ArrayList<>();

    public AddProductViewModel() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
    }
    
     public void insertProduct(String name, double price, int Id) throws SQLException {
        String InsertProductQuery = "INSERT INTO product_table VALUES (?, ?, ?)";
        PreparedStatement preparedstatment = connection.prepareStatement(InsertProductQuery);
        preparedstatment.setInt(1, Id);
        preparedstatment.setString(2, name);
        preparedstatment.setDouble(3, price);
        preparedstatment.executeUpdate();
        preparedstatment.close();
    }
     
     
    public boolean isUnique(int id) {
        for (int i = 0; i < productsArrayList.size(); i++) {
            if (id == productsArrayList.get(i).getId()) {
                return false;
            }
        }
        return true;
    }
    
    
    public boolean validateThenAdd( String id, String name, String price) throws SQLException {

        double p = 0;
        int ID = 0;
        try {

            p = Double.parseDouble(price);
            ID = Integer.parseInt(id);

            productsArrayList = vpvm.getProducts();
            boolean idValidation = isUnique(ID);
            if (idValidation == false) {
                JOptionPane.showMessageDialog(null, "A product exsists with the same id");
                return false;
            }
            if (p <= 0.0) {
                JOptionPane.showMessageDialog(null, "Price must be greater than ");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProductViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Number fields cannot contain characters!");
            return false;
        }
        insertProduct(name, p, ID);
        return true;
    }
}
