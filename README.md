#Suspy
The main goal of the Suspy project is to make life of people who love performing any kind of
activities as easy as possible. It will be fulfilled by creating web application allowing users to create events
connected with any activity, finding people who want to join created before events, finding interesting
activities, creating teams of people who love performing the same activity, add their own objects where the
events can be performed. Users should be able also to communicate with each other, make friendships, etc.
Suspy will also be integrated with most popular social media websites like Facebook or Endomondo.

##Requirements
* Java Oracle JDK 1.8
* Maven >= 3.1
* MySQL

##Database
Create appropriate MySQL database:

```
CREATE DATABASE suspy;
```

Change ```application.properties``` file for appropriate credentials.

##Run
Build and run the application:

```
mvn clean spring-boot:run
```

The application by default will run on localhost:8080.

###Continuous Integration
[![Build Status](https://travis-ci.org/khozzy/teamfinder.png)](https://travis-ci.org/khozzy/teamfinder)
