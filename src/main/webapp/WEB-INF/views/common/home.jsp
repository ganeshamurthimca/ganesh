<header id="header" data-current-skin={{aCtrl.currentSkin}}
	data-ng-include="'template/header.html'"
	data-ng-controller="headerControl as hCtrl"></header>
<section id="main">
	<aside id="sidebar" data-ng-include="'template/sidebar-left.html'"
		data-ng-class="{ 'toggled': aCtrl.sidebarToggle.left === true }"></aside>
	<aside id="chat" data-ng-include="'template/chat.html'"
		data-ng-class="{ 'toggled': aCtrl.sidebarToggle.right === true }"></aside>
</section>
<footer id="footer"></footer>