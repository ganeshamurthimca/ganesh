davosERP
.factory('RoleMenuService',['$http', '$q', function($http, $q) {
	var REST_MENU_SERVICE_URI = 'RoleMenuMapping';
	
	var factory = {
		addRoleMenu : addRoleMenu,
		buildMenuFromUser:buildMenuFromUser,
		findMenusByRole:findMenusByRole
	};
	
	return factory;
	
	function addRoleMenu(roleMenuMappingDto){
		var deferred = $q.defer();
		$http.post(REST_MENU_SERVICE_URI+"/addRoleMenu", roleMenuMappingDto).then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise;
	}
	
	function buildMenuFromUser(){
		var deferred = $q.defer();
		$http.get("user/getCurrentUser").then(function(successResponse){
			var userName = successResponse.data.userName;
			$http.get(REST_MENU_SERVICE_URI+"/findByUserName/"+userName).then(function(successResponse) {
				deferred.resolve(successResponse.data);
			}, function(errorResponse) {
				deferred.reject(errorResponse.data);
			});	
		},function(errorResponse){
			console.log(errorResponse);
		});
		
		return deferred.promise;
	}
	
	function findMenusByRole(menuId){
		var deferred = $q.defer();
		$http.get(REST_MENU_SERVICE_URI+"/findByRoleId/"+menuId).then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});	
		return deferred.promise;
	}
}]);
