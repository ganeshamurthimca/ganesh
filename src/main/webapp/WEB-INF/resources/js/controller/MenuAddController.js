angular.module('davosERP')
.controller('MenuAddController',['$scope', 'growlService','MenuService','$uibModalInstance','param',
                                  function($scope,growlService,MenuService,$modalInstance,param){
	var _self = this;
	_self.menu = {
			menuId : null,
			menuName : '',
			menuLink : '',
			menuIcon : '',
			menuOrder : '',
			menuParent:null
	};
	
	if(param !== null){
		if(param.menuParent != null){
			_self.menu = {
					menuId : param.menuId,
					menuName : param.menuName,
					menuLink : param.menuLink,
					menuIcon : param.menuIcon,
					menuOrder : param.menuOrder,
					menuParent: {
						menuId: param.menuParent.toString()
					}
			};
		}else{
			_self.menu = {
				menuId : param.menuId,
				menuName : param.menuName,
				menuLink : param.menuLink,
				menuIcon : param.menuIcon,
				menuOrder : param.menuOrder,
				menuParent: param.menuParent
			};
		}
	}
	
	_self.submit = submit;
	_self.reset = reset;
	populateMenu();
	$scope.menuList = [];
	
	$scope.submitForm = function(form){
		if(form.validate()){
			submit();
			populateMenu();
		}
	}
	
	
	function submit(){
		if(_self.menu.menuParent != null && _self.menu.menuParent.menuId === ''){
			_self.menu.menuParent = null;
		}
		addMenu(_self.menu);
		reset();
		populateMenu();
	}
	
	function reset(){
		_self.menu = {
				menuId : null,
				menuName : '',
				menuLink : '',
				menuIcon : '',
				menuOrder : '',
				menuParent:null
		};
		$scope.myForm.$setPristine();
	}
	
	function addMenu(menuDto){
		MenuService.addMenu(menuDto).then(function(successResponse){
			if(successResponse === "SUCCESS"){
    			growlService.growl('Menu details Added','success');
    			$modalInstance.close();
    		}else if(successResponse === "ALREADYEXISTS"){
    			growlService.growl('Menu details already exists','warning');
    		}else{
    			growlService.growl('Some problem occured. Please contact IT Dept.','danger');
    		}
		},function(errorResponse){
			growlService.growl('Some problem occured. Please contact IT Dept.','danger');
		});
	}
	
	function populateMenu(){
		MenuService.populateParentMenu().then(function(successResponse){
			$scope.menuList = successResponse;
		},function(errorResponse){
			$scope.menuList = [];
		});
	}
	
	$scope.validateOptions = {
			rules:{
				menuName:{
					required : true
				},
				menuLink:{
					required : true
				},
				menuOrder:{
					required : true,
					number : true
				}
			},
			messages:{
				menuName:{
					required : 'Please enter MenuName'
				},
				menuLink:{
					required : 'Please enter MenuLink'
				},
				menuOrder:{
					required : 'Please enter MenuOrder',
					number: 'Please enter valid number'
				}
			}
	};
}]);