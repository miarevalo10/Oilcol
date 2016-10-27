/**
 * Created by maria on 25/10/2016.
 */
(function (ng) {

    var mod = ng.module("campoModule");
    console.log("llega al ctrl")

// the list controller
    mod.controller("campoCtrl", ["$scope", "$resource", function($scope, $resource) {
        var campos = $resource("/campo"); // a RESTful-capable resource object
        $scope.campos = campos.query(); // for the list of regiones in public/html/main.html

        $scope.delete = function(id) {

            var delCampo = $resource( "/campo/" + id); // a RESTful-capable resource object
            delCampo.delete();
            // $scope.go('reportes/campo');  // go back to public/html/main.html
            // Find index of the user
            var index = $scope.campos.indexOf(id);
            // Remove user from array
            $scope.campos.splice(index, 1);

            console.log("Se eliminó el campo " + id );
        };
    }]);

// the create controller
    mod.controller("crearCampoCtrl", ["$scope", "$resource", "$timeout",  function($scope, $resource, $timeout) {

        $scope.save = function() {
            var createCampo = $resource("/campo"); // a RESTful-capable resource object
            createCampo.save($scope.campo); // $scope.region comes from the detailForm in public/html/detail.html
            $timeout(function() { $scope.go('reportes/campo'); }); // go back to public/html/main.html
        };
    }]);

// the edit controller
//      mod.controller("regionEditCtrl", ["$scope", "$resource", "$routeParams", "$timeout", "apiUrl", function($scope, $resource, $routeParams, $timeout) {
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
         // to delete a region
         // $scope.delete = function() {
         //     var delCampo = $resource( "/campo/" + $routeParams.id); // a RESTful-capable resource object
         //    delCampo.delete();
         //     $timeout(function() { $scope.go('/reportes/campo'); }); // go back to public/html/main.html
         //  };
     // }]);

})(window.angular)