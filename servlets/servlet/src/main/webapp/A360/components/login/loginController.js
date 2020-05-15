(function () {
    'use strict';

    angular
        .module('a360')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$scope','$location', 'AuthenticationService', 'UserService'];
    function LoginController($scope, $location, AuthenticationService, UserService) {
        var vm = this;

        //vm.login = login;
        function init() {
            $scope.allUsers = [];
            $scope.loginError = false;
            $scope.jsonUpdateUser = [];
            $scope.jsonNewUser = [];
            $scope.responseMessage = {status: false,
                message: "Invalid username or password"};
            $scope.loginInput = "";
            $scope.username = "";
            $scope.password = "";
            $scope.dataLoading = false;
            $scope.invalidUsernameOrPassword = false;
            $scope.userExist = false;
            fillAllUsers();
        }
        init();



        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function fillAllUsers(){
            UserService.getAllUsers().then(function (data) {

                data.forEach(function (resp) {
                    $scope.allUsers.push({
                        "id": resp.id,
                        "userName" : resp.userName,
                        "password": resp.password,
                        "role" : resp.role
                    })
                }, function (response) {

                    alert('error on users get request');
                });

            })};

       $scope.checkUserIsInDatabase = function (){
          for(var i = 0; i < $scope.allUsers.length; i++){
                if($scope.allUsers[i].userName === $scope.username){
                   $scope.userExist = true;
                }
           }

        };

        $scope.login = function () {
           $scope.checkUserIsInDatabase();
            if($scope.userExist === true) {
                $scope.dataLoading = true;
            AuthenticationService.Login($scope.username, $scope.password, function (response) {
                if (response.success) {
                   // AuthenticationService.ClearCredentials();
                    AuthenticationService.SetCredentials($scope.username, $scope.password);
                    $scope.invalidUsernameOrPassword = false;
                    $location.path('/session/');
                    $scope.responseMessage = {status: false,
                        message: "Invalid username or password"};
                    console.log("Logged in");
                } else {
                    $scope.responseMessage = {status: true,
                        message: "Invalid username or password"};
                    $scope.dataLoading = false;
                }
            });

            }
            else{
                console.log("something wrong");
                $scope.username = "";
                $scope.password = "";
                $scope.invalidUsernameOrPassword = true;
                $scope.responseMessage = {status: true,
                    message: "Invalid username or password"};
            }
        };


            $scope.redirectToSession = function (){
                $location.path('/session');
    };


    }

})();