'use strict';

angular.module('reportsApp')
	.controller('DbDeleteController', function($scope, $uibModalInstance, entity, Db) {

        $scope.db = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Db.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
