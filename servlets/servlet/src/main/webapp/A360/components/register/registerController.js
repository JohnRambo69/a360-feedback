(function () {
    'use strict';

    angular
        .module('a360')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$scope'];
    function RegisterController(UserService, $location, $rootScope, FlashService, $scope) {
        var vm = this;
        $scope.userName = "";
        $scope.password = "";
        $scope.role = "";
        $scope.userJson = "";
        $scope.dataLoading = false;

        $scope.createUserJson = function (){
            $scope.userJson = {
                "userName" : $scope.userName,
                "password" : $scope.password,
                "role" : $scope.role
            }
        }

        $scope.clearForm = function(){
            $scope.userName = "";
            $scope.password = "";
            $scope.role = "";
            $scope.userJson = "";
        }

        $scope.registerUser = function() {
           vm.dataLoading = true;
            UserService.addUser($scope.userJson)
                .then(function (response) {
                    $location.path('/admin');
                    if (response.success) {
                        FlashService.Success('Registration successful', true);

                    } else {
                        FlashService.Error(response.message);
                       vm.dataLoading = false;
                    }
                });
        }
    }

})();