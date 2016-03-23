'use strict';

angular.module('reportsApp')
    .controller('DbDetailController', function ($scope, $rootScope, $stateParams, entity, Db, Report) {
        $scope.db = entity;
        $scope.load = function (id) {
            Db.get({id: id}, function(result) {
                $scope.db = result;
            });
        };
        var unsubscribe = $rootScope.$on('reportsApp:dbUpdate', function(event, result) {
            $scope.db = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
