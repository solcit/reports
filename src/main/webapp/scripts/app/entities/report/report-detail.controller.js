'use strict';

angular.module('reportsApp')
    .controller('ReportDetailController', function ($scope, $rootScope, $stateParams, entity, Report, Db) {
        $scope.report = entity;
        $scope.load = function (id) {
            Report.get({id: id}, function(result) {
                $scope.report = result;
            });
        };
        var unsubscribe = $rootScope.$on('reportsApp:reportUpdate', function(event, result) {
            $scope.report = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
