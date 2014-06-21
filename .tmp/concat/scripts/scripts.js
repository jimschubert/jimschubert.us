'use strict';
angular.module('jimschubertusApp', [
  'ngSanitize',
  'ngRoute'
]).config([
  '$routeProvider',
  function ($routeProvider) {
    $routeProvider.when('/', {
      templateUrl: 'views/main.html',
      controller: 'MainCtrl'
    }).otherwise({ redirectTo: '/' });
  }
]);
'use strict';
angular.module('jimschubertusApp').controller('MainCtrl', [
  '$scope',
  function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  }
]);