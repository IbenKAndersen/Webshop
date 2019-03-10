/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Webshop.logic;

import Webshop.entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
}
