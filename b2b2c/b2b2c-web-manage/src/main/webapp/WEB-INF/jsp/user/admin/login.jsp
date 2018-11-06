<%@page isELIgnored="false" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>管理员登录</title>
<link href="${contextPath}/static/css/base.css" rel="stylesheet"
	type="text/css" />
<link href="${contextPath}/static/css/manage-login.css" rel="stylesheet"
	type="text/css" />
<style>
html, body {
	height: 100%;
}

body {
	background-image: url("${contextPath}/static/img/login_back.png");
}

body>table {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td align="center">
				<div class="logindv">
					<form action="#" method="post">
						<dl>
							<dd>
								<div class="ttitlediv">
								<span>管理平台</span>
								</div>

							</dd>
							<dd>
								<div class="ptextdiv">
									<span><img src="${contextPath}/static/img/users.png" /></span><input
										placeholder="用户名/E-mail/手机" autocomplete="off" name="username"
										maxlength="18" />
								</div>
							</dd>
							<dd>
								<div class="ptextdiv">
									<span><img src="${contextPath}/static/img/lock.png" /></span><input
										placeholder="密码" autocomplete="off" type="password"
										name="password" maxlength="18" />
								</div>
							</dd>
							<dd>
								<div class="dmorediv">
									<div class="dsaveuser">
										<input id="save_username" type="checkbox" /><span><label
											for="save_username">记住用户名</label></span>
									</div>
									<div class="dforgetpwd">
										<a href="#">忘记密码</a>
									</div>
								</div>
							</dd>
							<dd>
								<div class="fsubmitdiv">
									<input type="submit" value="登 录" />
								</div>
							</dd>
						</dl>

					</form>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>