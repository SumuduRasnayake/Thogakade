/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogaKade.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.thogaKade.db.DBConnection;
import lk.ijse.thogaKade.model.Customer;

/**
 *
 * @author niroth
 */
public class CustomerController {
    public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into Customer Values(?,?,?,?)");
        stm.setObject(1, customer.getId());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getSalary());
        return stm.executeUpdate()>0;
    }
    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update Customer set name=?, address=?, salary=? where id=?");
         stm.setObject(1, customer.getName());
        stm.setObject(2, customer.getAddress());
        stm.setObject(3, customer.getSalary());
        stm.setObject(4, customer.getId());
        return stm.executeUpdate()>0;
    }    
    public static boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        String SQL="Delete from Customer where id='"+id+"'";
        Statement stm = connection.createStatement();
        return stm.executeUpdate(SQL)>0;
    }        
    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        String SQL="Select * From Customer where id='"+id+"'";
        ResultSet rst = stm.executeQuery(SQL);
        if(rst.next()){
            return new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"),rst.getDouble("salary"));
        }
        return null;
    }
    public static ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * From Customer");
        ArrayList<Customer> customerList=new ArrayList<>();
        while(rst.next()){
            Customer customer=new Customer();
            customer.setId(rst.getString("id"));
            customer.setName(rst.getString("name"));
            customer.setAddress(rst.getString("address"));
            customer.setSalary(rst.getDouble("salary"));
            customerList.add(customer);
            //customerList.add(new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"),rst.getDouble("salary")));
        }
        return customerList;
    }
}
