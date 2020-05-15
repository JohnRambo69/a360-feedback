(function () {
    'use strict';
    angular.module('a360').service('SessionService', SessionService);
    SessionService.$inject = ['$resource', '$q'];
    function SessionService($resource, $q) {


        var sessionService = {};


        sessionService.send = function (jsonSession) {
            var sessionResource = $resource('/servlet/a360/sessions/create');
            var deferred = $q.defer();
            sessionResource.save(jsonSession).$promise.then(function (data) {
                deferred.resolve(data);
            }, function (response) {
                deferred.reject(response);

            });
            return deferred.promise;

        };


        sessionService.getAllActiveQuestions = function () {
            var url = '/servlet/a360/questions/getallquestion';
            var questionResource = $resource(url);
            var deferred = $q.defer();
            questionResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };

        return sessionService;
    }


})();