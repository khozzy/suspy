var suspyApp = angular.module('suspyApp',['duScroll'])
    .value('duScrollDuration', 300)
    .value('duScrollOffset', 70);

//var suspyApp = angular.module('suspyApp',['ngRoute']);
/*suspyApp.config(function($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: 'public/lib/views/homeJS.html',
            controller: 'HomeController'
        })
        .when('/friends', {
            templateUrl: 'public/lib/views/friendsJS.html',
            controller: 'FriendsController'
        })
        .otherwise({
            templateUrl: 'public/lib/views/errorJS.html',
            controller: 'ErrorController'
        });
});*/