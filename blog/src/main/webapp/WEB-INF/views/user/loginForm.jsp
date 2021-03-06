<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
	<br/>
	<div class="container">
		<form action="/auth/loginProc" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text" name="username" class="form-control" placeholder="Enter Username" id="username">
			</div>
			
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> 
				<input class="form-check-input" id="rememberMe" name="remember-me" type="checkbox"> Remember me
				</label>
			</div>
			<button id="btn-login" class="btn btn-primary">로그인</button>
			<a href="#"><img src="/image/kakao_login_btn.png"></a>
		</form>
	</div>
	<%@ include file="../layout/footer.jsp"%>
