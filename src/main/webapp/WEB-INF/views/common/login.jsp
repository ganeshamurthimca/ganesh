<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="davosERP">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>index</title>

<!-- Vendor CSS -->
<link
	href="resources/vendors/bower_components/animate.css/animate.min.css"
	rel="stylesheet">
<link
	href="resources/vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"
	rel="stylesheet">
<!-- CSS -->
<link href="resources/css/app_1.min.css" rel="stylesheet">
<link href="resources/css/app_2.min.css" rel="stylesheet">
</head>
<body>
	<div class="login-content">
		<!-- Login -->
		<div class="lc-block toggled" id="l-login">
			<div class="lcb-form">
				<c:if test="${not empty error}">
					<div style="color: red;">${error}</div>

					<%-- 					<c:if test="${not empty minute}"> --%>
					<%-- 						<timer data-minute="${minute}" data-seconds="${seconds}" --%>
					<!-- 							style="color:red;"></timer> -->
					<%-- 					</c:if> --%>
				</c:if>
				<c:if test="${not empty logout}">
					<div style="color: red;">${logout}</div>
				</c:if>
				<form action="login" method="POST">
					<div class="input-group m-b-20">
						<span class="input-group-addon"><i
							class="zmdi zmdi-account"></i></span>
						<div class="fg-line">
							<input type="text" name="username" class="form-control"
								placeholder="Username" autofocus autocomplete="off">
						</div>
					</div>

					<div class="input-group m-b-20">
						<span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
						<div class="fg-line">
							<input type="password" class="form-control"
								placeholder="Password" name="password" autocomplete="off">
						</div>
					</div>

					<div class="checkbox">
						<label> <input type="checkbox" value=""> <i
							class="input-helper"></i> Keep me signed in
						</label>
					</div>

					<button type="submit" class="btn btn-login btn-success btn-float">
						<i class="zmdi zmdi-arrow-forward"></i>
					</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>

			<div class="lcb-navigation">
				<a href="#" data-ma-action="login-switch"
					data-ma-block="#l-register"><i class="zmdi zmdi-plus"></i> <span>Register</span></a>
				<a href="#" data-ma-action="login-switch"
					data-ma-block="#l-forget-password"><i>?</i> <span>Forgot
						Password</span></a>
			</div>
		</div>

		<!-- Register -->
		<div class="lc-block" id="l-register">
			<div class="lcb-form">
				<div class="input-group m-b-20">
					<span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
					<div class="fg-line">
						<input type="text" class="form-control" placeholder="Username">
					</div>
				</div>

				<div class="input-group m-b-20">
					<span class="input-group-addon"><i class="zmdi zmdi-email"></i></span>
					<div class="fg-line">
						<input type="text" class="form-control"
							placeholder="Email Address">
					</div>
				</div>

				<div class="input-group m-b-20">
					<span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
					<div class="fg-line">
						<input type="password" class="form-control" placeholder="Password">
					</div>
				</div>

				<a href="#" class="btn btn-login btn-success btn-float"><i
					class="zmdi zmdi-check"></i></a>
			</div>

			<div class="lcb-navigation">
				<a href="#" data-ma-action="login-switch" data-ma-block="#l-login"><i
					class="zmdi zmdi-long-arrow-right"></i> <span>Sign in</span></a> <a
					href="#" data-ma-action="login-switch"
					data-ma-block="#l-forget-password"><i>?</i> <span>Forgot
						Password</span></a>
			</div>
		</div>

		<!-- Forgot Password -->
		<div class="lc-block" id="l-forget-password">
			<div class="lcb-form">
				<p class="text-left">Lorem ipsum dolor sit amet, consectetur
					adipiscing elit. Nulla eu risus. Curabitur commodo lorem fringilla
					enim feugiat commodo sed ac lacus.</p>

				<div class="input-group m-b-20">
					<span class="input-group-addon"><i class="zmdi zmdi-email"></i></span>
					<div class="fg-line">
						<input type="text" class="form-control"
							placeholder="Email Address">
					</div>
				</div>

				<a href="#" class="btn btn-login btn-success btn-float"><i
					class="zmdi zmdi-check"></i></a>
			</div>

			<div class="lcb-navigation">
				<a href="#" data-ma-action="login-switch" data-ma-block="#l-login"><i
					class="zmdi zmdi-long-arrow-right"></i> <span>Sign in</span></a> <a
					href="#" data-ma-action="login-switch" data-ma-block="#l-register"><i
					class="zmdi zmdi-plus"></i> <span>Register</span></a>
			</div>
		</div>
	</div>


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
</body>
</html>