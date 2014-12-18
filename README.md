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
* Node.js (npm) with the following node packages:
** Bower (dependencies management)
** Less (if someone want to change colours of bootstrap material design)
** Grunt
** Grunt-wiredep (injecting dependencies into html)
** Grunt-init


##Database
Create appropriate MySQL database:

```
CREATE DATABASE suspy;
```

Change ```application.properties``` file for appropriate credentials.

##Dependency injection
After installing go to project folder and run:
 ```
 npm install
 ```
It will install all node packages into project (declared in package.json file).

To use those node packages using command line install every needed package globally with:
 ```
 npm install -g nameOfNodePackage
 ```

 Packages needed to be used in command line:
 * Bower
 * Less
 * Grunt-cli

You can add new node package running:
 ```
 npm install nameOfNodePackage
 ```

To install all dependencies run:
```
bower install
```

To add new dependency run:
 ```
 bower install nameOfDependency
 ```

After installing new dependencies you should run:
```
bower init
 ```
 to replace bower.json with new bower.json file with new dependencies or edit bower.json
 file manually and:
 ```
grunt wiredep
 ```
 to inject dependencies into html.


##Run
Build and run the application:

```
mvn clean spring-boot:run
```

The application by default will run on localhost:8080.

###Continuous Integration
[![Build Status](https://travis-ci.org/khozzy/suspy.png)](https://travis-ci.org/khozzy/suspy)
