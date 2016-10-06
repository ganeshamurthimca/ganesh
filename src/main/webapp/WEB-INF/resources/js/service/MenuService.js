angular.module("davosERP")
.factory('MenuService',['$http', '$q', function($http, $q) {
	var REST_MENU_SERVICE_URI = 'menu';
	
	var factory = {
		addMenu : addMenu,
		populateMenu:populateMenu,
		populateParentMenu:populateParentMenu
	};
	
	return factory;
	
	function addMenu(menuDto){
		console.log(menuDto);
		var deferred = $q.defer();
		$http.post(REST_MENU_SERVICE_URI+"/addMenu", menuDto).then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise;
	}
	
	function populateMenu(){
		var deferred = $q.defer();
		$http.get(REST_MENU_SERVICE_URI+"/populateMenu").then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise;
	}  
	
	function populateParentMenu(){
		var deferred = $q.defer();
		$http.get(REST_MENU_SERVICE_URI+"/populateParentMenu").then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise;
	}
}]);
