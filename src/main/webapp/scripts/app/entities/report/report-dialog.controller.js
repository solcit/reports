'use strict';

angular.module('reportsApp').controller('ReportDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Report', 'Db',
        function($scope, $stateParams, $uibModalInstance, entity, Report, Db) {

        $scope.report = entity;
        $scope.dbs = Db.query();
        $scope.load = function(id) {
            Report.get({id : id}, function(result) {
                $scope.report = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('reportsApp:reportUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.report.id != null) {
                Report.update($scope.report, onSaveSuccess, onSaveError);
            } else {
                Report.save($scope.report, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
