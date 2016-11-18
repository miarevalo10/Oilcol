/**
 * Created by maria on 25/10/2016.
 */
(function (ng) {

    var mod = ng.module("sensorModule");
    console.log("llega al ctrl de sensor")

    mod.controller("sensorCtrl", ["$scope", "$resource","$routeParams" ,function($scope, $resource,$routeParams) {
        $scope.idPozo = $routeParams.idPozo;
        var sensor = $resource("/pozo/"+$routeParams.idPozo+"/sensor"); // a RESTful-capable resource object
        $scope.sensores = sensor.query(); // for the list of regiones in public/html/main.html
        console.log("/pozo/"+$routeParams.idPozo+"/sensor")

        refresh();
        function refresh(){
            $scope.sensores = sensor.query(); // for the list of regiones in public/html/main.html
            console.log("/pozo/"+$routeParams.idPozo+"/sensor")
        }

        $scope.delete = function(id) {

            var delSensor = $resource( "/sensor/" + id); // a RESTful-capable resource object
            delSensor.delete();
            // Find index of the user
            var index = $scope.sensores.indexOf(id);
            // Remove user from array
            $scope.sensores.splice(index, 1);

            console.log( "/sensor/" + id);
        };

        $scope.save = function() {
            var crearSensor = $resource("/pozo/"+$routeParams.idPozo+"/sensor"); // a RESTful-capable resource object
            crearSensor.save($scope.sensor) // $scope.pozo comes from  crearPozo in public/modules/pozo/crearSensor.html
            .$promise.then(function() { $scope.go('/pozo/'+$routeParams.idPozo+'/sensor'); }); // go back to public/html/main.html
        };
    }]);


// the edit controller
    mod.controller("editarSensorCtrl", ["$scope", "$resource", "$routeParams", "$timeout",  function($scope, $resource, $routeParams, $timeout) {
        var showSensor = $resource("/sensor/"+$routeParams.idSensor); // a RESTful-capable resource object
        if ($routeParams.idSensor) {
            // retrieve the corresponding celebrity from the database
            // $scope.region.id is now populated so the Delete button will appear in the detailForm in public/html/detail.html
            $scope.sensor= showSensor.get({idSensor: $routeParams.idSensor})
        }

        // to update a region
        $scope.save = function() {
            var updSensor = $resource("/sensor/" + $routeParams.idSensor,null,{update:{method:'PUT'}}); // a RESTful-capable resource object

            updSensor.update($scope.sensor); // $scope.celebrity comes from the detailForm in public/html/detail.html
            $timeout(function() { $scope.go('/pozo/'+$scope.sensor.pozo.id + '/sensor');
            }); // go back to public/html/main.html
        };


    }]);

})(window.angular)