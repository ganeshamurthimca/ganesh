
<div class="container"
	data-ng-controller="userHistoryController as tctrl">
	<div class="block-header"></div>

	<div class="card">
		<div class="card-header">
			<h2>User Account History</h2>
		</div>

		<div class="card-body">
			<div class="table-responsive">
				<table ng-table="tctrl.userListTable"
					class="table table-striped table-vmiddle" show-filter="true"
					ng-init="showX=true">
					<tr ng-repeat="w in $data">
						<td data-title="'#'" filter="{ '#': 'text' }"
							style="text-align: center">{{$index + 1}}</td>
						<td data-title="'UserId'" sortable="'userId'"
							filter="{ 'userId': 'text' }" style="text-align: center;">{{
							w.userId }}</td>
						<td data-title="'User Name'" sortable="'userName'"
							filter="{ 'userName': 'text' }">{{ w.userName }}</td>
						<td data-title="'Action By'" sortable="'actionBy'"
							filter="{ 'actionBy': 'text' }">{{ w.actionBy.userName }}</td>
						<td data-title="'Action On'" sortable="'actionOn'"
							filter="{ 'actionOn': 'text' }">{{ w.actionOn }}</td>
						<td data-title="'IsEnabled'" sortable="'isEnabled'"
							filter="{ 'isEnabled': 'text' }">{{ w.isEnabled }}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>


