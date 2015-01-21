/**
 * Created by alapinsk on 2015-01-20.
 */
suspyApp

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
            })
            .error(function(data){
                console.log(data);
            });

    }

    getEvents();
})

    .controller('newEventController', function($scope, $http, $document, $location, $filter) {

        getPlaces();

        $scope.updateTimeslots = function() {
            $http.get('/service/timeslots/place/' + $scope.eventPlace.id)
                .success(function (data) {
                    $scope.timeslots = data;
                })
                .error(function (data) {
                    console.log("Error when fetching places for creating new event")
                });
        };

        $scope.pay = function(e) {
            var promise = $http.get('/service/timeslots/' + $scope.eventTime.id)
                .success(function (data) {
                    $scope.timeSlotInfo = data;
                    $scope.openPayment(e);
                })
                .error(function (data) {
                    console.log("Error when fetching timeslot")
                });
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

        var handler = StripeCheckout.configure({
            key: 'pk_test_6pRNASCoBOKtIshFeQd4XMUh',
            image: '/public/lib/assets/logo.svg',
            token: function(token) {
                // Use the token to create the charge with a server-side script.
                // You can access the token ID with `token.id`
                var newEvent = {
                    deleted : false,
                    name : $scope.eventName,
                    details : $scope.eventDetails,
                    timeSlot : $scope.eventTime.id,
                    team : null,
                    priv : false,
                    token : token.id
                };
                
                console.log(newEvent);
                $http.post('/service/events/addNew', newEvent)
                    .success(function (result) {
                        console.log(result);
                        changeLocation("/events/" + result);
                    })
                    .error(function (data) {
                        console.log(data);
                    });
                
                //be sure to inject $scope and $location
                var changeLocation = function(url, forceReload) {
                    $scope = $scope || angular.element(document).scope();
                    if(forceReload || $scope.$$phase) {
                        window.location = url;
                    }
                    else {
                        //only use this if you want to replace the history stack
                        //$location.path(url).replace();

                        //this this if you want to change the URL and add it to the history stack
                        $location.path(url);
                        $scope.$apply();
                    }
                };
            }
        });
        
        $scope.openPayment =  function(e) {
            // Open Checkout with further options
            console.log($scope.timeSlotInfo);
            handler.open({
                name: $scope.eventPlace.name,
                description:
                $filter('date')($scope.timeSlotInfo.from,'dd-MM-yy HH:mm') +' - '+
                $filter('date')($scope.timeSlotInfo.to,'dd-MM-yy HH:mm'),
                amount: $scope.timeSlotInfo.price*100,
                currency: 'USD'
            });
            e.preventDefault();
        };

        // Close Checkout on page navigation
        angular.element(window).on('popstate',function() {
            handler.close();
        });
        
    })