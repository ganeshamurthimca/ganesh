
<div class="container" data-ng-controller="RoleListController as rLCtrl">
	<div class="block-header"></div>

	<div class="card">
		<div class="card-header">
			<h2>Role List</h2>
		</div>

		<div class="card-body">
			<div class="table-responsive">
				<table ng-table="rLCtrl.roleListTable"
					class="table table-striped table-vmiddle" show-filter="true"
					ng-init="showX=true">
					<tr ng-repeat="w in $data">
						<td data-title="'RoleId'" sortable="'roleId'"
							filter="{ 'roleId': 'text' }" style="text-align: center;">{{
							w.roleId }}</td>
						<td data-title="'Role Name'" sortable="'roleName'"
							filter="{ 'roleName': 'text' }">{{ w.roleName }}</td>

						<td data-title="'Action By'" sortable="'actionBy'"
							filter="{ 'actionBy': 'text' }">{{ w.actionBy.userName }}</td>
						<td data-title="'IsEnabled'" sortable="'isEnabled'"
							filter="{ 'isEnabled': 'text' }">{{ w.isEnabled }}</td>

						<td data-title="'Options'" filter="{ 'reset': 'text' }">
							<ul class="actions">
								<li class="dropdown" uib-dropdown><button
										class="btn btn-warning btn-icon waves-effect waves-circle"
										uib-dropdown-toggle>
										<i class="zmdi zmdi-apps"></i>
									</button>
									<ul class="dropdown-menu dropdown-menu-right">
										<li><a ng-click="rLCtrl.changeStatus(w);">Change
												Status</a></li>
									</ul></li>
							</ul>
						</td>

					</tr>
				</table>
			</div>
		</div>
	</div>
</div>


