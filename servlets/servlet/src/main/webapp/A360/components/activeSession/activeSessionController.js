(function () {
    "use strict";
    angular.module('a360').controller('ActiveSessionController', ActiveSessionController);
    ActiveSessionController.$inject = ['$scope', 'ActiveSessionService', '$filter'];


    function ActiveSessionController($scope, ActiveSessionService, $filter) {
        function init() {
            $scope.title = " All active sessions";
            $scope.allActiveSessions = [];
            $scope.i = 1;
            $scope.temp = [];
            $scope.sessions = [];

            answersForActiveSessions ();
            getAllSession();
        };
    init();

        function answersForActiveSessions ()
        {
            ActiveSessionService.getAmountAnswers().then(function (data) {
                data.forEach(function (element) {

                    if (element.isSent == false )
                        $scope.temp.push({"amountOfAnswers" :element.amountOfAnswers});
                  //  console.log(element.amountOfAnswers);
                    // return answers


                })


            })

        }
        function concatAmountOfAnswers (){
            for(var i = 0; i < $scope.allActiveSessions.length; i++ ){
                $scope.allActiveSessions[i].amountOfAnswers = $scope.temp[i] ;
            }
        }


        function getAllSession() {
            ActiveSessionService.getAllSessions().then(function (data) {
                data.forEach(function (el) {


                    $scope.sessions.push({
                        "number": $scope.i++,
                        "sessionName": el.sessionName,
                        "isSent": el.isSent,
                        "endDate": $scope.formatDate(el.endDate),
                        "amountParticipants": el.participantList.length,
                        "amountOfAnswers": null

                    })
                    console.log(session);




                })

            }, function (response) {

            });
            concatAmountOfAnswers();
        };


        $scope.formatDate = function (date) {
            var convertedEndDate = $filter('date')(new Date(date), 'hh:mm dd MM yyyy ');
            return convertedEndDate
        };

    }



})();
