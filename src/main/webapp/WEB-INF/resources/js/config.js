davosERP
		.config(function($stateProvider, $urlRouterProvider, $validatorProvider) {
			$urlRouterProvider.otherwise("/home");

			$validatorProvider.setDefaults({
				errorElement : 'small',
				errorClass : 'help-block has-error'
			});

			$stateProvider
					.state('home', {
						url : '/home',
						templateUrl : 'home'
					})
					.state('user', {
						url : '/user',
						templateUrl : 'common'
					})
					.state(
							'user.create',
							{
								url : '/create',
								templateUrl : 'user/user-create',
								resolve : {
									loadPlugin : function($ocLazyLoad) {
										return $ocLazyLoad
												.load([{
													name : 'vendors',
													insertBefore : '#app-level-js',
													files : [
															'resources/js/controller/UserController.js',
															'resources/js/service/UserService.js']
												}])
									}
								}
							})
					.state(
							'user.list',
							{
								url : '/list',
								templateUrl : 'user/user-list',
								resolve : {
									loadPlugin : function($ocLazyLoad) {
										return $ocLazyLoad
												.load([{
													name : 'vendors',
													insertBefore : '#app-level-js',
													files : [
															'resources/js/controller/userListController.js',
															'resources/js/service/UserService.js']
												}])
									}
								}
							})
					.state(
							'user.viewHistory',
							{
								url : '/viewHistory?userId',
								templateUrl : 'user/user-history',
								resolve : {
									loadPlugin : function($ocLazyLoad) {
										return $ocLazyLoad
												.load([{
													name : 'vendors',
													insertBefore : '#app-level-js',
													files : [
															'resources/js/controller/userHistoryController.js',
															'resources/js/service/UserService.js']
												}])
									}
								}
							})
					.state('role', {
						url : '/role',
						templateUrl : 'common'
					})
					.state(
							'role.create',
							{
								url : '/create',
								templateUrl : 'role/role-create',
								resolve : {
									loadPlugin : function($ocLazyLoad) {
										return $ocLazyLoad
												.load([{
													name : 'vendors',
													insertBefore : '#app-level-js',
													files : [
															'resources/js/controller/RoleAddController.js',
															'resources/js/service/RoleService.js']
												}])
									}
								}
							})
					.state(
							'role.list',
							{
								url : '/list',
								templateUrl : 'role/role-list',
								resolve : {
									loadPlugin : function($ocLazyLoad) {
										return $ocLazyLoad
												.load([{
													name : 'vendors',
													insertBefore : '#app-level-js',
													files : [
															'resources/js/controller/RoleListController.js',
															'resources/js/service/RoleService.js']
												}])
									}
								}
							}).state('menu', {
						url : '/menu',
						templateUrl : 'common'
					}).state('menu.create', {
						url : '/create',
						templateUrl : 'menu/menu-list',
						resolve : {
							loadPlugin : function($ocLazyLoad) {
								return $ocLazyLoad
										.load([{
											name : 'vendors',
											insertBefore : '#app-level-js',
											files : [
														'resources/js/controller/MenuListController.js',
														'resources/js/controller/MenuAddController.js',
														'resources/js/service/MenuService.js',
														'resources/js/service/RoleService.js']
										}])
							}
						}
					}).state('userRole', {
						url : '/userRole',
						templateUrl : 'common'
					}).state('userRole.map', {
						url : '/map',
						templateUrl : 'userRole-map'
					}).state('userRole.unmap', {
						url : '/unmap',
						templateUrl : 'userRole-unmap'
					}).state('roleMenu', {
						url : '/roleMenu',
						templateUrl : 'common'
					}).state('roleMenu.map', {
						url : '/map',
						templateUrl : 'roleMenu-map'
					}).state('roleMenu.unmap', {
						url : '/unmap',
						templateUrl : 'roleMenu-unmap'
					})
		});