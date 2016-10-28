/**
 * Created by maria on 25/10/2016.
 */
(function (ng) {

    var mod = ng.module("pozoModule");
    console.log("llega al ctrl de pozo")

// the list controller
    mod.controller("pozoCtrl", ["$scope", "$resource","$routeParams" ,function($scope, $resource,$routeParams) {
        var pozos = $resource("/campo/"+$routeParams.id+"/pozo"); // a RESTful-capable resource object
        $scope.pozos = pozos.query(); // for the list of regiones in public/html/main.html
        console.log("/campo/"+$routeParams.id+"/pozo")

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
            var crearPozo = $resource("/campo/"+$routeParams.id+"/pozo"); // a RESTful-capable resource object
            crearPozo.save($scope.pozo); // $scope.campo comes from the detailForm in public/html/detail.html
            $timeout(function() { $scope.go('/campo'); }); // go back to public/html/main.html
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