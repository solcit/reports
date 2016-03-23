'use strict';

angular.module('reportsApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


