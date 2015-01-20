/**
 * Created by alapinsk on 2015-01-20.
 */
suspyApp

    .controller('teamsManage', function($scope, $http) {
        function getTeams() {
            $http.get('/service/teams/my').success(function(result) {
                $scope.teams = result;

                angular.forEach($scope.teams, function (team) {
                    team.memberNames = [];
                    angular.forEach(team.members, function (member) {
                        $http.get('/service/users/' + member).success(function (user) {
                            team.memberNames.push(user);
                        })
                    })
                })
                console.log($scope.teams);
            })
        }
        getTeams();
    })

    .controller('teamProfile', function($scope, $http) {

        var isMemberBool = false;
        var isLeaderBool = false;

        $scope.init = function(teamID) {
            $scope.teamID = teamID;
            $http.get('/service/teams/' + teamID).success(function (result) {
                $scope.team = result;

                // Get leader name and surname
                $http.get('/service/users/' + $scope.team.leader).success(function(result) {
                    $scope.team.leaderName = result.name;
                    $scope.team.leaderSurname = result.surname;
                })

                // Get members names and surnames
                angular.forEach($scope.team.members, function(member) {
                    $scope.team.membersData = [];
                    $http.get('/service/users/' + member).success(function(result) {
                        $scope.team.membersData.push(result);
                    })
                })

                // Get events of the team
                angular.forEach($scope.team.events, function(event) {
                    $scope.team.eventsData = [];
                    $http.get('/service/events/' + event).success(function(result) {
                        $scope.team.eventsData.push(result);
                    })
                })

                $http.get('/service/users/current').success(function (result) {
                    // Determine if the current user is member of the team
                    if ($scope.team.members.indexOf(result.id) != -1) {
                        isMemberBool = true;
                    }

                    // Determine if the current user is leader of the team
                    if (result.id == $scope.team.leader) {
                        isLeaderBool = true;
                    }
                })
            })
        }

        $scope.isMember = function() {
            return isMemberBool;
        }

        $scope.isLeader = function() {
            return isLeaderBool;
        }
    })
