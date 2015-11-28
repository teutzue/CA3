'use strict';

angular.module('myApp.view6_create_new_account', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6_create_new_account', {
                    templateUrl: 'app/view6_create_new_account/view6_create_new_account.html',
                    controller: 'view6_create_new_accountCtrl'
                });
            }])

        .controller('view6_create_new_accountCtrl', function ($scope, $http) {

            $scope.wrongPs = "visibility: hidden";
            $scope.missingPS = "visibility: hidden";

            $scope.submit = function () {

                if (checkPassword($scope.password, $scope.password2) & checkUsername($scope.userName)) {

                    var credential = JSON.stringify({
                        username: $scope.userName,
                        password: $scope.password
                    });

                    var req = {
                        method: 'POST',
                        url: 'api/allusers',
                        headers: {'Content-Type': 'application/json'},
                        data: credential
                    };

                    $http(req).then(function successCallback(response) {
                        // this callback will be called asynchronously
                        // when the response is available
                        if (response.data.isSaved === "yes") {
                            $scope.userName = "";
                            $scope.password = "";
                            $scope.password2 = "";
                            $scope.isSaved = response.data.isSaved;
                            $scope.feetback = response.data.message;
                        } else {
                            $scope.feetbackError = response.data.message;
                        }
                    }, function errorCallback(response) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                        $scope.feetback = response.status;
                    });
                } // End of if-passworld

            }; // End of submit

            $scope.Style = function (isSaved) {

                if (isSaved === "yes") {
                    return "color: green";
                } else {
//                alert(isSaved);
                    return "color: red";
                }
            }; // End of Style

            $scope.Focus = function () {
                $scope.feetback = "";
                $scope.feetbackError = "";
                $scope.wrongPs = "visibility: hidden";
                $scope.missingPS = "visibility: hidden";
            }; // End of Focus()

            var checkPassword = function (pw1, pw2) {

                if (checkForPassword(pw1)) {
                    if (pw1 === pw2) {
                        $scope.wrongPs = "visibility: hidden";
                        return true;
                    } else {
                        $scope.wrongPs = "visibility: visible";
                        return false;
                    }
                }
            }; // End of checkPassword()


            var checkForPassword = function (ps) {
                if ((angular.isUndefined(ps)) | (ps === "")) {
                    $scope.missingPS = "visibility: visible";
                    return false;
                } else {
                    $scope.missingPS = "visibility: hidden";
                    return true;
                }

            };

            var checkUsername = function (username) {

                if ((angular.isUndefined(username)) | (username === "")) {
                    $scope.feetbackError = "Missing username";
                    return false;
                }
                return true;
            };

        }); // End of controller