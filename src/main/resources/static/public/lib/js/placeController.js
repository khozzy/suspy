/**
 * Created by alapinsk on 2015-01-20.
 */
suspyApp

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

    .controller('placeCtrl', function($scope,$http){

        //carusel
        $scope.myInterval = 5000;

        var slides = $scope.slides = [];

        $scope.addSlide = function() {
            slides.push({
                image: '/public/lib/assets/stadium' + slides.length + '.jpg'
            });
        };
        for (var i=0; i<3; i++) {
            $scope.addSlide();
        }

        $scope.init = function(placeID) {
            $scope.placeID = placeID;
            $scope.timeSlot = [];
            $http.get('/service/places/' + placeID)
                .success(function (place) {
                    $scope.place = place;

                    $http.get('/service/users/' + $scope.place.owner)
                        .success(function(owner){

                            $scope.place.owner = owner;

                        });

                    angular.forEach($scope.place.timeSlots ,function(item){

                        $http.get('/service/timeslots/' + item)
                            .success(function (timeSlots) {
                                $http.get('/service/events/' + timeSlots.event)
                                    .success(function(event) {

                                        timeSlots.event = event;
                                        $scope.timeSlot.push(timeSlots);

                                        //console.log(event);
                                        //console.log(angular.toJson($scope.place));
                                        //console.log(angular.toJson(timeSlot[0]));
                                        //console.log(timeSlot[1]);
                                        console.log($scope.timeSlot);
                                    })

                            })

                    });
                })
        }
    })