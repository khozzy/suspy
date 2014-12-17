suspyApp

    .controller('MainController', function() {
})
    .controller('HomeController', function($scope) {
        $scope.name='glowna';
})
    .controller('FriendsController', function() {

})
    .controller('ErrorController', function() {

})
    .controller('SearchController', function($scope, $http) {

        $scope.search = function(query){

           // if(selection=='event') {
            //    return results + 'event';
            //};

           // if(selection=='place') {
           //     return results + 'place';
           // };

            //$http.get('service/users/1')
             //   .success(function (data) {
              //  $scope.results = data;
            //})
             //   .error(function(data){});
            return query;

        }
    });