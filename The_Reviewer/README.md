#Users_service_app
This app is a service that allows users to register and login.


#TO run the DB
docker run --name mymariadb -e MARIADB_ROOT_PASSWORD=mypass -p 3306:3306 -d mariadb:latest

#TO build the APP
docker build -t users_service . 

#TO run the APP
docker run -it --rm --link mymariadb  -p 8080:8080 users_service

#TO run the APP in compose
docker-compose up