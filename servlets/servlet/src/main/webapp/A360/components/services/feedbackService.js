(function () {
    'use strict';
    angular.module('a360').service('FeedbackService', FeedbackService);
    FeedbackService.$inject = ['$resource', '$q'];

    function FeedbackService($resource, $q) {
        var feedbackService = {};

        feedbackService.getQuestions = function (sessionId) {
            var url = '/servlet/a360/questions/' + sessionId;
            var feedbackQuestionsResource = $resource(url);

            var deferred = $q.defer();
            feedbackQuestionsResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };



        feedbackService.getParticipantUID = function (UId) {
            var url = '/servlet/a360/participants/' + UId;
            var feedbackParticipantResource = $resource(url);
            var deferred = $q.defer();
            feedbackParticipantResource.get().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };



            feedbackService.sendAnswers = function(jsonAnswers) {
                var url = '/servlet/a360/answers/post/';
                var feedbackParticipantResource = $resource(url);
                var deferred = $q.defer();
                feedbackParticipantResource.save(jsonAnswers).$promise.then(function(data) {
                    deferred.resolve(data);
                }, function(response) {
                    deferred.reject(response);

                });
                return deferred.promise;

            };


        return feedbackService;
    }
    })();