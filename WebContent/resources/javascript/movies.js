// API information.
const url = 'https://api.themoviedb.org/3/movie/top_rated?api_key=81410e22566b6c91f61e80134358d75d&language=en-US&page=1';
const IMGPATH = "https://image.tmdb.org/t/p/w1280";
const SEARCHAPI =
    "https://api.themoviedb.org/3/search/movie?&api_key=04c35731a5ee918f014970082a0088b1&query=";
// Selecting our Elements.
const main = document.getElementById("main");
const form = document.getElementById("form");
const search = document.getElementById("search");
/* call the showMovies function that requests the movie data from the Api using fetch.
 Then it puts those data in the main HTML tag by creating elments for those data. */
showMovies(url);
function showMovies(url){
    fetch(url).then(res => res.json())
    .then(function(data){
    data.results.forEach(element => {
      // Creating elemnts for our data inside the main tag. 
        const el = document.createElement('div');
        el.classList.add("movie-box")
        const image = document.createElement('img');
        image.setAttribute("name", "link");
        const title = document.createElement('h2');
        title.setAttribute("name", "title");
        const description = document.createElement('p');
        description.setAttribute("name", "desc");

        const favButton = document.createElement('a');
        favButton.id = "fav-button";
        favButton.innerHTML = "<i class=\"fas fa-star\"></i>"
        favButton.href = "addToFavorites";
        const watchButton = document.createElement('a');
        watchButton.id = "watch-button";
        watchButton.href = "addToWatchList";
        watchButton.innerHTML = "<i class=\"fas fa-eye\"></i>"

        title.innerHTML = element.title;
        description.innerHTML = element.overview;
        image.src = IMGPATH + element.poster_path;
        el.appendChild(favButton);
        el.appendChild(watchButton);
        el.appendChild(description);
        el.appendChild(image);
        el.appendChild(title);
        
        
        main.appendChild(el);
    }); 
});
}

// listens for search bar submission
form.addEventListener("submit", (e) => {
    e.preventDefault();
    main.innerHTML = '';
     
    const searchTerm = search.value;

 /* Append user search to our search variable, load page with search matches and reset searchbar*/
    if (searchTerm) {
        showMovies(SEARCHAPI + searchTerm);
        search.value = "";
    }
});

