/**
 * Created by maria on 25/10/2016.
 */
(function(ng)
{
    var mod = ng.module("app",[

        "ngResource",
        "ngRoute",
        "campoModule",
        "pozoModule",
        "sensorModule",
        "registroModule"
    ]);

    mod.config(["$routeProvider", function($routeProvider) {
        return $routeProvider.when("/reportes/campo", {// Muestra todos los campos
            templateUrl: "/views/modules/campo/campo",
            controller: "campoCtrl"
         }).when("/campo/create", {//Crea un campo
            templateUrl: "/views/modules/campo/crearCampo",
            controller: "crearCampoCtrl"
        }).when("/campo/:idCampo/update", {//edita un campo
            templateUrl: "/views/modules/campo/crearCampo",
            controller: "editarCampoCtrl"
        }).when("/campo/:idCampo/pozo/create", { //crea un pozo, dado un campo
            templateUrl: "/views/modules/pozo/crearPozo",
            controller: "crearPozoCtrl"
        }).when("/campo/:idCampo/pozo", { //pozos de un campo
            templateUrl: "/views/modules/pozo/pozo",
            controller: "pozoCtrl"
        }).when("/pozo/:idPozo/update", {//edita un pozo
            templateUrl: "/views/modules/pozo/crearPozo",
            controller: "editarPozoCtrl"
        }).when("/pozo/:idPozo/sensor", { //sensores de un pozo
            templateUrl: "/views/modules/sensor/sensor",
            controller: "sensorCtrl"
        }).when("/pozo/:idPozo/sensor/create", { //crea un sensor, dado un pozo
            templateUrl: "/views/modules/sensor/crearSensor",
            controller: "crearSensorCtrl"
        }).when("/sensor/:idSensor/registro", { //registros de un sensor
            templateUrl: "/views/modules/registro/registro",
            controller: "registroCtrl"
        }).when("/sensor/:idSensor/registro/create", { //crea un registro, dado un sensor
            templateUrl: "/views/modules/registro/crearRegistro",
            controller: "crearSensorCtrl"
        }).when("/sensor/:idSensor/update", {//edita un sensor
            templateUrl: "/views/modules/sensor/crearSensor",
            controller: "editarSensorCtrl"
        });

        // .when("/inicio", {
        //         templateUrl:"/views/inicio"
        //     }).otherwise({
        //     redirectTo: "/inicio"
        // });
    }
    ]).config([
        "$locationProvider", function($locationProvider) {
            return $locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            }).hashPrefix("!"); // enable the new HTML5 routing and history API
            // return $locationProvider.html5Mode(true).hashPrefix("!"); // enable the new HTML5 routing and history API
        }]);

    // the global controller
    mod.controller("appCtrl", ["$scope", "$location", function($scope, $location) {
        // the very sweet go function is inherited by all other controllers
        $scope.go = function (path) {
            $location.path(path);
        };
    }]);
})(window.angular);
