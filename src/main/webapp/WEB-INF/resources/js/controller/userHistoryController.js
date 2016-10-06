angular.module('davosERP').controller(
		'userHistoryController',
		[
				'$filter',
				'$sce',
				'ngTableParams',
				'UserService',
				'growlService',
				'$stateParams',
				function($filter, $sce, ngTableParams, UserService,
						growlService, $stateParams) {
					_self = this;
					_self.data = [];
					listUserHistory();
					function listUserHistory() {
						UserService.listUserHistory($stateParams.userId).then(
								function(data) {
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
				}
		/*
		 * End of userHistoryController
		 */
		]);