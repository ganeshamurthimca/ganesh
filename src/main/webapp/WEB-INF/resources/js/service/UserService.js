angular.module('davosERP').factory('UserService', [ '$http', '$q', function($http, $q) {
	var REST_SERVICE_URI = "user";

	var factory = {
		createUser : createUser,
		listAllUsers: listAllUsers,
		resetPassword: resetPassword,
		changeStatus:changeStatus,
		listUserHistory:listUserHistory,
		unlockAccount:unlockAccount,
		currentUser:currentUser
	};

	return factory;

	function createUser(user) {
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI+"/createUser", user).then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			console.log('Error While creating User ');
			console.log(errorResponse);
			deferred.reject(errorResponse.data);
		});
		return deferred.promise;
	}
	
	
	function listAllUsers() {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI+"/listAllUsers").then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise;
	}
	
	function resetPassword(userId){
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI+"/resetPassword/"+userId).then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise;
	}
	
	function changeStatus(userId){
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI+"/changeStatus/"+userId).then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise; 
	}
	
	function listUserHistory(userId){
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI+"/listUserHistory/"+userId).then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise; 
	}
	
	
	function unlockAccount(userName){
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI+"/unlockAccount/"+userName).then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise; 
	}
	
	function currentUser(){
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI+"/getCurrentUser").then(function(successResponse) {
			deferred.resolve(successResponse.data);
		}, function(errorResponse) {
			deferred.reject(errorResponse.data);
		});
		return deferred.promise; 
	}
} ]);