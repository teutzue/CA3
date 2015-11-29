'use strict';

angular.module('myApp.view3_company_info', ['ngRoute', 'ngAnimate', 'ui.bootstrap'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3_company_info', {
                    templateUrl: 'app/view3_company_info/view3_company_info.html',
                    controller: 'view3_company_infoCtrl'
                });
            }])

        .controller('view3_company_infoCtrl', function ($http, $scope) {



            $scope.company = {};
            $scope.productionunits = [];
            $scope.myvalue = false;
            $scope.property = {};

            $scope.findCompany = function ()
            {
                $http({
                    method: 'GET',
                    url: 'http://cvrapi.dk/api?search=' + $('#find').val() + '&country=dk'

                }).then(function successCallback(response) {
                    $scope.company = response.data;
                    $scope.productionunits = response.data.productionunits;
                    console.log($scope.vat);
                    console.log(response);
                    console.log($scope.company);
                    console.log($scope.productionunits);


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


