suspyApp

    .controller('MainController',function () {

})
    .controller('HomeController', function() {

})
    .controller('FriendsController', function() {

})
    .controller('ErrorController', function() {

})
    .controller('SearchController', function($scope,$http) {
        $scope.form='';
        $scope.form = {
            selection: 'events',
            pageNum : 0,
            numOfResults : 10
        };
        $scope.$watchCollection('form', function() {
            $scope.results = 'Loading...';
            if ($scope.form.query != undefined && $scope.form.query !='') {
                $http.get('/service/' + $scope.form.selection,
                    {params: {
                        query: $scope.form.query,
                        pageNum: $scope.form.pageNum,
                        numOfResults: $scope.form.numOfResults
                    }})
                    .success(function (data) {
                        if(data.numberOfElements!=0) {
                            $scope.results = data.content;
                        }
                        else{
                            $scope.results = '';
                        }
                    })
                    .error(function (data) {
                        $scope.results = '';
                    });

            }

            //$("#searchResults").DataTable();

        });

    });