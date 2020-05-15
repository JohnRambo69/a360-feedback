(function () {
    'use strict';

    angular
        .module('a360')
        .controller('AdminController', AdminController);

    AdminController.$inject = ['UserService', '$rootScope', '$scope','AuthenticationService', '$location', '$log', 'SmtpService'];
    function AdminController(UserService, $rootScope, $scope,AuthenticationService, $location, $log, SmtpService) {

        function init() {
            $scope.user = null;
            $scope.allUsers = [];
            $scope.user1 = $rootScope.globals.currentUser.userName;

            $scope.dataLoading = false;

            $scope.smtp = [{
                smtp: "",
                port: "",
                senderEmail: "",
                senderPassword: ""
            }]

            $scope.smtpStatus = false;
            $scope.smtpResponse = "response"
            $scope.resetStatus = false;
            $scope.checkSmtpStatus = "";

        }

        $scope.smtpJson = [];

        $scope.createSmtpJson = function(){
            $scope.smtpJson = {
                smtp: $scope.smtp.smtp,
                port: $scope.smtp.port,
                senderEmail: $scope.smtp.senderEmail,
                senderPassword: $scope.smtp.senderPassword
            }

        }

        $scope.clearForm = function(){
            $scope.smtp = [];
        }

        init();
/*
        $scope.oneAtATime = true;


        $scope.status = {
            isCustomHeaderOpen: false,
            isFirstOpen: true,
            isFirstDisabled: false
        };
*/


        function initController() {
            loadCurrentUser();
            loadAllUsers();
        }

        initController();

        $scope.grantUserToAdmin = function(user,role){
            UserService.update(user, role).then(function (data) {
                loadAllUsers();
            }, function (response) {
            });

        }

        $scope.grantUserToDesigner = function(user,role){
            UserService.update(user, role).then(function (data) {
                loadAllUsers();
            }, function (response) {
            });
        }

        $scope.showElement = function () {

            if (AuthenticationService.userHasPermission(["admin"])) {
                return true;
            } else {
                return false;
            }

        }

        $scope.changeSmtp = function () {
            SmtpService.changeSmtp( $scope.smtpJson).then(function (data) {

               // $scope.smtpStatus = data;
            }, function (response) {
                //$scope.showSendSessionLoader = false;
                // !!!!!!!!!!!!!!!
               // $scope.smtpResponse = response;
            });
        }

        $scope.resetSmtp = function(){
            SmtpService.resetSmtp().then(function (data) {
                $scope.resetStatus = true;
            }, function (response) {
                //$scope.showSendSessionLoader = false;
                // !!!!!!!!!!!!!!!
                $scope.smtpResponse = response;
            });

        }

        $scope.testSmtp = function (){
            SmtpService.checkStatus().then(function (data) {
                $scope.checkSmtpStatus = "Success";
            }, function (response) {
                $scope.checkSmtpStatus = "Fail";
                // !!!!!!!!!!!!!!!
              //  $scope.checkSmtpStatus = response;
            });

        }


        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.username)
                .then(function (user) {
                    $scope.user = user;
                });
        }

        function loadAllUsers() {
            UserService.getAllUsers()
                .then(function (users) {
                    $scope.allUsers = users;
                });
        }

        $scope.deleteUser = function (userName) {
            UserService.delete(userName)
                .then(function () {
                    loadAllUsers();
                });
        }
        $scope.logOut = function () {
            AuthenticationService.LogOut();
        }

        $scope.redirectToSession = function () {
            $location.path('/session');
        }

        $scope.redirectToRegister = function () {
            $location.path('/register');
        }
        $scope.redirectToAdmin = function () {
            $location.path('/admin');
        }


        $scope.status = {
            isopen: false
        };

        $scope.toggled = function (open) {
            $log.log('Dropdown is now: ', open);
        };

        $scope.toggleDropdown = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.status.isopen = !$scope.status.isopen;
        };

      //  $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));

    }

})();