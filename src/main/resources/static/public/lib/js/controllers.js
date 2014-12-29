suspyApp

    .controller('MainController',function ($document) {
        $.material.init();
})
    .controller('HomeController', function() {

})
    .controller('FriendsController', function() {

})
    .controller('ErrorController', function() {

})
    .controller('SearchController', function($scope,$http,$document) {
        $scope.form='';
        $scope.form = {
            query: '',
            selection: 'events',
            pageNum : 0,
            numOfResults : 10
        };
        var searchInput = angular.element(document.getElementById('searchInput'));
        $scope.$watchCollection('form', function() {
            $scope.results = 'Loading...';
            if ($scope.form.query != undefined && $scope.form.query !='') {
                $document.duScrollToElementAnimated(searchInput);
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
            else if($scope.form.query ==''){$document.duScrollTopAnimated(300);}

            //$("#searchResults").DataTable();

        });

    });