(function () {
    'use strict';
    angular.module('a360').service('SmtpService', SmtpService);
    SmtpService.$inject = ['$resource', '$q'];
    function SmtpService($resource, $q) {


        var smtpService = {};


        smtpService.changeSmtp = function(smtpJson) {
            var sessionResource = $resource('/servlet/a360/smtp/');
            var deferred = $q.defer();
            sessionResource.save(smtpJson).$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };


        smtpService.resetSmtp = function() {
            var sessionResource = $resource('/servlet/a360/smtp/reset/');
            var deferred = $q.defer();
            sessionResource.save().$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };

        smtpService.checkStatus = function () {
            var url = '/servlet/a360/smtp/checkSmtp';
            var questionResource = $resource(url);
            var deferred = $q.defer();
            questionResource.get().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };



        return smtpService;
    }


})();