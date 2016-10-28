/**
 * Created by maria on 25/10/2016.
 */
(function (ng) {

    var mod = ng.module("pozoModule");
    console.log("llega al ctrl de pozo")

// the list controller
    mod.controller("pozoCtrl", ["$scope", "$resource","$routeParams" ,function($scope, $resource,$routeParams) {
        $scope.idCampo = $routeParams.idCampo;
        var pozos = $resource("/campo/"+$routeParams.idCampo+"/pozo"); // a RESTful-capable resource object
        $scope.pozos = pozos.query(); // for the list of regiones in public/html/main.html
        console.log("/campo/"+$routeParams.idCampo+"/pozo")

        $scope.delete = function(id) {

            var delPozo = $resource( "/pozo/" + id); // a RESTful-capable resource object
            delPozo.delete();
            // Find index of the user
            var index = $scope.pozos.indexOf(id);
            // Remove user from array
            $scope.pozos.splice(index, 1);

            console.log( "/pozo/" + id);
        };
    }]);

    // the create controller
    mod.controller("crearPozoCtrl", ["$scope", "$resource", "$timeout", "$routeParams",function($scope, $resource, $timeout, $routeParams) {

        $scope.save = function() {
            var crearPozo = $resource("/campo/"+$routeParams.idCampo+"/pozo"); // a RESTful-capable resource object
            crearPozo.save($scope.pozo); // $scope.pozo comes from  crearPozo in public/modules/pozo/crearSensor.html
            $timeout(function() { $scope.go('/campo/'+$routeParams.idCampo+'/pozo'); }); // go back to public/html/main.html
        };
    }]);

// the edit controller
    mod.controller("editarPozoCtrl", ["$scope", "$resource", "$routeParams", "$timeout",  function($scope, $resource, $routeParams, $timeout) {
        var showPozo = $resource("/pozo/"+$routeParams.idPozo); // a RESTful-capable resource object
        if ($routeParams.idPozo) {
            // retrieve the corresponding celebrity from the database
            // $scope.region.id is now populated so the Delete button will appear in the detailForm in public/html/detail.html
            $scope.pozo = showPozo.get({idPozo: $routeParams.idPozo})
        }

        // to update a region
        $scope.save = function() {
            var updPozo = $resource("/pozo/" + $routeParams.idPozo,null,{update:{method:'PUT'}}); // a RESTful-capable resource object

            updPozo.update($scope.pozo); // $scope.celebrity comes from the detailForm in public/html/detail.html
            $timeout(function() { $scope.go('/campo/'+$scope.pozo.campo.id + '/pozo'); }); // go back to public/html/main.html
        };


    }]);

})(window.angular)