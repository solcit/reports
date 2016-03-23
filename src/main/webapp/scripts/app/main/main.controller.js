'use strict';

angular.module('reportsApp')
    .controller('MainController', function ($scope, Principal, Report) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        $scope.reports = Report.query();
    });
