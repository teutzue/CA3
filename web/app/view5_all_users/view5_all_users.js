'use strict';

angular.module('myApp.view5_all_users', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5_all_users', {
                    templateUrl: 'app/view5_all_users/view5_all_users.html',
                    controller: 'view5_all_usersCtrl'
                });
            }])

        .controller('view5_all_usersCtrl', function ($http, $scope) {

            $scope.users;

            // Get users :: Cliked
            $scope.getUsers = function () {
                $http.get('api/demoadmin/users')
                        .success(function (data, status, headers, config) {

                            $scope.users = data;                          
                        })
                        .error(function (data, status, headers, config) {

                        });                           
            }; // End of getUsers


            // Delete user
            $scope.delete = function (uName) {

                $http.delete('api/demoadmin/users/' + uName)
                        .success(function (data, status, headers, config) {

                            var index = -1;
                            var comArr = eval($scope.users);
                            for (var i = 0; i < comArr.length; i++) {
                                if (comArr[i].username === uName) {
                                    index = i;
                                    break;
                                }
                            }
                            if (index === -1) {
                                alert("Something gone wrong");
                            }
                            $scope.users.splice(index, 1);



                        })
                        .error(function (data, status, headers, config) {
                            alert(data);
                        });

            }; // End of delete




        }); // End of controller