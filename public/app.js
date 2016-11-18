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
            templateUrl: "/modules/campo/campo.html",
            controller: "campoCtrl"
         }).when("/campo/create", {//Crea un campo
            templateUrl: "/modules/campo/crearCampo.html",
            controller: "campoCtrl"
        }).when("/campo/:idCampo/update", {//edita un campo
            templateUrl: "/modules/campo/crearCampo.html",
            controller: "editarCampoCtrl"
        }).when("/campo/:idCampo/pozo/create", { //crea un pozo, dado un campo
            templateUrl: "/modules/pozo/crearPozo.html",
            controller: "pozoCtrl"
        }).when("/campo/:idCampo/pozo", { //pozos de un campo
            templateUrl: "/modules/pozo/pozo.html",
            controller: "pozoCtrl"
        }).when("/pozo/:idPozo/update", {//edita un pozo
            templateUrl: "/modules/pozo/crearPozo.html",
            controller: "editarPozoCtrl"
        }).when("/pozo/:idPozo/sensor", { //sensores de un pozo
            templateUrl: "/modules/sensor/sensor.html",
            controller: "sensorCtrl"
        }).when("/pozo/:idPozo/sensor/create", { //crea un sensor, dado un pozo
            templateUrl: "/modules/sensor/crearSensor.html",
            controller: "sensorCtrl"
        }).when("/sensor/:idSensor/registro", { //registros de un sensor
            templateUrl: "/modules/registro/registro.html",
            controller: "registroCtrl"
        }).when("/sensor/:idSensor/registro/create", { //crea un registro, dado un sensor
            templateUrl: "/modules/registro/crearRegistro.html",
            controller: "registroCtrl"
        }).when("/sensor/:idSensor/update", {//edita un sensor
            templateUrl: "/modules/sensor/crearSensor.html",
            controller: "editarSensorCtrl"
        });

        // .when("/inicio", {
        //         templateUrl:"/views/inicio"
        //     }).otherwise({
        //     redirectTo: "/inicio"
        // });
    }
    ]);

    // the global controller
    mod.controller("appCtrl", ["$scope", "$location", function($scope, $location) {
        // the very sweet go function is inherited by all other controllers
        $scope.go = function (path) {
            $location.path(path);
        };
    }]);
})(window.angular);
