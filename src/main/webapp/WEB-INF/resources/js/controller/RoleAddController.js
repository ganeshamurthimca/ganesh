angular.module('davosERP')
.controller('RoleAddController',['$scope', 'growlService','RoleService',
                                 function($scope,growlService,RoleService){
	var _self = this;
	_self.role = {
			roleId : null,
 			roleName : ''
	};
	
	_self.submit = submit;
	_self.reset = reset;
	
	$scope.submitForm = function(form){
		if(form.validate()){
			submit();
		}
	}
	function submit(){
		if(_self.role.roleId == null){
			createRole(_self.role);
		}else{
			growlService.growl('Already data exists','inverse');
		}
		reset();
		angular.element('[id="roleName"]').focus();
	}
	
	
	function reset(){
		 _self.role={
			roleId:null,
			roleName:''
		 };
		 $scope.myForm.$setPristine();
	}
	
	function createRole(roleDto){
		RoleService.createRole(roleDto).then(function(successResponse){
			if(successResponse === "SUCCESS"){
    			growlService.growl(roleDto.roleName + ' Added','inverse');
    		}else if(successResponse === "ALREADYEXISTS"){
    			growlService.growl(roleDto.roleName + ' already exists','inverse');
    		}else{
    			growlService.growl('Some problem occured. Please contact IT Dept.','inverse');
    		}
		},function(errorResponse){
			console.log(errorResponse);
			growlService.growl('Some problem occured. Please contact IT Dept.','inverse');
		});
	}
	
	
	$scope.validateOptions = {
            rules: {
                roleName: {
                    required: true,
                    remote: {
                        url: 'role/findByRoleName'
                    }
                }
            },
            messages: {
            	roleName: {
                    required: 'Please enter Rolename',
                    remote: 'RoleName already exists'
                }
            }
        };
}]);