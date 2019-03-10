<%-- 
    Document   : shop
    Created on : Mar 8, 2019, 11:23:06 AM
    Author     : ibenk
--%>

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
            </select>
            <select name="topping">
                <option value="chocolate" >Chocolate</option>
                <option value="blueberry" >Blueberry</option>
                <option value="Strawberry">Strawberry 6$</option>
            </select>
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


    </body>
</html>
