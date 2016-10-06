'use strict';
davosERP
		.controller(
				'anthemCtrl',['$timeout','$state','$scope','growlService','RoleMenuService','UserService',
				function($timeout, $state, $scope, growlService,RoleMenuService,UserService) {
					$scope.userImage = 'img/profile-pics/4.jpg';
					$scope.currentUser = '';
					//Welcome Message
					currentUser();
					$scope.menuList = [];
					populateMenu();
					// Detact Mobile Browser
					if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i
							.test(navigator.userAgent)) {
						angular.element('html').addClass('ismobile');
					}

					// By default Sidbars are hidden in boxed layout and in wide
					// layout only the right sidebar is hidden.
					this.sidebarToggle = {
						left : false,
						right : false
					}

					// By default template has a boxed layout
					this.layoutType = localStorage.getItem('ma-layout-status');
					
					// For Mainmenu Active Class
					this.$state = $state;

					// Close sidebar on click
					this.sidebarStat = function(event) {
						if (!angular.element(event.target).parent().hasClass(
								'active')) {
							this.sidebarToggle.left = false;
						}
					}

					// Listview Search (Check listview pages)
					this.listviewSearchStat = false;

					this.lvSearch = function() {
						this.listviewSearchStat = true;
					}

					// Listview menu toggle in small screens
					this.lvMenuStat = false;

					// Blog
					this.wallCommenting = [];

					this.wallImage = false;
					this.wallVideo = false;
					this.wallLink = false;

					// Skin Switch
					this.currentSkin = 'blue';

					this.skinList = [ 'lightblue', 'bluegray', 'cyan', 'teal',
							'green', 'orange', 'blue', 'purple' ]

					this.skinSwitch = function(color) {
						this.currentSkin = color;
					}
					function populateMenu(){
						RoleMenuService.buildMenuFromUser().then(function(successResponse){
							$scope.menuList = successResponse;
						},function(errorResponse){
							console.log(errorResponse);
						});
					}
					
					function currentUser(){
						UserService.currentUser().then(function(successResponse){
							$scope.currentUser = successResponse.userName;
							growlService.growl('Welcome back ' + $scope.currentUser + '!',
							'inverse');
						},function(errorResponse){
							console.log(errorResponse);
						});
						
					}
				}])
		.controller(
				'chatPopulateController',
				[ 'chatPopulateService', '$scope',
						function(chatPopulateService, $scope) {

							var _chat = {
								id : null,
								firstName : '',
								lastName : '',
								profile_pic : '',
								availability : ''
							};
							$scope.chats = chatPopulateService.populate();
						} ])
		.controller(
				'headerControl',
				function($timeout, messageService) {

					// Top Search
					this.openSearch = function() {
						angular.element('#header').addClass('search-toggled');
						angular.element('#top-search-wrap').find('input')
								.focus();
					}

					this.closeSearch = function() {
						angular.element('#header')
								.removeClass('search-toggled');
					}

					// Get messages and notification for header
					this.img = messageService.img;
					this.user = messageService.user;
					this.user = messageService.text;

					this.messageResult = messageService.getMessage(this.img,
							this.user, this.text);

					// Clear Notification
					this.clearNotification = function($event) {
						$event.preventDefault();

						var x = angular.element($event.target).closest(
								'.listview');
						var y = x.find('.lv-item');
						var z = y.size();

						angular.element($event.target).parent().fadeOut();

						x.find('.list-group').prepend(
								'<i class="grid-loading hide-it"></i>');
						x.find('.grid-loading').fadeIn(1500);
						var w = 0;

						y.each(function() {
							var z = $(this);
							$timeout(function() {
								z.addClass('animated fadeOutRightBig').delay(
										1000).queue(function() {
									z.remove();
								});
							}, w += 150);
						})

						$timeout(
								function() {
									angular.element('#notifications').addClass(
											'empty');
								}, (z * 150) + 200);
					}

					// Clear Local Storage
					this.clearLocalStorage = function() {

						// Get confirmation, if confirmed clear the localStorage
						swal(
								{
									title : "Are you sure?",
									text : "All your saved localStorage values will be removed",
									type : "warning",
									showCancelButton : true,
									confirmButtonColor : "#F44336",
									confirmButtonText : "Yes, delete it!",
									closeOnConfirm : false
								}, function() {
									localStorage.clear();
									swal("Done!", "localStorage is cleared",
											"success");
								});

					}

					// Fullscreen View
					this.fullScreen = function() {
						// Launch
						function launchIntoFullscreen(element) {
							if (element.requestFullscreen) {
								element.requestFullscreen();
							} else if (element.mozRequestFullScreen) {
								element.mozRequestFullScreen();
							} else if (element.webkitRequestFullscreen) {
								element.webkitRequestFullscreen();
							} else if (element.msRequestFullscreen) {
								element.msRequestFullscreen();
							}
						}

						// Exit
						function exitFullscreen() {
							if (document.exitFullscreen) {
								document.exitFullscreen();
							} else if (document.mozCancelFullScreen) {
								document.mozCancelFullScreen();
							} else if (document.webkitExitFullscreen) {
								document.webkitExitFullscreen();
							}
						}

						if (exitFullscreen()) {
							launchIntoFullscreen(document.documentElement);
						} else {
							launchIntoFullscreen(document.documentElement);
						}
					}

				}).controller('animCtrl', function($timeout) {

			// Animation List
			this.attentionSeekers = [ {
				animation : 'bounce',
				target : 'attentionSeeker'
			}, {
				animation : 'flash',
				target : 'attentionSeeker'
			}, {
				animation : 'pulse',
				target : 'attentionSeeker'
			}, {
				animation : 'rubberBand',
				target : 'attentionSeeker'
			}, {
				animation : 'shake',
				target : 'attentionSeeker'
			}, {
				animation : 'swing',
				target : 'attentionSeeker'
			}, {
				animation : 'tada',
				target : 'attentionSeeker'
			}, {
				animation : 'wobble',
				target : 'attentionSeeker'
			} ]
			this.flippers = [ {
				animation : 'flip',
				target : 'flippers'
			}, {
				animation : 'flipInX',
				target : 'flippers'
			}, {
				animation : 'flipInY',
				target : 'flippers'
			}, {
				animation : 'flipOutX',
				target : 'flippers'
			}, {
				animation : 'flipOutY',
				target : 'flippers'
			} ]
			this.lightSpeed = [ {
				animation : 'lightSpeedIn',
				target : 'lightSpeed'
			}, {
				animation : 'lightSpeedOut',
				target : 'lightSpeed'
			} ]
			this.special = [ {
				animation : 'hinge',
				target : 'special'
			}, {
				animation : 'rollIn',
				target : 'special'
			}, {
				animation : 'rollOut',
				target : 'special'
			} ]
			this.bouncingEntrance = [ {
				animation : 'bounceIn',
				target : 'bouncingEntrance'
			}, {
				animation : 'bounceInDown',
				target : 'bouncingEntrance'
			}, {
				animation : 'bounceInLeft',
				target : 'bouncingEntrance'
			}, {
				animation : 'bounceInRight',
				target : 'bouncingEntrance'
			}, {
				animation : 'bounceInUp',
				target : 'bouncingEntrance'
			} ]
			this.bouncingExits = [ {
				animation : 'bounceOut',
				target : 'bouncingExits'
			}, {
				animation : 'bounceOutDown',
				target : 'bouncingExits'
			}, {
				animation : 'bounceOutLeft',
				target : 'bouncingExits'
			}, {
				animation : 'bounceOutRight',
				target : 'bouncingExits'
			}, {
				animation : 'bounceOutUp',
				target : 'bouncingExits'
			} ]
			this.rotatingEntrances = [ {
				animation : 'rotateIn',
				target : 'rotatingEntrances'
			}, {
				animation : 'rotateInDownLeft',
				target : 'rotatingEntrances'
			}, {
				animation : 'rotateInDownRight',
				target : 'rotatingEntrances'
			}, {
				animation : 'rotateInUpLeft',
				target : 'rotatingEntrances'
			}, {
				animation : 'rotateInUpRight',
				target : 'rotatingEntrances'
			} ]
			this.rotatingExits = [ {
				animation : 'rotateOut',
				target : 'rotatingExits'
			}, {
				animation : 'rotateOutDownLeft',
				target : 'rotatingExits'
			}, {
				animation : 'rotateOutDownRight',
				target : 'rotatingExits'
			}, {
				animation : 'rotateOutUpLeft',
				target : 'rotatingExits'
			}, {
				animation : 'rotateOutUpRight',
				target : 'rotatingExits'
			} ]
			this.fadeingEntrances = [ {
				animation : 'fadeIn',
				target : 'fadeingEntrances'
			}, {
				animation : 'fadeInDown',
				target : 'fadeingEntrances'
			}, {
				animation : 'fadeInDownBig',
				target : 'fadeingEntrances'
			}, {
				animation : 'fadeInLeft',
				target : 'fadeingEntrances'
			}, {
				animation : 'fadeInLeftBig',
				target : 'fadeingEntrances'
			}, {
				animation : 'fadeInRight',
				target : 'fadeingEntrances'
			}, {
				animation : 'fadeInRightBig',
				target : 'fadeingEntrances'
			}, {
				animation : 'fadeInUp',
				target : 'fadeingEntrances'
			}, {
				animation : 'fadeInBig',
				target : 'fadeingEntrances'
			} ]
			this.fadeingExits = [ {
				animation : 'fadeOut',
				target : 'fadeingExits'
			}, {
				animation : 'fadeOutDown',
				target : 'fadeingExits'
			}, {
				animation : 'fadeOutDownBig',
				target : 'fadeingExits'
			}, {
				animation : 'fadeOutLeft',
				target : 'fadeingExits'
			}, {
				animation : 'fadeOutLeftBig',
				target : 'fadeingExits'
			}, {
				animation : 'fadeOutRight',
				target : 'fadeingExits'
			}, {
				animation : 'fadeOutRightBig',
				target : 'fadeingExits'
			}, {
				animation : 'fadeOutUp',
				target : 'fadeingExits'
			}, {
				animation : 'fadeOutUpBig',
				target : 'fadeingExits'
			} ]
			this.zoomEntrances = [ {
				animation : 'zoomIn',
				target : 'zoomEntrances'
			}, {
				animation : 'zoomInDown',
				target : 'zoomEntrances'
			}, {
				animation : 'zoomInLeft',
				target : 'zoomEntrances'
			}, {
				animation : 'zoomInRight',
				target : 'zoomEntrances'
			}, {
				animation : 'zoomInUp',
				target : 'zoomEntrances'
			} ]
			this.zoomExits = [ {
				animation : 'zoomOut',
				target : 'zoomExits'
			}, {
				animation : 'zoomOutDown',
				target : 'zoomExits'
			}, {
				animation : 'zoomOutLeft',
				target : 'zoomExits'
			}, {
				animation : 'zoomOutRight',
				target : 'zoomExits'
			}, {
				animation : 'zoomOutUp',
				target : 'zoomExits'
			} ]

			// Animate
			this.ca = '';

			this.setAnimation = function(animation, target) {
				if (animation === "hinge") {
					animationDuration = 2100;
				} else {
					animationDuration = 1200;
				}

				angular.element('#' + target).addClass(animation);

				$timeout(function() {
					angular.element('#' + target).removeClass(animation);
				}, animationDuration);
			}

		});
