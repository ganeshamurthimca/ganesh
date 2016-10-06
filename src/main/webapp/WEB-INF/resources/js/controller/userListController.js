angular.module('davosERP')
    /*
     * User List Table Controller
     */
    .controller('userListController', ['$filter', '$sce', 'ngTableParams', 'UserService', 'growlService',
        function($filter, $sce, ngTableParams, UserService, growlService) {
            var _self = this;
            _self.data = [];
            _self.resetPassword = resetPassword;
            _self.changeStatus = changeStatus;
            _self.unlockAccount = unlockAccount;
            listAllUsers();

            function listAllUsers() {
                UserService.listAllUsers().then(function(data) {
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

                            this.userId = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                            this.userName = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                            this.userPassword = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                            this.actionBy = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
                            this.isEnabled = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());

                            params.total(orderedData.length);
                            // set
                            // total
                            // for
                            // recalc
                            // pagination
                            $defer.resolve(this.userId, this.userName, this.userPassword, this.actionBy, this.isEnabled);
                        }
                    };
                    _self.userListTable = new ngTableParams(parameters, settings);
                });
            }

            function resetPassword(userId) {
                UserService.resetPassword(userId).then(function(data) {
                    growlService.growl(data.response, 'inverse');
                });
            }

            function changeStatus(user) {
                UserService.changeStatus(user.userId).then(function(data) {
                    growlService.growl(data.response, 'inverse');
                    if (data.response !== 'You dont have rights to change Admin Status') {
                        user.isEnabled = !user.isEnabled;
                    }

                });
            }
            
            function unlockAccount(user){
            	UserService.unlockAccount(user.userName).then(function(data){
            		console.log(data);
            		 growlService.growl(data.response, 'inverse');
            		 if(!user.accountNonLocked){
            			 user.accountNonLocked = !user.accountNonLocked;
            		 }
            	});
            }
        }
    ]);