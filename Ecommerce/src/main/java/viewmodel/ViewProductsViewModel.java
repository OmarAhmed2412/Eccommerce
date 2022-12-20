/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ProductItem;

/**
 *
 * @author Blu-Ray
 */
public class ViewProductsViewModel {
    ArrayList<ProductItem> productsArrayList = new ArrayList<>();

    public ArrayList<ProductItem> getProducts() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery("select * from product_table");
        while (resultset.next()) {
            productsArrayList.add(new ProductItem(resultset.getInt("id"), resultset.getString("name"), resultset.getDouble("price")));
        }
        
        return productsArrayList;
    }
    public void displayProducts (JTable table) throws SQLException{
        productsArrayList = getProducts();
        String name;     
        double price;
        int id;
        DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();    
            for (int i = 0; i < productsArrayList.size(); i++) {
                name = productsArrayList.get(i).getName();
                price = productsArrayList.get(i).getPrice();
                id = productsArrayList.get(i).getId();
                tablemodel.addRow(new Object[]{id, name, price});   
            }        
    }
}
