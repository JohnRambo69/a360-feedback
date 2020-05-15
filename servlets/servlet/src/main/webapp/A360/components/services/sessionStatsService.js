(function () {
    'use strict';
    angular.module('a360').service('SessionStatsService', SessionStatsService);
    SessionStatsService.$inject = ['$resource', '$q'];

    function SessionStatsService($resource, $q) {


        var sessionStatsService = {};

        sessionStatsService.getAllSessions = function () {
            var url = '/servlet/a360/sessions/allsessions/';
            var sessionsResource = $resource(url);

            var deferred = $q.defer();
            sessionsResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };

        sessionStatsService.freeze = function (sessionId, value) {
            var sessionResource = $resource('/servlet/a360/sessions/freeze/' + sessionId + '/' + value);
            var deferred = $q.defer();
            sessionResource.save(sessionId, value).$promise.then(function (data) {
                deferred.resolve(data);
            }, function (response) {
                deferred.reject(response);

            });
            return deferred.promise;

        };


        return sessionStatsService;
    }

})();