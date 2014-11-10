<%-- 
    Document   : filter_login
    Created on : Nov 1, 2014, 12:13:34 AM
    Author     : elmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign in</title>
    </head>
    <body>
        <h1>Login page</h1>
        <form action="LoginServlet" method="post">
            Username: <input type='text' name='username'/> <br/>
            Password: <input type="password" name='password'/>
            <input type="submit" name='login' value='Login'/> &nbsp;
            <input type='reset' name='clear' value='Clear'/>
        </form>
    </body>
</html>
