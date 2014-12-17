suspyApp

    .controller('MainController', function() {

})
    .controller('HomeController', function($scope) {

})
    .controller('FriendsController', function() {

})
    .controller('ErrorController', function() {

})
    .controller('SearchController', function($scope,$http) {

        $scope.searchUrl = '/events';
        $scope.pageNum = 0;
        $scope.numOfResults = 10;

        $scope.$watch('query', function() {
            if ($scope.query != undefined && $scope.query !='') {
                if($scope.selection=='event') {
                    $scope.searchUrl = 'events/';
                };

                if($scope.selection=='place') {
                    $scope.searchUrl = 'places/';
                };
                $http.get('/service/' + $scope.searchUrl,
                    {params: {
                        query: $scope.query,
                        pageNum: $scope.pageNum,
                        numOfResults: $scope.numOfResults
                    }})
                    .success(function (data) {
                        $scope.results = data;
                    })
                    .error(function (data) {
                        $scope.results = 'No results found.';
                    });
            };

            if($scope.query==''){
                $scope.results='';
            };
        });

    });