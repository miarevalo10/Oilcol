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

        $scope.delete = function(id) {

            var delSensor = $resource( "/sensor/" + id); // a RESTful-capable resource object
            delSensor.delete();
            // Find index of the user
            var index = $scope.sensores.indexOf(id);
            // Remove user from array
            $scope.sensores.splice(index, 1);

            console.log( "/sensor/" + id);
        };
    }]);

    // the create controller
    mod.controller("crearSensorCtrl", ["$scope", "$resource", "$timeout", "$routeParams",function($scope, $resource, $timeout, $routeParams) {

        $scope.save = function() {
            var crearSensor = $resource("/pozo/"+$routeParams.idPozo+"/sensor"); // a RESTful-capable resource object
            crearSensor.save($scope.sensor); // $scope.pozo comes from  crearPozo in public/modules/pozo/crearSensor.html
            $timeout(function() { $scope.go('/pozo/'+$routeParams.idPozo+'/sensor'); }); // go back to public/html/main.html
        };
    }]);

// the edit controller
//     mod.controller("regionEditCtrl", ["$scope", "$resource", "$routeParams", "$timeout", "apiUrl", function($scope, $resource, $routeParams, $timeout, apiUrl) {
//         var ShowRegion = $resource(apiUrl +"/regiones/:id", {id:"@id"}); // a RESTful-capable resource object
//         if ($routeParams.id) {
//             // retrieve the corresponding celebrity from the database
//             // $scope.region.id is now populated so the Delete button will appear in the detailForm in public/html/detail.html
//             $scope.region = ShowRegion.get({id: $routeParams.id});
//             $scope.dbContent = ShowRegion.get({id: $routeParams.id}); // this is used in the noChange function
//         }
//
//         // decide whether to enable or not the button Save in the detailForm in public/html/detail.html
//         $scope.noChange = function() {
//             return angular.equals($scope.region, $scope.dbContent);
//         };
//
//         // to update a region
//         $scope.save = function() {
//             var UpdateRegion = $resource(apiUrl +"/regiones/" + $routeParams.id,null,{update:{method:'PUT'}}); // a RESTful-capable resource object
//             $scope.regionSinCampos = {
//                 "id":$scope.region.id,
//                 "nombre":$scope.region.nombre,
//                 "area":$scope.region.area
//             };
//             UpdateRegion.update($scope.regionSinCampos); // $scope.celebrity comes from the detailForm in public/html/detail.html
//             $timeout(function() { $scope.go('/region'); }); // go back to public/html/main.html
//         };
//
//     }]);

})(window.angular)