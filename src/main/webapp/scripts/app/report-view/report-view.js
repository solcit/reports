'use strict';

angular.module('reportsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('report-view', {
                parent: 'site',
                url: '/report-view/{id:[0-9]+}',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/report-view/report-view-detail.html',
                        controller: 'ReportViewDetailController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('main');
                        return $translate.refresh();
                    }]
                }
            });
    });
