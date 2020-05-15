(function () {
    'use strict';

    angular
        .module('a360')
        .factory('UserService', UserService);

    UserService.$inject = ['$http','$resource', '$q'];
    function UserService($http, $resource, $q) {
        var service = {};

        service.GetByUsername = GetByUsername;



        service.getAllUsers = function() {
            var userResource = $resource('/servlet/a360/users/getallusers');
            var deferred = $q.defer();
            userResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };


        function GetByUsername(username) {
            return $http.get('/servlet/a360/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        service.addUser = function(userJson) {
            var sessionResource = $resource('/servlet/a360/users/adduser/');
            var deferred = $q.defer();
            sessionResource.save(userJson).$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };

        service.delete = function(userName) {
            var sessionResource = $resource('/servlet/a360/users/delete/' + userName);
            var deferred = $q.defer();
            sessionResource.save(userName).$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };


        service.update = function(userName, role) {
            var sessionResource = $resource('/servlet/a360/users/updateUser/' + userName + '/' + role);
            var deferred = $q.defer();
            sessionResource.save(userName, role).$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }


        return service;
    }

})();