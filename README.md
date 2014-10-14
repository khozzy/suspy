#TeamFinder
Main goal of the project is to make life of people who love sport activities as easy as it is possible. This goal will be fulfilled by creating online booking system of gym halls where users will be able to find nearest free gym halls, to book them, to create events in them and finally to find people who want to join created before events. Users should also be able to communicate with each other, to add own gym halls, create teams (make team bookings) and make calendars of events. Our portal should support cash transactions (also team cash transactions – in other words fee for the gym will be divided into everyone who joined the event), signing in with facebook, etc.

##Requirements
* Java Oracle JDK 1.8
* Maven >= 3.1
* MySQL

##Database
Create appropriate MySQL database:

```
CREATE DATABASE teamfinder;
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
