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

        refresh();
        function refresh(){
            $scope.campos = campos.query(); // for the list of regiones in public/html/main.html
        }

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
        $scope.save = function() {
            var createCampo = $resource("/campo"); // a RESTful-capable resource object
            createCampo.save($scope.campo).$promise.then(function() { $scope.go('reportes/campo'); }); // go back to public/html/main.html
        };
    }]);


// the edit controller
     mod.controller("editarCampoCtrl", ["$scope", "$resource", "$routeParams", "$timeout",  function($scope, $resource, $routeParams, $timeout) {
        var showCampo = $resource("/campo/"+$routeParams.idCampo); // a RESTful-capable resource object
        if ($routeParams.idCampo) {
            // retrieve the corresponding celebrity from the database
            // $scope.region.id is now populated so the Delete button will appear in the detailForm in public/html/detail.html
            $scope.campo = showCampo.get({idCampo: $routeParams.idCampo})
        }

        // to update a region
        $scope.save = function() {
            var updCampo = $resource("/campo/" + $routeParams.idCampo,null,{update:{method:'PUT'}}); // a RESTful-capable resource object

            updCampo.update($scope.campo); // $scope.celebrity comes from the detailForm in public/html/detail.html
            $timeout(function() { $scope.go('/reportes/campo'); }); // go back to public/html/main.html
        };


      }]);

})(window.angular)