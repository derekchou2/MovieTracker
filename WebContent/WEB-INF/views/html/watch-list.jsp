<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.MovieTracker.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/movies.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movies</title>
    
    <script src="https://kit.fontawesome.com/cab2a53f55.js" crossorigin="anonymous"></script>
</head>
<body>
    <%User u = (User)session.getAttribute("user"); %>
    <header>
        <a href="movies" id = "welcome" class = "header-item"><h1>Welcome <%=u.getName()%>!</h1></a>
        <form id="form">
            <input
                type="text" id="search" placeholder="Look up movies..." class="search"
            />
        </form>
        <a href="favorites" id = "favorites" class = "header-item"><h2>Favorites</h2></a>
        <a href="watch-list" id = "watch-list" class = "header-item" ><h2>Watchlist</h2></a>
        <a href="logout" id = "log-out" class = "header-item"><h2>Log Out</h2></a>
    </header>
    <main id = "main">
        
    </main>


</body>
</html>