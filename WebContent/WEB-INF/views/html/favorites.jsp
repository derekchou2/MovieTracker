<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.MovieTracker.entity.User" %>
<%@ page import = "com.MovieTracker.entity.Movie" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
    	<% 
        	for(int i = 0; i < u.getFavorites().size(); i++) {%>
         	<div class = "movie-box">
         		<a id = "fav-button" href = "#"><i class="fas fa-star"></i></a>
         		<a id = "watch-button" href = "#"><i class="fas fa-eye"></i></a>
         		<p><%=u.getFavorites().get(i).getDesc()%></p>
         		<img src = "https://image.tmdb.org/t/p/w1280<%=u.getFavorites().get(i).getLink()%>">
         		<h2><%=u.getFavorites().get(i).getTitle()%></h2>
         	</div>
      		<% }
      	 %>
    </main>
    
    <script>
    var size = <%=u.getFavorites().size()%>;
    var output = '';
    
    for (var i = 0; i < size; i++) {
    	
    }
    const main = document.getElementById("main");
    const testdiv = document.createElement('div');
    const name = document.createElement('h1');


    </script>


</body>
</html>