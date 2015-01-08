suspyApp

    
    .controller('MainController',function ($scope,$document) {
})
    .controller('HomeController', function() {

})
    .controller('FriendsController', function() {

})
    .controller('ErrorController', function() {

})
    .controller('newPlace', function ($scope, $http) {
        $scope.today = function() {
            $scope.dt = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.dt = null;
        };

        $scope.open = function($event , opened) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope[opened] = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.format = 'dd/MM/yyyy';


        
        $scope.getLocation = function(val) {
            return $http.get('http://maps.googleapis.com/maps/api/geocode/json', {
                // Any function returning a promise object can be used to load values asynchronously
                params: {
                    address: val,
                    sensor: false
                }
            }).then(function(response){
                return response.data.results.map(function(item){
                    return item.formatted_address;
                });
            });
        };
    })

    .controller('manageEvents', function($scope, $http){
      

        //I imagine it could be done better :)
        function getEvents () {
            $http.get('/service/events/all')
                .success(function(result) {
                    $scope.events = result;
                    
                    angular.forEach( $scope.events , function (item) {
                       
                        $http.get('/service/timeslots/' + item.timeSlot)
                            .success(function(timeSlot) {

                                $http.get('/service/places/' + timeSlot.place)
                                    .success(function(place) {
                                        
                                        timeSlot.place = place;
                                        item.timeSlot = timeSlot;

                                    })
                                    .error(function(data){
                                        console.log(data); 
                                    });
                            
                            })
                            .error(function(data){
                                console.log(data); 
                            });

                        $http.get('/service/users/' + item.organizer)
                            .success(function(user) {
                                
                                item.organizer = user;

                            })
                            .error(function(data){
                                console.log(data);
                            });

                        
                    });
                    //console.log($scope.events);
                })
                .error(function(data){
                    console.log(data); 
                });
            
        }

        
        getEvents();




    })

    
    .controller('newEventController', function($scope, $http, $document) {

        getPlaces();
        
        

        $scope.addNewEvent = function() {

            
            var newEvent = {
                deleted : false,
                name : $scope.eventName,
                details : $scope.eventDetails,
                timeSlot : $scope.eventTime.id,
                team : null,
                priv : false
            };

            console.log(newEvent);
            $http.post('/service/events/addNew', newEvent)
                .success(function (result) {
                    console.log(result);
                    
                })
                .error(function (data) {
                    console.log(data); //print out error to the log
                });
            
            $scope.eventName = '';
            $scope.eventDetails = '';
            $scope.eventPlace = '';
            $scope.eventTime = '';
        };
        
        $scope.updateTimeslots = function() {
            $http.get('/service/timeslots/place/' + $scope.eventPlace.id)
                .success(function (data) {
                    $scope.timeslots = data;
                })
                .error(function (data) {
                    console.log("Error when fetching places for creating new event")
                });
        };

        $scope.stripeCallback = function (code, result) {
            console.log("stripe callback");

            if (result.error) {
                console.log('it failed! error: ' + result.error.message);
            } else {
                console.log('success! token: ' + result.id);
            }
        };



        function getPlaces() {
            $http.get('/service/places/all')
                .success(function (data) {
                    $scope.places = data;
                })
                .error(function (data) {
                    console.log("Error when fetching places for creating new event")
                });
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
            switch($scope.form.selection){
                case 'events':
                    $scope.materialColour = 'cyan';
                    angular.element('#eventsLabel').addClass('shadow-z-5');
                    break;
                case 'places':
                    $scope.materialColour = 'orange';
                    angular.element('#placesLabel').addClass('shadow-z-5');
                    break;
                case 'teams':
                    $scope.materialColour = 'pink';
                    angular.element('#teamsLabel').addClass('shadow-z-5');
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

    });