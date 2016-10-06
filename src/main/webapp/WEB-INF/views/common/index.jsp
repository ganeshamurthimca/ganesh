<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9" data-ng-app="davosERP" data-ng-controller="anthemCtrl as aCtrl"><![endif]-->
<![if IE 9 ]>
<html data-ng-app="davosERP" data-ng-controller="anthemCtrl as aCtrl">
<![endif]>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="resources/img/icons/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="resources/img/icons/favicon.ico"
	type="image/x-icon">
<title>Davos Pharma Net.</title>

<!-- Vendor CSS -->
<link
	href="resources/vendors/bower_components/animate.css/animate.min.css"
	rel="stylesheet">
<link
	href="resources/vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"
	rel="stylesheet">
<link
	href="resources/vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.css"
	rel="stylesheet">
<link
	href="resources/vendors/bower_components/angular-loading-bar/src/loading-bar.css"
	rel="stylesheet">
<link
	href="resources/vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css"
	rel="stylesheet">
<link
	href="resources/vendors/angular-tree/angular-ui-tree.min.css"
	rel="stylesheet">

<!-- CSS -->
<link href="resources/css/app.min.1.css" rel="stylesheet" id="app-level">
<link href="resources/css/app.min.2.css" rel="stylesheet">
<link href="resources/css/demo.css" rel="stylesheet">

<link href="resources/css/app.css" rel="stylesheet">

</head>

<body data-ng-class="{ 'sw-toggled': aCtrl.layoutType === '1'}">

	<data ui-view></data>

	<!-- Older IE warning message -->
	<!--[if lt IE 9]>
            <div class="ie-warning">
                <h1 class="c-white">Warning!!</h1>
                <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web browsers to access this website.</p>
                <div class="iew-container">
                    <ul class="iew-download">
                        <li>
                            <a href="http://www.google.com/chrome/">
                                <img src="img/browsers/chrome.png" alt="">
                                <div>Chrome</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.mozilla.org/en-US/firefox/new/">
                                <img src="img/browsers/firefox.png" alt="">
                                <div>Firefox</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://www.opera.com">
                                <img src="img/browsers/opera.png" alt="">
                                <div>Opera</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.apple.com/safari/">
                                <img src="img/browsers/safari.png" alt="">
                                <div>Safari</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                                <img src="img/browsers/ie.png" alt="">
                                <div>IE (New)</div>
                            </a>
                        </li>
                    </ul>
                </div>
                <p>Sorry for the inconvenience!</p>
            </div>
        <![endif]-->


	<!-- Core -->
	<script
		src="resources/vendors/bower_components/jquery/dist/jquery.min.js"></script>
	<script
		src="resources/vendors/bower_components/jquery-validation/jquery.validate.min.js"></script>


	<!-- Angular -->
	<script src="resources/vendors/bower_components/angular/angular.min.js"></script>
	<script
		src="resources/vendors/bower_components/angular-animate/angular-animate.min.js"></script>
	<script
		src="resources/vendors/bower_components/angular-resource/angular-resource.min.js"></script>

	<!-- Angular Modules -->
	<script
		src="resources/vendors/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
	<script
		src="resources/vendors/bower_components/angular-loading-bar/src/loading-bar.js"></script>
	<script
		src="resources/vendors/bower_components/oclazyload/dist/ocLazyLoad.min.js"></script>
	<script
		src="resources/vendors/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
	<script
		src="resources/vendors/bower_components/angular-validation/angular-validate.js"></script>

	<!-- Common Vendors -->
	<script
		src="resources/vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
	<script
		src="resources/vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.min.js"></script>
	<script
		src="resources/vendors/bower_components/Waves/dist/waves.min.js"></script>
	<script src="resources/vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
	<script
		src="resources/vendors/bower_components/ng-table/dist/ng-table.min.js"></script>


	<!-- Placeholder for IE9 -->
	<!--[if IE 9 ]>
            <script src="vendors/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
        <![endif]-->

	<!-- Using below vendors in order to avoid misloading on resolve -->
	<script src="resources/vendors/bower_components/flot/jquery.flot.js"></script>
	<script
		src="resources/vendors/bower_components/flot.curvedlines/curvedLines.js"></script>
	<script
		src="resources/vendors/bower_components/flot/jquery.flot.resize.js"></script>
	<script
		src="resources/vendors/bower_components/moment/min/moment.min.js"></script>
	<script
		src="resources/vendors/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
	<script
		src="resources/vendors/bower_components/flot-orderBars/js/jquery.flot.orderBars.js"></script>
	<script
		src="resources/vendors/bower_components/flot/jquery.flot.pie.js"></script>
	<script
		src="resources/vendors/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
	<script
		src="resources/vendors/bower_components/angular-nouislider/src/nouislider.min.js"></script>
		
		<!-- Added to test the angular Treeview -->
	<script
		src="resources/vendors/angular-tree/angular-ui-tree.min.js"></script>

	<!-- App level -->
	<script src="resources/js/app.js"></script>
	<script src="resources/js/config.js"></script>
	<script src="resources/js/controller/ui-controller.js"></script>
	<script src="resources/js/service/growlService.js"></script>
	<script src="resources/js/service/ui-service.js"></script>
	<script src="resources/js/service/Case-Directive.js"></script>
	<script src="resources/js/directive/sweat-alert-directive.js"></script>
	<Script src="resources/js/template.js"></Script>

	<!-- App Module -->
	<script src="resources/js/module/template.js"></script>
	<script src="resources/js/module/ui.js"></script>
	<script src="resources/js/service/UserService.js"></script>
	<script src="resources/js/service/RoleMenuService.js"></script>
</body>
</html>