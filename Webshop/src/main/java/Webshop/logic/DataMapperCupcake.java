package Webshop.logic;

import Webshop.entity.Bottoms;
import Webshop.entity.Cupcake;
import Webshop.entity.Toppings;
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
 * @author
 */
public class DataMapperCupcake {

    private DBConnector conn;

    /*
    
        Cupcake Data Mapper Class. 
            Connects to SQL.
    
     */
    public DataMapperCupcake() {

    }

    /**
     * Get a cupcake
     *
     * @param topName
     * @param bottomName
     * @return
     */
    public Cupcake makeCupcake(String topName, String bottomName) {

        Toppings top = getTop(topName);
        Bottoms bottom = getBottom(bottomName);
        return new Cupcake(top, bottom);

    }

    /**
     * Get a cupcake
     *
     * @param top
     * @param bot
     * @return
     */
    public Cupcake makeCupcake(Toppings top, Bottoms bot) {
        return new Cupcake(top, bot);
    }

    /**
     * Get a Top
     *
     * @param name
     * @return
     */
    public Toppings getTop(String name) {
        if (name != null) {
            int price = getTopPrice(name);
            Toppings top = new Toppings(name, price);
            return top;
        }
        return null;
    }

    /**
     * Insert a top into the SQL database. Dont use.
     *
     * @param name
     * @param price
     */
    public void addTopping(String name, int price) {
        try {
            conn = new DBConnector();

            String insertTopping = "START TRANSACTION;"
                    + "SET AUTOCOMMIT = OFF;"
                    + "INSERT INTO `cupcake`.`topping` (tname, price) "
                    + "VALUES (?, " + price + ");"
                    + "COMMIT;";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertTopping);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataMapperCupcake.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Insert a bottom into the SQL database. Dont use.
     *
     * @param name
     * @param price
     */
    public void addBottom(String name, int price) {
        try {
            conn = new DBConnector();

            String insertBottom = "INSERT INTO `cupcake`.`bottom` (bname, price) "
                    + "VALUES (?, " + price + ");";
            PreparedStatement ps = conn.getConnection().prepareStatement(insertBottom);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataMapperCupcake.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get a Bottom
     *
     * @param name
     * @return
     */
    public Bottoms getBottom(String name) {
        if (name != null) {
            int price = getBottomPrice(name);
            Bottoms bot = new Bottoms(name, price);
            return bot;
        }
        return null;
    }

    /**
     * Returns a list of Tops.
     *
     * @return
     */
    public List<Toppings> getTops() {
        try {
            conn = new DBConnector();

            String query = "SELECT * FROM `Cupcake`.`topping`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<Toppings> toppings = new ArrayList<>();
            String name;
            int price;

            while (rs.next()) {
                name = rs.getString("tname");
                price = rs.getInt("Price");
                Toppings top = new Toppings(name, price);
                toppings.add(top);
            }
            return toppings;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Returns a list of Bottoms.
     *
     * @return List of Bottoms.
     */
    public List<Bottoms> getBottoms() {
        try {
            conn = new DBConnector();

            String query = "SELECT * FROM `Cupcake`.`bottom`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<Bottoms> bottoms = new ArrayList<>();
            String name;
            int price;

            while (rs.next()) {
                name = rs.getString("bname");
                price = rs.getInt("Price");
                Bottoms bottom = new Bottoms(name, price);
                bottoms.add(bottom);
            }
            return bottoms;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Gets a price for a bottom piece of Cupcake.
     *
     * @param name name of the bottom piece.
     * @return int - Price
     */
    public int getTopPrice(String name) {
        try {
            conn = new DBConnector();

            String query = "SELECT price FROM `Cupcake`.`topping` "
                    + "WHERE `cupcake`.`topping`.`tname` = '" + name + "';";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int price = 0;

            while (rs.next()) {
                price = rs.getInt("price");
            }
            return price;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    /**
     * Gets a price for a bottom piece of Cupcake.
     *
     * @param name name of the bottom piece.
     * @return int - Price
     */
    public int getBottomPrice(String name) {
        try {
            conn = new DBConnector();

            String query = "SELECT price FROM `Cupcake`.`bottom` "
                    + "WHERE `cupcake`.`bottom`.`bname` = '" + name + "';";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int price = 0;

            while (rs.next()) {
                price = rs.getInt("price");
            }
            return price;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
}
