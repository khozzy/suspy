var suspyApp = angular.module('suspyApp',['duScroll',
        'ui.bootstrap','cfp.hotkeys', 'ngResource'])
    .value('duScrollDuration', 300)
    .value('duScrollOffset', 70)
    .config(function($locationProvider) {
        $locationProvider.html5Mode(true);
    });