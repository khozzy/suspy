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
** Grunt
** Grunt-wiredep (injecting dependencies into html)
** Less (if someone want to change colours of bootstrap material design)


##Getting Started

Create appropriate MySQL database:
```
CREATE DATABASE suspy;
```
Then change ```application.properties``` file for appropriate credentials.

Install Node.js and then run:
 ```
 npm install
 ```
It will install all node packages into project (declared in package.json file).

To use node packages in command line install every needed package globally with:
 ```
 npm install -g nameOfNodePackage
 ```
 Packages which probably will be used in command line:
 * Bower
 * Grunt-cli
 * Less

Finally install all css and js dependencies with:
```
bower install
```

##Run
Build and run the application:

```
mvn clean spring-boot:run
```

The application by default will run on localhost:8080.

##Adding new node packages or dependencies

There can be added new node package running:
 ```
 npm install nameOfNodePackage
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
 to inject dependencies into html ( be careful that bootstrap css and js need to
 be placed before material and ribbon css and js).


###Continuous Integration
[![Build Status](https://travis-ci.org/khozzy/suspy.png)](https://travis-ci.org/khozzy/suspy)
