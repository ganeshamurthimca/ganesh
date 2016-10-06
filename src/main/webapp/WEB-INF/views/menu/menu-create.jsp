<style>
.help-block {
	color: red !important;
}
</style>
<div class="card">
	<div class="card-body card-padding p-t-0">
		<div class="modal-header">
			<h4 class="modal-title">Menu Creation</h4>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" role="form" name="myForm"
				ng-submit="submitForm(myForm)" ng-validate="validateOptions">
				<div class="card-header"></div>
				<div class="card-body card-padding">
					<div class="form-group">
						<label for="menuName" class="col-sm-2 control-label">Menu
							Name</label>
						<div class="col-sm-10">
							<div class="fg-line">
								<input type="text" class="form-control input-sm" id="menuName"
									name="menuName" placeholder="Menu Name"
									ng-model="mACtrl.menu.menuName">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="menuLink" class="col-sm-2 control-label">Menu
							link</label>
						<div class="col-sm-10">
							<div class="fg-line">
								<input type="text" class="form-control input-sm" id="menuLink"
									name="menuLink" placeholder="Menu Link"
									ng-model="mACtrl.menu.menuLink">
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="menuIcon" class="col-sm-2 control-label">Menu
							Icon</label>
						<div class="col-sm-10">
							<div class="fg-line">
								<input type="text" class="form-control input-sm" id="menuIcon"
									name="menuIcon" placeholder="Menu Icon"
									ng-model="mACtrl.menu.menuIcon">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="menuOrder" class="col-sm-2 control-label">Menu
							Order</label>
						<div class="col-sm-10">
							<div class="fg-line">
								<input type="number" min="0" class="form-control input-sm"
									id="menuOrder" name="menuOrder" placeholder="Menu Order"
									ng-model="mACtrl.menu.menuOrder">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="menuParent" class="col-sm-2 control-label">Menu
							Parent</label>
						<div class="col-sm-10">
							<div class="fg-line">
								<div class="select">
									<select class="form-control" name="menuParent" id="menuParent"
										ng-model="mACtrl.menu.menuParent.menuId">
										<option value="">-Select-</option>
										<optgroup ng-repeat="menu in menuList"
											label="{{menu.menuName}}">
											<option value="{{menu.menuId}}">{{menu.menuName}}</option>
											<option ng-repeat="menuChild in menu.menuList"
												value="{{menuChild.menuId}}">{{menuChild.menuName}}</option>
										</optgroup>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary btn-sm">Add</button>
							<button type="reset" ng-click="mACtrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="{{mACtrl.menu.menuId!=null}}">Reset</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>