'use strict';

angular.module('myApp.view7_calc', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view7_calc', {
                    templateUrl: 'app/view7-Calculator/view7_calc.html',
                    controller: 'calculator'
                });
            }])

        .controller('calculator', function ($http, $scope) {


            $http({
                method: 'GET',
                url: 'api/demoUser/cur'
            }).then(function successCallback(response) {
                $scope.data.countrycodes = response.data;
                $scope.data1.countrycodes1 = response.data;
            }, function errorCallback(response) {
                $scope.error = res.status + ": " + res.data.statusText;
            });





            $scope.data = {
                repeatSelect: null,
                countrycodes: []
            };

//            $scope.data.countrycodes = [
//                {code: "USD", description: "US dollars"},
//                {code: "EUR", description: "Euro"},
//                {code: "GBP", description: "Pounds sterling"}
//            ];


            $scope.data1 = {
                repeatSelect1: null,
                countrycodes1: []
            };

//            $scope.data1.countrycodes1 = [
//                {code: "USD", description: "US dollars"},
//                {code: "EUR", description: "Euro"},
//                {code: "GBP", description: "Pounds sterling"}
//            ];

            $scope.number = "";
            $scope.myvalue = false;


            $scope.getData = function ()
            {
                $http({
                    method: 'GET',
                    url: 'api/demoUser/currency/calculator/' + $scope.amount + '/' + $scope.data.repeatSelect + '/' + $scope.data1.repeatSelect1
//                    url: 'api/demoUser/currency/calculator/' + Number($('#amount').val()) + '/' + $('#from').val() + '/' + $('#to').val()

                }).then(function successCallback(response) {
                    $scope.number = response.data;

                }, function errorCallback(response) {
                    alert("Error occured");
                });
            };


            $scope.showAlert = function () {
                $scope.myvalue = true;
            };

        }); // End of controller
