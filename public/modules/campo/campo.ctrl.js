/**
 * Created by maria on 25/10/2016.
 */
(function (ng) {

    var mod = ng.module("campoModule");
    // console.log("llega al ctrl")

// the list controller
    mod.controller("campoCtrl", ["$scope", "$resource", function($scope, $resource) {
        var campos = $resource("/campos"); // a RESTful-capable resource object
        $scope.campos = campo.query(); // for the list of regiones in public/html/main.html
    }]);

// the create controller
    mod.controller("campoCreateCtrl", ["$scope", "$resource", "$timeout",  function($scope, $resource, $timeout) {

        $scope.save = function() {
            var CreateCampo = $resource("/campos"); // a RESTful-capable resource object
            CreateCampo.save($scope.campo); // $scope.region comes from the detailForm in public/html/detail.html
            $timeout(function() { $scope.go('/campos'); }); // go back to public/html/main.html
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
//         // to delete a region
//         $scope.delete = function() {
//             var DeleteRegion = $resource( apiUrl +"/regiones/" + $routeParams.id); // a RESTful-capable resource object
//             DeleteRegion.delete();
//             $timeout(function() { $scope.go('/region'); }); // go back to public/html/main.html
//         };
//     }]);

})(window.angular)