'use strict';

angular.module('reportsApp').controller('DbDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Db', 'Report',
        function($scope, $stateParams, $uibModalInstance, entity, Db, Report) {

        $scope.db = entity;
        $scope.reports = Report.query();
        $scope.load = function(id) {
            Db.get({id : id}, function(result) {
                $scope.db = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('reportsApp:dbUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.db.id != null) {
                Db.update($scope.db, onSaveSuccess, onSaveError);
            } else {
                Db.save($scope.db, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
