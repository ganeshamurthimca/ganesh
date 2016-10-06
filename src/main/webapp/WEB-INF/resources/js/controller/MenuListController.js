angular.module('davosERP').controller('MenuTreeController',function($scope,MenuService,$uibModal,growlService){
	var _self = this;
	_self.menuData = [];
	_self.expandAll = expandAll;
	_self.collapseAll = collapseAll;
	_self.newSubMenu = newSubMenu;
	_self.changeStatus = changeStatus;
	buildMenus();
	function expandAll(){
		 $scope.$broadcast('angular-ui-tree:expand-all');
	}
	function collapseAll(){
		 $scope.$broadcast('angular-ui-tree:collapse-all');
	}
	
	function newSubMenu(obj,parent){
		var modalObj = $uibModal.open({
	      animation: true,
	      templateUrl: 'menu/menu-create',
	      controller: 'MenuAddController',
	      controllerAs: 'mACtrl',
	      size: 'lg',
	      backdrop: true,
	      keyboard: true,
	      resolve: {
              param: function () {
            	  if(obj === undefined){
            		  return null;
            	  }else if(parent === undefined){
            		  return {'menuId':obj.menuId,'menuName' : obj.menuName,'menuLink' : obj.menuLink, 'menuIcon':obj.menuIcon,'menuOrder':obj.menuOrder,'menuParent':null };
            	  }else{
            		  return {'menuId':obj.menuId,'menuName' : obj.menuName,'menuLink' : obj.menuLink, 'menuIcon':obj.menuIcon,'menuOrder':obj.menuOrder,'menuParent':parent };
            	  }
              }
          }
		});
		modalObj.result.then(function () {
			buildMenus(); 
	    });
	}
	
	function changeStatus(menuDTO,parent){
		if(parent != undefined){
			menuDTO.menuParent = {
				menuId:parent.toString()
			}
		}
		menuDTO.isEnabled = !menuDTO.isEnabled;
		MenuService.addMenu(menuDTO).then(function(successResponse){
			if(successResponse === "SUCCESS"){
    			growlService.growl('Menu Status Changed','success');
    		}else if(successResponse === "ALREADYEXISTS"){
    			growlService.growl('Menu details already exists','warning');
    			menuDTO.isEnabled = !menuDTO.isEnabled;
    		}else{
    			growlService.growl('Some problem occured. Please contact IT Dept.','danger');
    			menuDTO.isEnabled = !menuDTO.isEnabled;
    		}
		},function(errorResponse){
			growlService.growl('Some problem occured. Please contact IT Dept.','danger');
			menuDTO.isEnabled = !menuDTO.isEnabled;
		});
	}
	
	function buildMenus(){
		MenuService.populateParentMenu().then(function(successResponse){
			_self.menuData = successResponse;
		},function(errorResponse){
			console.log(errorResponse);
		});
	}
});