<div id="header" align="center">
  <img src="football-image.svg.png" width="1600"/>
</div>

## 📖 Description
This app look like a simple visualisation of Football control.
Where you can add teams and players. Also you can manage players and teams, like
deleting, updating, see all models, deleting and transfer player to another team, 
and while transferring balance of teams will change.

## 📋 Project structure
**The project has an 3-Tier Architecture**
- Controller - This level allows the user to work with this application.
- Service - This level of architecture is responsible for processing the data received from the DAO level.
- Repository - This level of architecture is responsible for communicating with the database.

## 🎯 Features
- Saving to DB all models
- Updating all models
- Deleting all models
- Watching list of models
- Transferring players to another teams

## 🖥️ Technologies
- Java 17
- Maven
- MySQL
- Hibernate
- Liquibase
- Tomcat
- Swagger
- Spring Web/Boot/MVC
- DOCKER
- Angular

## ⚡️Quickstart

### For launch project

1. Install Docker Desktop and register on DockerHub

2. Run next command in terminal from `football-api` directory:

   > mvn clean package

3. Run next command in terminal from the main directory:

   > docker-compose up --build

4. Open your browser on http://localhost:4200.

### For local development

1. Fork this repository
2. Copy link of project
3. Create new project from Version Control
4. Edit resources/application.properties - set the necessary parameters

``` java
    spring.datasource.driver-class-name=YOUR_DRIVER
    spring.datasource.url=YOUR_URL
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
```
5. Do not forget set this param on "create" for first project run. Like this:
``` java
    spring.jpa.hibernate.ddl-auto=create
```
6. Create the necessary name of DB
7. Run project

## 👀 Example of parameters for db.properties
``` java
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/NameOfDataBase?useUnicode=true&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=123456
```
