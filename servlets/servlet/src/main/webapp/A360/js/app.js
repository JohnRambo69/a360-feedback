angular.module('a360', ['ngRoute', 'ngResource', 'ngAnimate', 'ngSanitize','ui.bootstrap', 'ngRoute', 'ngCookies'], function($routeProvider) {
        $routeProvider.when('/session', {
            templateUrl: 'components/session/createSession.html',
            controller: 'SessionController',
            requiresAuthentication: true
        }).when('/feedback/:UId', {
            templateUrl: 'components/feedback/feedback.html',
            controller: 'FeedbackController'
        }).when('/feedbackAfterSend', {templateUrl: 'components/feedback/feedbackAfterSend.html'}).when('/activeSession', {
            templateUrl: 'components/activeSession/activeSession.html',
            controller: 'ActiveSessionController'
        }).when('/admin', {
            templateUrl: 'components/admin/adminPage.html',
            controller: 'AdminController',
            requiresAuthentication: true,
           permissions: ["admin"]
        }).when('/login', {
            templateUrl: 'components/login/login.html',
            controller: 'LoginController',
            //controllerAs: 'vm'
        })
            .when('/register', {
                templateUrl: 'components/register/register.html',
                controller: 'RegisterController',
                requiresAuthentication: true,
                permissions: ["admin"]
               // controllerAs: 'vm'
            })
            .when('/sessionPanel', {
                templateUrl: 'components/sessionPanel/sessionStats.html',
                controller: 'SessionStatsController',
                requiresAuthentication: true
            })
            .when('/feedbackResponse', {
                templateUrl: 'components/feedback/feedbackResponse.html'
            })
            .otherwise({redirectTo: '/login'});



    }

).run(run);

function run($rootScope, $location, $cookies, $http) {

    // keep user logged in after page refresh
    $rootScope.globals = $cookies.getObject('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
    }


    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }

    });
}








