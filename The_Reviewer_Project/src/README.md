#THE REVIEWER APP
This app is a service of movies reviews.

This application allows authenticated users to search movies by original or portuguese title, create reviews of them,
see the local rating and internal ratings of Rotten Tomatoes and IMDB.

This application is linked with the websites of Rotten Tomatoes, IMDB and NOS cinemas:
 - https://www.rottentomatoes.com/
 - https://www.imdb.com/
 - https://cinemas.nos.pt/

The users have to register and logged in, to make movies searches and reviews.

The application gives information about the searched film, namely the original title, the title in Portuguese, year,
director, actors, Rotten Tomatoes Rating, IMDB Rating, number of Reviews. If the APP returns the information of a "movie 
not found", it means that the film has not yet been reviewed.

The application allows searching if there are available sessions of a specific film in NOS cinemas.

POSSIBLE REQUESTS:

- @RequestMapping("/api/user")
  - Posters:
    - /create (to create a user posting: username, email and password)
  - Getters:
    - /searchid/{id} (to search user by id)
    - /allusers (to search all users)
    - /{id}/reviews (to search reviews of a specific user)
  - Updaters:
    - /updatesettings (to update all)
    - /{userId}/admin (to the owner)
    - /changepassword (to change user's password)
  - Deleters:
    - /deletebyid/{id} (to delete user by id, only by admin)
    - /deleteall (to delete all users, only to the owner)
    - /token/refresh (When not authenticated)


- @RequestMapping("/api/review")
  - Posters:
    - /create (to create a review posting: movieName, description, localRating)
  - Getters:
    - /allreviews (to admin)
    - /searchmovie/{movieId} (to search a movie by id)
    - /searchid/{reviewId} (to search a review by a review id)
  - Deleters:
    - /delete/{reviewId} (to delete a review by review id)
    - /deletemyreview/{movieId} (to delete user's review by movie id)
    - /deleteall (to delete all reviews, only by admin)


- @RequestMapping("/api/movie")
  - Getters:
    - /allmovies (to see all movies that have reviews)
    - /searchtitle/{originalTitle} (to search reviews of a movie by original title)
    - /searchdirector/{director} (to search reviews of a movie by the name of the director)
    - /searchactor/{actor} (to search a reviews of a movie by the name of an actor)
    - /searchyear (to search a reviews of a movie by the year)
    - /imdb (to search reviews for all the movies that have also internal rating on IMDB)
    - /rottentomatoes (to search reviews for all the movies that have also internal rating on Rotten Tomatoes)
    - /localrating 
    - /sessions/{movieId} (to search the movie sessions available on the cinemas NOS, by the movie id)
    

- @RequestMapping("/auth")
  - Posters:
    - /login (email and password)
    

#TO run the DB
docker run --name mymariadb -e MARIADB_ROOT_PASSWORD=mypass -p 3306:3306 -d mariadb:latest


#TO build the APP
docker build -t the_reviewer


#TO run the APP
docker run -it --rm --link mymariadb  -p 8080:8080 the_reviewer


#TO run the APP in compose
docker-compose up

#TO Swagger Api Documentation
http://localhost:8080/swagger-ui/index.html#/