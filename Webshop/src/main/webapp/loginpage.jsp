<%-- 
    Document   : loginpage
    Created on : Mar 8, 2019, 10:33:51 AM
    Author     : ibenk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>loginpage</title>
    </head>
    <body>
        <h1>Log in</h1>
        <form method="post" action="/Webshop/WebController?">
            <input type="hidden" name="origin" value="loginpage">
            Username:<br>
            <input type="text" name="username"><br>
            Password:<br>
            <input type="text" name="password" > <br>
            <input type="submit" value="Log in">
        </form>
        <!-- 
        <br>Username: <br> <input type="text" class="form-control" id="usr"> <br> 
        <br>Password: <br> <input type="password" class="form-control" id="pwd"> <br>
        <br><a href="WebController?origin=shop" class="btn btn-info" role="button">Log in</a> <br>
        -->
    </body>
</html>
