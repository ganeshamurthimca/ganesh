
<div class="container" data-ng-controller="userListController as tctrl">
	<div class="block-header"></div>

	<div class="card">
		<div class="card-header">
			<h2>User List</h2>
		</div>

		<div class="card-body">
			<div class="table-responsive">
				<table ng-table="tctrl.userListTable"
					class="table table-striped table-vmiddle" show-filter="true"
					ng-init="showX=true">
					<tr ng-repeat="w in $data">
						<td data-title="'UserId'" sortable="'userId'"
							filter="{ 'userId': 'text' }" style="text-align: center;">{{
							w.userId }}</td>
						<td data-title="'User Name'" sortable="'userName'"
							filter="{ 'userName': 'text' }">{{ w.userName }}</td>

						<td data-title="'Action By'" sortable="'actionBy'"
							filter="{ 'actionBy': 'text' }">{{ w.actionBy.userName }}</td>
						<td data-title="'IsEnabled'" sortable="'isEnabled'"
							filter="{ 'isEnabled': 'text' }">{{ w.isEnabled }}</td>
						<td data-title="'IsLocked'" sortable="'isLocked'"
							filter="{ 'isLocked': 'text' }">{{ !w.accountNonLocked }}</td>
						<td data-title="'Options'" filter="{ 'reset': 'text' }">
							<ul class="actions">
								<li class="dropdown" uib-dropdown><button
										class="btn btn-warning btn-icon waves-effect waves-circle"
										uib-dropdown-toggle>
										<i class="zmdi zmdi-apps"></i>
									</button>
									<ul class="dropdown-menu dropdown-menu-right">
										<li><a ng-click="tctrl.resetPassword(w.userId)">Reset
												Password</a></li>
										<li><a ng-click="tctrl.changeStatus(w);">Change
												Status</a></li>
										<li><a ng-click="tctrl.unlockAccount(w);">Unlock
												Account</a></li>
										<li><a data-ui-sref-active="active"
											data-ui-sref="user.viewHistory({userId:w.userId})">View
												History</a></li>
									</ul></li>
							</ul>
						</td>

					</tr>
				</table>
			</div>
		</div>
	</div>
</div>


