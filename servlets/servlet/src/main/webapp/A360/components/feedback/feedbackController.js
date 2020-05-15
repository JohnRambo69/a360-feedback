

(function () {
    "use strict";
    angular.module('a360').controller('FeedbackController', FeedbackController);
    FeedbackController.$inject = ['$location','$window','$scope', '$routeParams', 'FeedbackService','SessionStatsService'];
    function FeedbackController($location, $window, $scope, $routeParams, FeedbackService, SessionStatsService) {
        function init() {

            $scope.jsonAnswers = [];
            $scope.questions = [];
            $scope.answers = [];
            $scope.participantEmail = "";
            $scope.sessionId = "";
            $scope.feedbackSendOk = "";
            $scope.participantId = "";
            $scope.dataLoading = false;
            $scope.sessions = [];
            $scope.sessionIsFreeze = false;
            getParticipantByUid();
            getAllSession();
        }
        init();

        function getAllSession() {
            SessionStatsService.getAllSessions().then(function (data) {
                data.forEach(function (resp) {
                    var session = {
                        Id: resp.id,
                        isFreeze: resp.isFreeze
                    }

                    $scope.sessions.push(session);
                }, function (response) {
                    alert('No sessions to review !');
                });


            });
        }

        $scope.checkIsSessionFreeze = function(){
            for(var i = 0; i < $scope.sessions.length; i++){
                if($scope.sessionId = $scope.sessions[i].Id){
                    $scope.sessionIsFreeze = $scope.sessions[i].isFreeze
                }
            }
        }



        function getQuestionsBySessionId(sessionId) {
            FeedbackService.getQuestions(sessionId).then(function (data) {
                console.log('Question Get success. ' + data);
                data.forEach(function (resp) {
                    var question = {
                        question_id: resp.question_id,
                        question_text: resp.question_text,
                        question_type: resp.question_type,
                        default_answers: [],
                        answer: null
                    }
                    if(resp.default_answers != null) {
                        resp.default_answers.split(';').forEach(function (resp) {
                            var answerValue = {
                                value: resp
                            }
                            question.default_answers.push(answerValue);
                        })
                    }
                    $scope.questions.push(question);
                });
                $scope.checkIsSessionFreeze();
            }, function (response) {
                alert('Question GET error.');
            });


        };



        function getParticipantByUid() {
            // FeedbackService.getParticipantUID($routeParams.uId).then(function (data) {
            FeedbackService.getParticipantUID($routeParams.UId).then(function (data) {
                console.log('GET participant by UId success ' + data.email);
                $scope.participantEmail = data.email;
                $scope.sessionId = data.sessionId;
                $scope.participantId = data.id;
                getQuestionsBySessionId($scope.sessionId);
            }, function (response) {
                alert('error on participant get request');
            });

        }


    $scope.createAnswerJson = function () {
                for (let i = 0; i < $scope.questions.length; i++) {
                    $scope.jsonAnswers.push({
                        "answerText": $scope.questions[i].answer,
                        "participantId": $scope.participantId,
                        "questionId": $scope.questions[i].question_id,
                    });
                }
                FeedbackService.sendAnswers($scope.jsonAnswers).then(function (response) {
                    console.log('Feedback send ' );

                    $scope.feedbackSendOk = response;
                }, function (response) {
                    // alert('Feedback send error.');

                });
        console.log('Feedback send ' + $scope.feedbackSendOk);
    }

    $scope.checkIsAllAnswerNotNull = function (){
            for(var i= 0; i < $scope.questions.length; i++){

                if($scope.questions[i].answer === null){
                    return true;
                }

            }
        return false;

    }

    $scope.redirectToFeedbackResponse = function(){
       $location.path ('feedbackResponse');
    }

    }

})();