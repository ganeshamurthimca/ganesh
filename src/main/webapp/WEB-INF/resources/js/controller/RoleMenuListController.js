angular.module('davosERP').controller('RoleMenuListController',['$scope','RoleService','RoleMenuService', function($scope, RoleService,RoleMenuService) {
	var _self = this;
	_self.roleList = [];
	_self.selectedRole = "";
	_self.menuData = [];
	_self.generateTree = generateTree;
	_self.showMenu = false;
	
	if(_self.selectedRole !== ""){
		generateTree(_self.selectedRole);
	}
	populateRoles();
	function populateRoles(){
		RoleService.listAllRoles().then(function(successResponse){
			_self.roleList = successResponse;
		},function(errorResponse){
			 console.log(errorResponse);
		});
	}
	
	function generateTree(menuId){
		RoleMenuService.findMenusByRole(menuId).then(function(successResponse){
			_self.menuData = successResponse;
			_self.showMenu = true;
		},function(errorResponse){
			console.log(errorResponse);
		});
	}
}]);