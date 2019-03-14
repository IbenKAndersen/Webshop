<%-- 
    Document   : checkout
    Created on : Mar 14, 2019, 8:34:51 AM
    Author     : ibenk
--%>

<%@page import="Webshop.entity.LineItem"%>
<%@page import="Webshop.entity.Users"%>
<%@page import="Webshop.entity.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Check out</h1>
        <%
            Users user = (Users) session.getAttribute("user");
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

            for (LineItem item : cart.getLineItems()) {
                out.println("<p>" + item.toString() + "</p>");
            }
        %>
    </body>
</html>
