angular.module('davosERP')
    /*
     * User List Table Controller
     */
    .controller('RoleListController', ['$filter', '$sce', 'ngTableParams', 'RoleService', 'growlService',
        function($filter, $sce, ngTableParams, RoleService, growlService) {
            var _self = this;
            _self.data = [];
            _self.changeStatus = changeStatus;
            listAllRoles();

            function listAllRoles() {
            	RoleService.listAllRoles().then(function(data) {
                    _self.data = data;
                    var parameters = {
                        page: 1,
                        count: 10
                    };
                    var settings = {
                        total: _self.data.length,
                        getData: function($defer, params) {
                            var sample = params.sorting() ? $filter('orderBy')(_self.data, params.orderBy()) : _self.data;
                            // use build-in
                            // angular filter
                            var orderedData = params.filter() ? $filter('filter')(sample, params.filter()) : sample;

                            this.roleId = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                            this.roleName = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                            this.actionBy = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                            this.isEnabled = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());

                            params.total(orderedData.length);
                            // set
                            // total
                            // for
                            // recalc
                            // pagination
                            $defer.resolve(this.roleId, this.roleName,  this.actionBy, this.isEnabled);
                        }
                    };
                    _self.roleListTable = new ngTableParams(parameters, settings);
                });
            }
            
            function changeStatus(roleDto){
            	roleDto.isEnabled = !roleDto.isEnabled; 
            	RoleService.createRole(roleDto).then(function(successResponse){
            		if(successResponse === "SUCCESS"){
            			growlService.growl('Status changed for ' + roleDto.roleName,'inverse');
            		}else if(successResponse === "ALREADYEXISTS"){
            			growlService.growl(roleDto.roleName + ' already exists','inverse');
            		}else{
            			growlService.growl('Some problem occured. Please contact IT Dept.','inverse');
            		}
            	},function(errorResponse){
            		roleDto.isEnabled = !roleDto.isEnabled;
            		growlService.growl('Some problem occured. Please contact IT Dept.','inverse');
            	});
            }
        }
    ]);