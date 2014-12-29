suspyApp

    .controller('MainController',function () {
        //$.material.init();
       /* $(document).arrive($.material.options.inputElements, function() {
            $.material.input($(this));
        });
        $(document).arrive($.material.options.checkboxElements, function() {
            $.material.checkbox($(this));
        });
        $(document).arrive($.material.options.radioElements, function() {
            $.material.radio($(this));
        });
        $(document).arrive($.material.options.togglebuttonElements, function() {
            $.material.togglebutton($(this));
        });
        */
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
            query: '',
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