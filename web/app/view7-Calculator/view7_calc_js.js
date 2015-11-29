'use strict';

angular.module('myApp.view7_calc', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view7_calc', {
                    templateUrl: 'app/view7-Calculator/view7_calc.html',
                    controller: 'calculator'
                });
            }])

        .controller('calculator', function ($http, $scope) {

            $scope.number = {};
            
            $scope.myvalue = false;
            

            $scope.getData = function ()
            {
                $http({
                    method: 'GET',
                    url: 'api/demoUser/currency/calculator/' + Number($('#amount').val()) + '/' + $('#from').val() + '/' + $('#to').val()

                }).then(function successCallback(response) {
                    $scope.number = response.data;

//                    console.log($scope.response);
                    console.log($scope.number);



                }, function errorCallback(response) {
                    alert("Error occured");
                });
            };


            $scope.showAlert = function () {
                $scope.myvalue = true;
            };

        });
//        $http({
//            method: 'GET',
//            url: 'api/demouser'
//        }).then(function successCallback(res) {
//            $scope.data = res.data.message;
//        }, function errorCallback(res) {
//            $scope.error = res.status + ": " + res.data.statusText;
//        });


