
<div class="container">
	<div class="block-header"></div>

	<div class="card" ng-controller="MenuTreeController as mtCtrl">
		<div class="card-header">
			<h2>Menu List</h2>
		</div>
		<form role="form" class="form-horizontal">
			<div class="card-body card-padding">
				<div class="form-group">
					<label for="menuParent" class="col-sm-2 control-label"><strong>Role
							Name</strong></label>
					<div class="col-sm-5">
						<div class="fg-line">
							<div class="select">
								<select class="form-control" name="role" id="role"
									ng-model="mtCtrl.selectedRole">
									<option value="">-Select-</option>
									<option ng-repeat="role in mtCtrl.roleList"
										value="{{role.roleId}}">{{role.roleName}}</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-sm-5">
						<button type="button" class="btn btn-primary btn-sm"
							ng-click="mtCtrl.generateTree(mtCtrl.selectedRole)">Seacrh</button>
					</div>
				</div>
			</div>
		</form>
		<div ng-show="mtCtrl.showMenu">
			<script type="text/ng-template" id="nodes_renderer.html">
  <div ui-tree-handle class="tree-node tree-node-content">
    <a class="btn btn-info btn-xs" ng-click="toggle(this)" ng-if="node.menuList && node.menuList.length > 0" data-nodrag><span
        class="zmdi"
        ng-class="{
          'zmdi-plus': collapsed,
          'zmdi-minus': !collapsed
        }"></span></a>
    {{node.menuName}}
    <a class="pull-right btn btn-danger btn-xs" data-nodrag ng-click="remove(this)"><span
        class="glyphicon glyphicon-remove"></span></a>
    <a class="pull-right btn btn-primary btn-xs" data-nodrag ng-click="mtCtrl.newSubItem(node.menuList)" style="margin-right: 8px;"><span
        class="glyphicon glyphicon-plus"></span></a>
  </div>

  <ol ui-tree-nodes="" ng-model="node.menuList" ng-class="{hidden: collapsed}">
    <li ng-repeat="node in node.menuList" ui-tree-node ng-include="'nodes_renderer.html'">
    </li>
  </ol>
			</script>
			<div class="row">
				<div class="col-sm-12">
					<button ng-click="expandAll()">Expand all</button>
					<button ng-click="collapseAll()">Collapse all</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div ui-tree id="tree-root">
						<ol ui-tree-nodes ng-model="mtCtrl.menuData">
							<li ng-repeat="node in mtCtrl.menuData" ui-tree-node
								ng-include="'nodes_renderer.html'"></li>
						</ol>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
