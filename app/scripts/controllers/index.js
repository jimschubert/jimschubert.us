'use strict';

angular.module('jimschubertusApp')
    .controller('IndexCtrl', ['$scope', 'RESUME', function ($scope, RESUME) {
        angular.extend($scope, RESUME);

        // these displayed honors are school-specific
        $scope.gpaDisplayHonor = function(gpa){
          if(!gpa || gpa < 3.3){
              return '';
          }

            if(gpa < 3.6) {
                return 'cum laude';
            }

            if(gpa < 3.9) {
                return 'magna cum laude';
            }

            return 'summa cum laude';
        };
    }]);
