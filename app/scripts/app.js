'use strict';

angular.module('jimschubertusApp', [ 'ngSanitize', 'ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller : 'IndexCtrl'
            })
            .when('/about', {
                templateUrl: 'views/about.html',
                controller : 'IndexCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });

angular.element(document).ready(
    function () {
        var injector = angular.injector(['ng']);
        var $http = injector.get('$http');
        $http.get('scripts/resume.json').then(function (response) {
            angular.module('jimschubertusApp').constant('RESUME', response.data);
            angular.bootstrap(document, ['jimschubertusApp']);
        });
    }
);
