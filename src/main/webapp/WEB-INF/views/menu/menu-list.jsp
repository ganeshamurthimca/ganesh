
<div class="container">
	<div class="block-header"></div>

	<div class="card">
		<div class="card-header">
			<h2>Menu List</h2>
		</div>

		<div class="card-body" ng-controller="MenuTreeController as mtCtrl">
			<script type="text/ng-template" id="nodes_renderer.html">
  <div  class="tree-node tree-node-content angular-ui-tree-handle">
    <a class="btn btn-info btn-xs" ng-click="toggle(this)" ng-if="node.menuList && node.menuList.length > 0" data-nodrag><span
        class="zmdi"
        ng-class="{
          'zmdi-plus': collapsed,
          'zmdi-minus': !collapsed
        }"></span></a>
    {{node.menuName}}
    <a class="pull-right btn btn-default btn-sm" data-nodrag ng-click="mtCtrl.changeStatus(node,parent)" style="margin-right: 8px;"><span
        class="zmdi" ng-class="node.isEnabled?'zmdi-eye' : 'zmdi-eye-off'"></span></a>
    <a class="pull-right btn btn-primary btn-sm" data-nodrag ng-click="mtCtrl.newSubMenu(node,parent)" style="margin-right: 8px;"><span
        class="zmdi zmdi-edit"></span></a>
  </div>

  <ol ui-tree-nodes="" ng-model="node.menuList" ng-class="{hidden: collapsed}">
    <li ng-repeat="node in node.menuList" onload="parent=$parent.$parent.node.menuId" ui-tree-node ng-include="'nodes_renderer.html'">
    </li>
  </ol>
</script>
			<div class="row">
				<div class="col-sm-12 text-right">
					<button ng-click="mtCtrl.expandAll()" class="btn btn-primary">Expand
						all</button>
					<button ng-click="mtCtrl.collapseAll()" class="btn btn-info">Collapse
						all</button>
					<button ng-click="mtCtrl.newSubMenu()" class="btn bgm-gray">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
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
