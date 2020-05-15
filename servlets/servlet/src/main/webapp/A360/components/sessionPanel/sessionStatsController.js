(function () {
    "use strict";
    angular.module('a360').controller('SessionStatsController', SessionStatsController);
    SessionStatsController.$inject = ['$location', '$window', '$scope', '$routeParams', 'SessionStatsService', 'AuthenticationService'];

    function SessionStatsController($location, $window, $scope, $routeParams, SessionStatsService, AuthenticationService) {
    function init(){
        $scope.sessions = [];
        $scope.sessionId = "";
        $scope.questionsBySessionId = [];
        $scope.sessionTableDetailsVisible = false;
        $scope.participantsBySessionId = [];
        $scope.buttonVisible= false;



        getAllSessions();
    }

    init();
        $scope.freezeUnfreeze = function(id, value){
            SessionStatsService.freeze(id,value).then(function (data) {
                getAllSessions();
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


        $scope.backToSession = function () {
            $location.path('/session');
        }

        function getAllSessions () {
            SessionStatsService.getAllSessions().then(function (data) {

                $scope.sessions = data;
            }, function (response) {
                alert('No sessions to review !');
            });

        }

        $scope.setSessionId = function (data){
            $scope.sessionId = data;
        }

        $scope.getQuestionsBySessionId = function(){
            $scope.questionsBySessionId = $scope.sessions[$scope.sessionId].questionList;
        }

        $scope.getParticipantsBySessionId = function(){
            $scope.participantsBySessionId = $scope.sessions[$scope.sessionId].participantList;
        }

        $scope.setSessionTableVisible = function (){
            if( $scope.buttonVisible === false){
                $scope.buttonVisible = true;
            }else{
                $scope.buttonVisible = false;
            }
        }


        $scope.setButtonVisible = function (){
            if( $scope.sessionTableDetailsVisible === false){
                $scope.sessionTableDetailsVisible = true;
            }else{
                $scope.sessionTableDetailsVisible = false;
            }
        }

    }

})();