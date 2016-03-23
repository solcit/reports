'use strict';

angular.module('reportsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('db', {
                parent: 'entity',
                url: '/dbs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'reportsApp.db.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/db/dbs.html',
                        controller: 'DbController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('db');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('db.detail', {
                parent: 'entity',
                url: '/db/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'reportsApp.db.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/db/db-detail.html',
                        controller: 'DbDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('db');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Db', function($stateParams, Db) {
                        return Db.get({id : $stateParams.id});
                    }]
                }
            })
            .state('db.new', {
                parent: 'db',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/db/db-dialog.html',
                        controller: 'DbDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null,
                                    userName: null,
                                    password: null,
                                    server: null,
                                    port: null,
                                    enabled: false,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('db', null, { reload: true });
                    }, function() {
                        $state.go('db');
                    })
                }]
            })
            .state('db.edit', {
                parent: 'db',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/db/db-dialog.html',
                        controller: 'DbDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Db', function(Db) {
                                return Db.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('db', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('db.delete', {
                parent: 'db',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/db/db-delete-dialog.html',
                        controller: 'DbDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Db', function(Db) {
                                return Db.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('db', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
