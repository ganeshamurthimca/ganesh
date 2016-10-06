<style>
.help-block {
	color: red !important;
}
</style>
<div class="container">
	<div class="block-header">
		<h2>User Creation</h2>

		<ul class="actions">
			<li><a href="#"> <i class="zmdi zmdi-trending-up"></i>
			</a></li>
			<li><a href="#"> <i class="zmdi zmdi-check-all"></i>
			</a></li>
			<li class="dropdown" uib-dropdown><a href="#"
				uib-dropdown-toggle> <i class="zmdi zmdi-more-vert"></i>
			</a>

				<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="#">Refresh</a></li>
					<li><a href="#">Manage Widgets</a></li>
					<li><a href="#">Widgets Settings</a></li>
				</ul></li>
		</ul>
	</div>
	<div class="card" ng-controller="UserController as uCtrl">
		<form name="myForm" class="form-horizontal" role="form"
			ng-submit="submitForm(myForm)" ng-validate="validateOptions">
			<input type="hidden" ng-model="uCtrl.user.id" />
			<div class="card-header"></div>
			<div class="card-body card-padding">
				<div class="form-group" ng-class="uCtrl.userNameClass">
					<label for="inputEmail3" class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<div class="fg-line">
							<input type="text" name="userName" class="form-control input-sm"
								id="userName" ng-model="uCtrl.user.userName"
								placeholder="Username" autofocus="autofocus" autocomplete="off"
								required lowerize>

						</div>
					</div>

				</div>
				<div class="form-group" ng-class="uCtrl.userPasswordClass">
					<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">

						<div class="fg-line">
							<input type="password" name="userPassword"
								class="form-control input-sm" id="userPassword"
								placeholder="Password" ng-model="uCtrl.user.userPassword"
								autocomplete="off" required>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary btn-sm">Add</button>
						<button type="reset" ng-click="uCtrl.reset()"
							class="btn btn-warning btn-sm">Reset</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
