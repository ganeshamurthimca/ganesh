'use strict';
davosERP.factory('chatPopulateService', [ '$http', '$q', function($http, $q) {

	var _chatPopulateService = {
		populate : populate
	};
	return _chatPopulateService;

	function populate() {
		var _chats = [];
		for (var i = 0; i < 5; i++) {
			var _chat = {
				id : i,
				firstName : 'Vigneshwaran',
				lastName : 'B ' + i,
				profilepic : 'img/profile-pics/1.jpg',
				availability : 'Available'
			};
			_chats.push(_chat);
		}
		return _chats;
	}
} ]);

davosERP.service('messageService', [ '$resource', function($resource) {
	this.getMessage = function(img, user, text) {
		var gmList = {
			"list" : [ {
				"img" : "1.jpg",
				"user" : "Vigneshwaran B",
				"text" : "Hello World"
			} ]
		};

		return gmList;
	}
} ])
// =========================================================================
// Malihu Scroll - Custom Scroll bars
// =========================================================================
.service('scrollService', function() {
	var ss = {};
	ss.malihuScroll = function scrollBar(selector, theme, mousewheelaxis) {
		$(selector).mCustomScrollbar({
			theme : theme,
			scrollInertia : 100,
			axis : 'yx',
			mouseWheel : {
				enable : true,
				axis : mousewheelaxis,
				preventDefault : true
			}
		});
	}

	return ss;
})