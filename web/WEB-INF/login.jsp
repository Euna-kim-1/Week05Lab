<%-- 
    Document   : login
    Created on : 7-Feb-2023, 12:03:44 PM
    Author     : rladm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action ="login" method="post">
            Username: <input type ="text" name ="username" value ="${username}"><br>
            Password: <input type ="password" name ="password" value ="${password}"><br>
            <input type ="submit" value ="Log in">
            <p>${message}</p>
        </form>
            
    </body>
</html>
