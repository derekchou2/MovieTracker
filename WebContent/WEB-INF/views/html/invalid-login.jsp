<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/invalid.css" />
</head>
<body>
    <div id = "register" class = "main-container">
        <h1>Invalid email or password</h1>

                <img id="sad-popcorn" alt="sad-popcorn" src="<%=request.getContextPath()%>/resources/images/Sad-popcorn.jpg" />


        <div class = "button-wrapper">
            <button><a href = "logout">Take me back!</a></button>
        </div>


    </div>

      
</body>
</html>