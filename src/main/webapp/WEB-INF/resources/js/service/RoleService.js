angular.module('davosERP')
.factory('RoleService',[ '$http', '$q', function($http, $q) {
	var REST_ROLE_SERVICE_URI = 'role';
	
	var factory = {
			createRole:createRole,
			listAllRoles:listAllRoles
	};
	
	return factory;
	
	function createRole(roleDto){
		var deferred = $q.defer();
		$http.post(REST_ROLE_SERVICE_URI+"/createRole", roleDto).then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise;
	}
	
	function listAllRoles(){
		var deferred = $q.defer();
		$http.post(REST_ROLE_SERVICE_URI+"/listAllRoles").then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise;
	}
}]);