(function () {
    "use strict";
    angular.module('a360',).controller('SessionController', SessionController);
    SessionController.$inject = ['$scope', 'SessionService', '$filter', 'AuthenticationService', '$location', '$window'];


    function SessionController($scope, SessionService, $filter, AuthenticationService, $location, $window) {


        function init()  {
            $scope.title = "Create - 360 Feedback Session";
            $scope.participants = [];
            $scope.sessionForm = "";
            $scope.sessionName = "";
            $scope.inputEmail = "";
            $scope.showAlertPositive = false;
            $scope.showAlertError = false;
            $scope.endDate = "";
            $scope.showAlert = "";
            $scope.emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            $scope.showSendSessionLoader = false;
            $scope.jsonExcelEmails = [];

            $scope.questions = [];
            $scope.questionTextArea = null;
            $scope.questionRadioArea = "";
            $scope.jsonSession = [];
            $scope.switchStatus = true;
            $scope.questionsToSend = [];
            $scope.radioQuestionConcat = "";
            $scope.questionType = "TEXT";
            $scope.showQuestionEditor = false;
            $scope.showRadioQuestion = false;
            $scope.showQuestions = false;
            $scope.showQuestionsButton = "Show questions"
            $scope.showX = false;
            $scope.showXInput = "hidden";
            $scope.answer1 ="";
            $scope.showMenu = false;
            $scope.showMenuButton = "Show Menu"
            getAllActiveQuestions();

        }
        init();

        $scope.logOut = function(){
         AuthenticationService.LogOut();
        }

        $scope.showHideXInput = function(){
            if($scope.showXInput === "hidden"){
                $scope.showXInput = "visible";
            }
            else{
                $scope.showXInput = "hidden";
            }
        }

        $scope.showHideMenu = function(){
            if($scope.showMenu === false){
                $scope.showMenu = true;
                $scope.showMenuButton = "Hide Menu"

            }
            else{
                $scope.showMenu = false;
                $scope.showMenuButton = "Show Menu"
            }
        }

        $scope.showHideXButton = function(){
            if($scope.showX === false){
                $scope.showX = true;
            }
            else{
                $scope.showX = false;
            }
        }

        $scope.showHideQuestions = function(){
            if($scope.showQuestions === false){
                $scope.showQuestions = true;
                $scope.showQuestionsButton = "Hide questions"

            }
            else{
                $scope.showQuestions = false;
                $scope.showQuestionsButton = "Show questions"
            }
        }

        $scope.questionValidator = function(int){
            if(int === 0) {
                $scope.showQuestionEditor = false;
            }
            if(int === 1){
                $scope.showQuestionEditor = true;
            }
        }




        $scope.endDateOptions = {
            dateDisabled: function (data, mode) {
                var date = data.date;
                var mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            },
            formatYear: 'yy',
            maxDate: new Date(2090, 5, 22),
            minDate: addDaysToDate(new Date(), 1),
            startingDay: 1
        };

        function addDaysToDate(date, days) {
            var newDate = new Date(date);
            newDate.setDate(newDate.getDate() + days);
            return newDate

        }

        $scope.endDatePopup = {
            opened: false
        };
        $scope.openEndDatePopup = function () {
            $scope.endDatePopup.opened = true;
        };

        $scope.addParticipant = function () {
            if ($scope.inputEmail == 0 || $scope.participants.includes($scope.inputEmail)) {
                $scope.inputEmail = '';
            } else {
                $scope.participants.push({"email": $scope.inputEmail});
                $scope.inputEmail = '';
            }


        };
        $scope.removeParticipant = function ($index) {
            $scope.participants.splice($index, 1);
        };
        $scope.canSendSession = function () {
            return $scope.participants.length === 0 || $scope.sessionForm.sessionName.$invalid || $scope.sessionForm.endDate.$invalid
        };

        $scope.cleanFields = function () {
            $scope.sessionName = "";
            $scope.endDate = "";
            $scope.participants = [];
            $scope.inputEmail = "";
        };


        $scope.formatDate = function () {
            var convertedEndDate = $filter('date')(new Date($scope.endDate), 'yyyy-MM-dd hh:mm');
            return convertedEndDate
        };



        $scope.convertData = function () {
            var convertedData = $filter('date')(new Date($scope.endDate), 'yyyy-MM-dd hh:mm');
            return convertedData;

        }

        $scope.createSession = function () {

            convertQuestionToSend ();
            var endDateSession = $scope.convertData();
            $scope.jsonSession = ({
                "sessionName": $scope.sessionName,
                "endDate": endDateSession,
                "isFreeze" : false,
                "participantList": $scope.participants,
                "questionList": $scope.questionsToSend,

            });




            $scope.showSendSessionLoader = true;
            $scope.cleanFields();
            $scope.questions = [];
            getAllActiveQuestions();
            SessionService.send($scope.jsonSession).then(function (data) {
                $scope.showSendSessionLoader = false;

                $scope.sessionStatus = true;
            }, function (response) {
                //$scope.showSendSessionLoader = false;
                // !!!!!!!!!!!!!!!
                $scope.showSendSessionLoader = false;
                $scope.sessionStatus = false;
            });
        };


        $scope.showElement = function () {

            if (AuthenticationService.userHasPermission(["admin"])) {
                return true;
            } else {
                return false;
            }

        }
        $scope.redirectToAdminPage = function () {
            $location.path('/admin');
        }

        $scope.redirectToSessionPanel = function(){
            $location.path('/sessionPanel');
        }

        $scope.addParticipantsFromFile = function() {
            for(var i = 0; i < $scope.jsonExcelEmails.length; i++){
                $scope.participants.push({
                    "email": $scope.jsonExcelEmails[i]
                } )
            }
            $scope.jsonExcelEmails = [];
        }



        ////////////////////////////// EXCEL TO JSON



        var file = document.getElementById('docpicker')
        var viewer = document.getElementById('dataviewer')
        file.addEventListener('change', importFile);

        function importFile(evt) {
            var f = evt.target.files[0];

            if (f) {
                var r = new FileReader();
                r.onload = e => {
                    var contents = processExcel(e.target.result);
                    console.log(contents)

                }
                r.readAsBinaryString(f);
            } else {
                console.log("Failed to load file");
            }
        }

        function processExcel(data) {
            var workbook = XLSX.read(data, {
                type: 'binary'
            });

            var firstSheet = workbook.SheetNames[0];
            var data = to_json(workbook);
            console.log(workbook);
            var result = [];

 /*           workbook.Strings.forEach(function (data){
                result = ({
                    email: data.t
                })
                $scope.jsonExcelEmails.push(result);

            });*/
            //console.log(data)
          //  $scope.jsonExcelEmails = data;
            return data

        };



        function to_json(workbook) {
            var result = [];
            workbook.Strings.forEach(function(sheetName) {
                result.push(sheetName.t);
            });
            //console.log(result);
            $scope.jsonExcelEmails = result;
            console.log($scope.jsonExcelEmails);
            $.scope.addParticipantsFromFile()
        };

        ///////////////////// EDIT QUESTION //////////////////////////////////////////////


        $scope.deleteQuestion = function (index) {
            $scope.questions.splice(index, 1)
        }

        function convertQuestionToSend () {
            $scope.questions.forEach(function (data) {
                $scope.questionsToSend.push({
                    "is_active" : false,
                    "question_text": data.question_text,
                    "question_type": data.question_type,
                    "default_answers": data.default_answers
                })
            })
        }


        $scope.setQuestionType = function(int){
            if(int === 0){
                $scope.questionType = "TEXT";
            }
            if(int === 1){
                $scope.questionType = "RADIO";
            }
        }

        $scope.insertQuestion = function (radioText) {
            $scope.questions.push({
                "is_active": "false",
                "question_text": radioText,
                "question_type": $scope.questionType,
                "default_answers" : $scope.radioQuestionConcat})
        }

        $scope.addRadioQuestion = function(text){
            if($scope.radioQuestionConcat === "") {
                $scope.radioQuestionConcat = $scope.radioQuestionConcat + text
            }else{
                $scope.radioQuestionConcat = $scope.radioQuestionConcat + ";" +  text
            }

        }

        $scope.clearConcatAnswer = function (){
            $scope.radioQuestionConcat = "";

        }

        $scope.resetToDefaultQuestions = function (){
            $scope.questions = [];
            getAllActiveQuestions();
        }

        function getAllActiveQuestions() {
            SessionService.getAllActiveQuestions().then(function (data) {

                data.forEach(function (resp) {
                    $scope.questions.push({
                        question_id: resp.id,
                        question_text: resp.question_text,
                        question_type: resp.question_type,
                        default_answers: resp.default_answers,
                    })
                }, function (response) {

                    alert('error on questions get request');
                });

            })}







        ///////////////////////////////////////////////////////////

    }


})();
