/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Webshop.logic;

import Webshop.entity.Bottoms;
import Webshop.entity.Cupcake;
import Webshop.entity.LineItem;
import Webshop.entity.ShoppingCart;
import Webshop.entity.Toppings;
import Webshop.entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibenk
 */
public class DataMapperUsers {
    
    private DBConnector dbc;

    public DataMapperUsers() throws SQLException {
        this.dbc = new DBConnector();
    }
    
    /**
    * The createUser-method takes a username, password and email as input.
    * Adds User to Database.
    */
    
    public void createUser(String username, String password, String email) throws SQLException 
    {
       if (username != null || password != null || email != null)
       {    
        try {
            dbc = new DBConnector();
            String addUser
                    = "INSERT INTO cupcake.users (`username`, `password`, `balance`, `email` ) "
                    + "VALUES(?,?,0,?);";

            PreparedStatement ps = dbc.getConnection().prepareStatement(addUser);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();
            } catch (SQLException ex) {
          Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    }
    
    /**
    * The getUser-method finds all information about the user that has the username, we give as input.
    */
    
    public Users getUser(String username) throws SQLException {
        
        Users user = new Users();

        dbc = new DBConnector();

        String query = "SELECT * FROM cupcake.users "
                + "WHERE `username`='" + username + "';";

        Connection connection = dbc.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String password = rs.getString("password");
            user.setPassword(password);
            int balance = rs.getInt("balance");
            user.setBalance(balance);
            String email = rs.getString("email");
            user.setEmail(email);
        }
        user.setUserName(username);
        return user;
    }
    
    public void addInvoice(Users user) {
        try {
            dbc = new DBConnector();

            String name = user.getUserName();
            String insertBalance = "INSERT INTO `cupcake`.`invoices` (`name`) VALUES (?);";
            PreparedStatement ps = dbc.getConnection().prepareStatement(insertBalance);
            ps.setString(1, name);
            ps.executeUpdate();

            ShoppingCart cart = user.getCart();
            for (LineItem item : cart.getLineItems()) {
                addOrder(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addOrder(LineItem item) throws SQLException {
        dbc = new DBConnector();

        Cupcake cake = item.getCupcake();
        Toppings top = cake.getTop();
        Bottoms bot = cake.getBottom();

        String tname = top.getName();
        String bname = bot.getName();
        int qty = item.getQuantity();

        String query = "INSERT INTO `cupcake`.`orderdetails` (tname, bname, qty)"
                + "VALUES (?, ?, ?)";

        PreparedStatement ps = dbc.getConnection().prepareStatement(query);
        ps.setString(1, tname);
        ps.setString(2, bname);
        ps.setInt(3, qty);
        ps.executeUpdate();
    }
    
}
