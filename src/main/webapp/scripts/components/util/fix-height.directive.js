'use strict';

angular.module('reportsApp')
	.directive('fixHeight', function ($window) {
		return {
			restrict: 'A',
			link: function (scope, iElement, iAttrs) {
				//Get window height and the wrapper height
		      	var neg = angular.element(iElement).find('.main-header').outerHeight() + angular.element(iElement).find('.main-footer').outerHeight();
		      	var window_height = $window.innerHeight;
		      	var sidebar_height = angular.element(iElement).find(".sidebar").height();
		      	//Set the min-height of the content and sidebar based on the
		      	//the height of the document.
		      	if ($("body").hasClass("fixed")) {
		        	angular.element(iElement).find(".content-wrapper, .right-side").css('min-height', window_height - angular.element(iElement).find('.main-footer').outerHeight());
		      	} else {
		        	var postSetWidth;
		        	if (window_height >= sidebar_height) {
		          		angular.element(iElement).find(".content-wrapper").css('min-height', window_height - neg);
		          		postSetWidth = window_height - neg;
		        	} else {
		          		angular.element(iElement).find(".content-wrapper").css('min-height', sidebar_height);
		          		postSetWidth = sidebar_height;
		        	}

		        	//Fix for the control sidebar height
		        	// var controlSidebar = $($.AdminLTE.options.controlSidebarOptions.selector);
		        	// if (typeof controlSidebar !== "undefined") {
		         //  		if (controlSidebar.height() > postSetWidth)
		         //    		angular.element(iElement).find(".content-wrapper, .right-side").css('min-height', controlSidebar.height());
		        	// }

		      	}
			}
		};
	});