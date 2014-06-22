'use strict';

angular.module('jimschubertusApp')
    .controller('MainCtrl', ['$scope', '$location', 'RESUME', function ($scope, $location, RESUME) {
        angular.extend($scope, RESUME);

        var origin = [$location.host()];
        if($location.port()){
            origin.push(':');
            origin.push($location.port());
        }
        $scope.origin = origin.join('');
    }]);
