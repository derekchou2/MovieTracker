# MovieTracker - README


## Project Description
MovieTracker is an application that allows users to search from a database of over 8500 movies and allows them to view their movie posters as well as descriptions. They can add their favorite movies to their favorites page, and any movies that they plan to view later to their watchlist. The database of movies is generated from querying the online API The Movie Database(TMDB)

## User Stories
1. As I user I want to be able to register on a landing/registration page.
2. As a user I want to be able to login into my account with all my movies saved from previous sessions.
3. As a user I want to be notified if my registration or login are invalid.
4. As a user I want to be able to add movies to my favorites page from all other pages.
5. As a user I want to be able to add movies to my watchlist from all other pages.
6. As a user I want to be able to remove movies from my watchlist and favorites pages.
7. As a user I want to be notified by the application if adding movies to a list was successful or not.
8. As a user I want to be able to search for movies from the database from any page.
9. As a user I want to be able to log out and end my session safely.
10. As a user I want to be able to view movie posters, titles, and see a brief description of the plot when I hover over the movie.
11. As a user I want all of my user data and movie data to be stored in an organized database.

## Main Technical Challenges
-To display the movies from TMDB I had to learn the basics of fetching json data and using promises and thens to cyclically create movie boxes for each element.

-To communicate with the MariaDB database I created I had to create services classes to handle CRUD operations

-At the beginning I didn't know how to get the movie info from my front-end to persist in the database since I was using Spring MVC for the first time. To make sure I had at least a partially complete database I manually transferred each page of the JSON data in CSVs and parsed the relevant columns using a SQL script that I wrote. I added about a quarter of the movies from TMDB into my MariaDB database this way.

-Later on, I tried to use the Spring MVC model attribute annotation to handle form requests on the JSP side so I could add movies to my database automatically and fill my database as users clicked the favorite/watchlist buttons on the front-end but none of the movie descriptions were being mapped correctly for some reason. I decided to instead handle the movie information as separate Request params instead and had no issues.

-Some movies were not being displayed correctly after users added them to the database, and I noticed that it occured specifically to movies whose descriptions included certain characters such as apostraphes or accent marks from foreign films. After researching character sets and gaining some knowledge on how to specify them for my project I was able to convert my JSP character sets into the UTF-8 format which resolved my issue.

-My last major issue involved users logging out and having a different user log in, only to have their movies set to those of the previous user. I had not handled my user sessions correctly and I realized I was not changing them on login as I should have. To add another layer of protection I made sure to invalidate the current session when any user logged out.

#Thank you to the Per Scholas instruction team. The instructor Marcial Cordon and TA Bably worked endlessly to provide us with a full-stack curriculum and were extremely patient with me whenever I had any questions or issues.

