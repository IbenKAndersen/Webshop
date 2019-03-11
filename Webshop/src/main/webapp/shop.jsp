<%-- 
    Document   : shop
    Created on : Mar 8, 2019, 11:23:06 AM
    Author     : ibenk
--%>

<%@page import="Webshop.entity.LineItem"%>
<%@page import="java.util.List"%>
<%@page import="Webshop.entity.ShoppingCart"%>
<%@page import="Webshop.entity.Cupcake"%>
<%@page import="Webshop.entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>It's cupcake o'clock</h1><br>Happiness is only a cupcake away<br>
        <br>
        <%
            Users user = (Users) session.getAttribute("user");
            out.println("User: " + user.getUserName() + ", balance: " + user.getBalance());
        %>
        <form method="POST" action="/Webshop/WebController?">
            <input type="hidden" name="origin" value="cupcake">
            <select name="bottom">
                <option value="chocolate" >Chocolate</option>
                <option value="vanilla" >Vanilla</option>
                <option value="nutmeg" >Nutmeg</option>
                <option value="pistacio" >Pistacio</option>
                <option value="almond" >Almond</option>
            </select>
            <select name="topping">
                <option value="chocolate" >Chocolate</option>
                <option value="blueberry" >Blueberry</option>
                <option value="raspberry" >Raspberry</option>
                <option value="crispy" >Crispy</option>
                <option value="strawberry">Strawberry</option>
                <option value="rumraisin" >Rum/Raisin</option>
                <option value="orange" >Orange</option>
                <option value="lemon" >Lemon</option>
                <option value="bluecheese" >Blue cheese</option>
            </select>
            <input type="text" name="qty" />
            <input type="submit" value="Select">
        </form> 
        <%
            Cupcake cupcake = (Cupcake) session.getAttribute("cupcake");
            if (cupcake != null) {
                out.println("You have ordered a cupcake with " + cupcake.getBottom() + "-bottom and " + cupcake.getTop() + "-topping");
            } else {
                out.println("No cupcake ordered");
            }
        %>
        <br> 
        <br>
        <%
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart != null && !cart.isEmpty()) {
                if (cart.getLineItems().size() == 1) {
                    LineItem item = cart.getLineItems().get(0);
                    out.println(item.toString());
                } else {
                    int totalprice = 0;
                    for (LineItem item : cart.getLineItems()) {
                        out.println(item.toString());
        %>
        <br>
        <%
                        totalprice = totalprice + item.getTotalPrice();
                    }
        %>
        <br> <br>
        <%
                    out.println("Totalprice: " + totalprice);
                }
            } else {
                out.println("ShoppingCart is empty");
            }
        %>
    </body>
</html>
