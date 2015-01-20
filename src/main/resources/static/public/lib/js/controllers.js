suspyApp

    
    .controller('MainController',function ($scope,$document) {
})
    .controller('HomeController', function() {

})
    .controller('FriendsController', function() {

})
    .controller('ErrorCtrl', function($scope) {


})
    .controller('homeAdvisorCtrl', function($scope){

            //carusel
            $scope.myInterval1 = 3000;
            $scope.myInterval2 = 2000;
            $scope.myInterval3 = 2500;
        

            var slides1 = $scope.slides1 = [];
            var slides2 = $scope.slides2 = [];
            var slides3 = $scope.slides3 = [];

            $scope.addSlides = function() {
                slides1.push({
                    image: '/public/lib/assets/stadium' + slides1.length + '.jpg'
                });
                slides2.push({
                    image: '/public/lib/assets/pool' + slides2.length + '.jpg'
                });
                slides3.push({
                    image: '/public/lib/assets/voley' + slides3.length + '.jpg'
                });
            };
        
            for (var i=0; i<3; i++) {
                $scope.addSlides();
            }
        
    })

    .controller('SearchController', function($scope,$http,$document,hotkeys) {
        $scope.form='';
        $scope.form = {
            query: '',
            selection: 'events',
            pageNum : 0,
            numOfResults : 10
        };
        var searchInput = angular.element('#searchInput');
        $scope.$watchCollection('form', function() {
            $scope.progressBarVal = 0;
            if ($scope.form.query != undefined && $scope.form.query !='') {
                $scope.results = 'Loading...';
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
                                    $scope.progressBarVal += 1/4*100;
                                    $scope.getOrganizers();
                                    $scope.getTimeSlots();
                                    break;
                                case 'places':
                                    $scope.progressBarVal += 1/2*100;
                                    $scope.getOwners();
                                    break;
                                case 'teams':
                                    break;
                            }
                            $document.duScrollToElementAnimated(searchInput);
                        }
                        else{
                            $scope.results = '';
                            $scope.progressBarVal = 100;

                        }
                    })
                    .error(function (data) {
                        $scope.results = '';
                        $scope.progressBarVal = 100;
                    });

            }
            else if($scope.form.query ==''){$document.duScrollTopAnimated(0);}

            
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
                            $scope.progressBarVal += 1/($scope.results.length*2)*100;
                        })
                        .error(function (data) {
                            $scope.results[i].owner=
                            {
                                id:userId, 
                                name:'Not found'
                            };
                            $scope.progressBarVal += 1/($scope.results.length*2)*100;
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
                            $scope.progressBarVal += 1/($scope.results.length*4)*100;
                        })
                        .error(function (data) {
                            $scope.results[i].organizer=
                            {
                                id:userId, 
                                name:'Not found'
                            };
                            $scope.progressBarVal += 1/($scope.results.length*4)*100;
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
                            $scope.progressBarVal += 1/($scope.results.length*4)*100;
                            $scope.getPlace(i);
                        })
                        .error(function (data) {
                            $scope.results[i].timeSlot=
                            {
                                id:timeSlotId,
                                place:'Not found',
                                from: 'Not found',
                                to: 'Not found'
                            };
                            $scope.progressBarVal += 1/($scope.results.length*4)*100;
                        });
                })(index);
            }

        };

        $scope.getTimeSlotsForPlaces = function() {

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
                            $scope.progressBarVal += 1/($scope.results.length*4)*100;
                            $scope.getPlace(i);
                        })
                        .error(function (data) {
                            $scope.results[i].timeSlot=
                            {
                                id:timeSlotId,
                                place:'Not found',
                                from: 'Not found',
                                to: 'Not found'
                            };
                            $scope.progressBarVal += 1/($scope.results.length*4)*100;
                        });
                })(index);
            }

        };

        $scope.getPlace = function(index) {
                (function(i) {
                    var placeId = $scope.results[i].timeSlot.place;
                    
                    $http.get('/service/places/' + placeId)
                        .success(function (data) {
                            $scope.results[i].timeSlot.place=
                            {
                                id: placeId,
                                name: data.name
                            };
                            $scope.progressBarVal += 1/($scope.results.length*4)*100;
                        })
                        .error(function (data) {
                            $scope.results[i].timeSlot.place=
                            {
                                id: placeId,
                                name: 'Not found'
                            };
                            $scope.progressBarVal += 1/($scope.results.length*4)*100;
                        });
                })(index);

        };
        
        $scope.round = function(value){
            return Math.round(value);
        };
        
        $scope.searchButtons = function(){
            angular.element('#eventsLabel').removeClass('shadow-z-5');
            angular.element('#placesLabel').removeClass('shadow-z-5');
            angular.element('#teamsLabel').removeClass('shadow-z-5');
            angular.element('#searchInput').removeClass('form-control-material-cyan');
            angular.element('#searchInput').removeClass('form-control-material-orange');
            angular.element('#searchInput').removeClass('form-control-material-pink');
            switch($scope.form.selection){
                case 'events':
                    $scope.materialColour = 'cyan';
                    angular.element('#eventsLabel').addClass('shadow-z-5');
                    angular.element('#searchInput').addClass('form-control-material-cyan');
                    break;
                case 'places':
                    $scope.materialColour = 'orange';
                    angular.element('#placesLabel').addClass('shadow-z-5');
                    angular.element('#searchInput').addClass('form-control-material-orange');
                    break;
                case 'teams':
                    $scope.materialColour = 'pink';
                    angular.element('#teamsLabel').addClass('shadow-z-5');
                    angular.element('#searchInput').addClass('form-control-material-pink');
                    break;
            }

        };

        hotkeys.bindTo($scope)
            .add({
                combo: 'right',
                description: 'Changes form selection',
                allowIn: ['INPUT', 'SELECT', 'TEXTAREA'],
                callback: function() {
                    switch($scope.form.selection){
                        case 'events':
                            $scope.form.selection = 'places';
                            break;
                        case 'places':
                            $scope.form.selection = 'teams';
                            break;
                        case 'teams':
                            $scope.form.selection = 'events';
                            break;
                    }
                    $scope.searchButtons();
                }
            });
        
        hotkeys.bindTo($scope)
            .add({
                combo: 'left',
                description: 'Changes form selection',
                allowIn: ['INPUT', 'SELECT', 'TEXTAREA'],
                callback: function(){
                    switch ($scope.form.selection) {
                        case 'events':
                            $scope.form.selection = 'teams';
                            break;
                        case 'places':
                            $scope.form.selection = 'events';
                            break;
                        case 'teams':
                            $scope.form.selection = 'places';
                            break;
                    }
                    $scope.searchButtons();
                }
            });

    })

    .controller('userProfile', function($scope, $http) {

        var isObservedBool = false;
        var isMyselfBool = false;

        $scope.init = function(userID) {
            $scope.userID = userID;

            $http.get('/service/users/' + userID).success(function (result) {
                $scope.user = result;

                // Get teams data
                angular.forEach($scope.user.teams, function(team) {
                    $scope.user.teamsData = [];
                    $http.get('/service/teams/' + team).success(function(result) {
                        $scope.user.teamsData.push(result);
                    })
                });

                // Get observed data
                angular.forEach($scope.user.observed, function(obs) {
                    $scope.user.observedData = [];
                    $http.get('/service/users/' + obs).success(function(result) {
                        $scope.user.observedData.push(result);
                    })
                });

                $http.get('/service/users/current').success(function (result) {
                    // Determine if observed
                    if (result.observed.indexOf($scope.user.id) != -1) {
                        isObservedBool = true;
                    }

                    // Determine if myself
                    if (result.id == $scope.user.id) {
                        isMyselfBool = true;
                    }
                })
            });
        }

        $scope.isObserved = function() {
            return isObservedBool;
        }

        $scope.isMyself = function() {
            return isMyselfBool;
        }
    });
