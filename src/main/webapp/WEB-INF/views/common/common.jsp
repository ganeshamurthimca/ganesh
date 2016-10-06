<header id="header" data-current-skin={{aCtrl.currentSkin}}
	data-ng-include src="'template/header.html'"
	data-ng-controller="headerControl as hCtrl"></header>

<section id="main">
	<aside id="sidebar" data-ng-include src="'template/sidebar-left.html'"
		data-ng-class="{ 'toggled': aCtrl.sidebarToggle.left === true }"></aside>

	<aside id="chat" data-ng-include src="'template/chat.html'"
		data-ng-class="{ 'toggled': aCtrl.sidebarToggle.right === true }"></aside>

	<section id="content" class="page-view" data-ui-view></section>

</section>

<footer id="footer"></footer>