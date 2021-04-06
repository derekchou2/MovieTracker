<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.MovieTracker.entity.User"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/movies.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Movies</title>

<script src="https://kit.fontawesome.com/cab2a53f55.js"
	crossorigin="anonymous"></script>
</head>


<body>
	<%
		User u = (User) session.getAttribute("user");
	%>

	<header>
		<a href="movies" id="welcome" class="header-item"><h1>
				Welcome
				<%=u.getName()%>!
			</h1></a>
		<form id="form">
			<input type="text" id="search" placeholder="Look up movies..."
				class="search" />
		</form>
		<a href="favorites" id="favorites" class="header-item"><h2>Favorites</h2></a>
		<a href="watch-list" id="watch-list" class="header-item"><h2>Watchlist</h2></a>
		<a href="logout" id="log-out" class="header-item"><h2>Log Out</h2></a>
	</header>
	<main id="main"></main>

	<!--putting script tag in head resulted in not being able to use document.getElementByID-->
	<script> 
	const url='https://api.themoviedb.org/3/movie/top_rated?api_key=81410e22566b6c91f61e80134358d75d&language=en-US&page=1';
	const IMGPATH="https://image.tmdb.org/t/p/w1280" ;
	const SEARCHAPI="https://api.themoviedb.org/3/search/movie?&api_key=04c35731a5ee918f014970082a0088b1&query=";
	const main=document.getElementById( "main");
	const form=document.getElementById( "form");
	const search=document.getElementById("search");

	showMovies(url);
	
	function showMovies(url){
    	fetch(url).then(res=> res.json()).then(function(data){
    	data.results.forEach(element => {
      
        const el = document.createElement('div');
        el.classList.add("movie-box")
        const image = document.createElement('img');
        image.setAttribute("name", "link");
        const title = document.createElement('h2');
        title.setAttribute("name", "title");
        const descript = document.createElement('p');
        descript.setAttribute("name", "descript");

        const favButton = document.createElement('a');
        favButton.id = "fav-button";
        favButton.innerHTML = "<i class=\"fas fa-star\"></i>"
        favButton.href = "addToFavorites?description=" + element.overview + "&title=" + element.title + "&link=" + element.poster_path;
        const watchButton = document.createElement('a');
        watchButton.id = "watch-button";
        watchButton.href = "addToWatchlist?description=" + element.overview + "&title=" + element.title + "&link=" + element.poster_path;
        watchButton.innerHTML = "<i class=\"fas fa-eye\"></i>"

        title.innerHTML = element.title;
        descript.innerHTML = element.overview;
        image.src = IMGPATH + element.poster_path;
        el.appendChild(favButton);
        el.appendChild(watchButton);
        el.appendChild(descript);
        el.appendChild(image);
        el.appendChild(title);
        
        
        main.appendChild(el);
	    }); 
		});
	}


	form.addEventListener("submit", (e) => {
	    e.preventDefault();
	    main.innerHTML = '';
	     
	    const searchTerm = search.value;
	
	 
	    if (searchTerm) {
	        showMovies(SEARCHAPI + searchTerm);
	        search.value = "";
	    }
	});

    </script>
</body>
</html>