<%-- 
    Document   : register
    Created on : Mar 10, 2019, 3:25:16 PM
    Author     : ibenk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="/Webshop/WebController?">
            <input type="hidden" name="origin" value="register">
            Username:<br>
            <input type="text" name="username"><br>
            Password:<br>
            <input type="text" name="password" > <br>
            Email: <br> 
            <input type="text" name="email" > <br> 
            <input type="submit" value="Submit">
            
        </form>
    </body>
</html>
