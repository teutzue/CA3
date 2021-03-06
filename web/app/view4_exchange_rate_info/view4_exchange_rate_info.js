'use strict';

angular.module('myApp.view4_exchange_rate_info', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4_exchange_rate_info', {
                    templateUrl: 'app/view4_exchange_rate_info/view4_exchange_rate_info.html',
                    controller: 'view4_exchange_rate_infoCtrl'
                });
            }])

//    .controller('view4_exchange_rate_infoCtrl', function ($http, $scope) {
//        $http({
//            method: 'GET',
//            url: 'api/demouser'
//        }).then(function successCallback(res) {
//            $scope.data = res.data.message;
//        }, function errorCallback(res) {
//            $scope.error = res.status + ": " + res.data.statusText;
//        });
//
//    }
        .controller('view4_exchange_rate_infoCtrl', function ($http, $scope) {

            $scope.currency = [];

            $http({
                method: 'GET',
                url: 'api/demoUser/cur'

            }).then(function successCallback(response) {
                $scope.currency = response.data;

            }, function errorCallback(response) {
                alert("Error occured");
            });

            $scope.showAlert = function () {
                $scope.myvalue = true;
            };

        }
        );
