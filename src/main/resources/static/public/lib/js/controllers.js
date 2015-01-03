suspyApp

    .controller('MainController',function ($document) {
        //TODO: $.material.init(); to powinno być ale jest problem jakiś z bindowaniem
        //TODO: $.material.input(); to nie działa poprawnie
        $.material.ripples();
        $.material.checkbox();
        $.material.togglebutton();
        $.material.radio();
        $.material.autofill();

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
                            switch($scope.form.selection) {
                                case 'events':
                                    $scope.getOrganizers();
                                    $scope.getTimeSlots();
                                    $scope.getPlaces();
                                    break;
                                case 'places':
                                    $scope.getOwners();
                                    break;
                                case 'teams':
                                    break;
                            }
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

        });

        $scope.getOwners = function() {

            for (index = 0; index < $scope.results.length; ++index) {
                (function(i) {
                    var userId = $scope.results[i].owner;
                    $http.get('/service/users/' + userId)
                        .success(function (data) {
                            $scope.results[i].owner=
                            {
                                id:userId, 
                                name:data.name + ' ' +data.surname
                            };
                        })
                        .error(function (data) {
                            $scope.results[i].owner=
                            {
                                id:userId, 
                                name:'Not found'
                            };
                        });
                })(index);
            }
            
        };

        
        //wiem że dublowanie ale nie chce mi sie przerabiać
        $scope.getOrganizers = function() {

            for (index = 0; index < $scope.results.length; ++index) {
                (function(i) {
                    var userId = $scope.results[i].organizer;
                    $http.get('/service/users/' + userId)
                        .success(function (data) {
                            $scope.results[i].organizer=
                            {
                                id:userId, 
                                name:data.name + ' ' +data.surname
                            };
                        })
                        .error(function (data) {
                            $scope.results[i].organizer=
                            {
                                id:userId, 
                                name:'Not found'
                            };
                        });
                })(index);
            }

        };

        $scope.getTimeSlots = function() {

            for (index = 0; index < $scope.results.length; ++index) {
                (function(i) {
                    var timeSlotId = $scope.results[i].timeSlot;
                    $http.get('/service/timeslots/' + timeSlotId)
                        .success(function (data) {
                            $scope.results[i].timeSlot=
                            {
                                id:timeSlotId,
                                place: data.place,
                                from: data.from,
                                to: data.to
                            };
                        })
                        .error(function (data) {
                            $scope.results[i].timeSlot=
                            {
                                id:timeSlotId,
                                place:'Not found',
                                from: 'Not found',
                                to: 'Not found'
                            };
                        });
                })(index);
            }

        };

        $scope.getPlaces = function() {
            for (index = 0; index < $scope.results.length; ++index) {
                (function(i) {
                    console.log($scope.results[i].timeSlot)
                    var placeId = $scope.results[i].timeSlot.place;
                    $http.get('/service/places/' + placeId)
                        .success(function (data) {
                            $scope.results[i].timeSlot.place=
                            {
                                id: placeId,
                                name: data.name
                            };
                        })
                        .error(function (data) {
                            $scope.results[i].timeSlot.place=
                            {
                                id: placeId,
                                name: 'Not found'
                            };
                        });
                })(index);
                
            }
        
        };

        

    });