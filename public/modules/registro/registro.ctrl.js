/**
 * Created by maria on 25/10/2016.
 */
(function (ng) {

    var mod = ng.module("registroModule");
    console.log("llega al ctrl del registro")

// the list controller
    mod.controller("registroCtrl", ["$scope", "$resource","$routeParams" ,function($scope, $resource,$routeParams) {
        $scope.idSensor = $routeParams.idSensor;
        $scope.fecha = false;
        var registro = $resource("/sensor/"+$routeParams.idSensor+"/registro"); // a RESTful-capable resource object

        refresh();
        function refresh(){
            $scope.registros = registro.query(); // for the list of regiones in public/html/main.html
            console.log("/sensor/"+$routeParams.idSensor+"/registro");
        }
        var date =  new Date();

        $scope.delete = function(id) {

            var delRegistro = $resource( "/registro/" + id); // a RESTful-capable resource object
            delRegistro.delete();
            // Find index of the user
            var index = $scope.pozos.indexOf(id);
            // Remove user from array
            $scope.registros.splice(index, 1);

            console.log( "/registro/" + id);
        };
        $scope.buscarFecha = function(from, to) {
            $scope.fecha = true;
            console.log("/registro/porFecha/?from="+from+"&to="+to);
            var buscarFecha = $resource( "/registro/porFecha/?from="+from+"&to="+to); // a RESTful-capable resource object
            $scope.registrosF = buscarFecha.query();
            // Find index of the user

            // console.log( "/registro/" + id);
        };

        $scope.save = function() {
            var crearRegistro = $resource("/sensor/"+$routeParams.idSensor+"/registro"); // a RESTful-capable resource object
            crearRegistro.save($scope.sensor).$promise.then(function() { $scope.go('/sensor/'+$routeParams.id+'/registro'); });
            // $scope.pozo comes from  crearPozo in public/modules/pozo/crearSensor.html
            // go back to public/html/main.html
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