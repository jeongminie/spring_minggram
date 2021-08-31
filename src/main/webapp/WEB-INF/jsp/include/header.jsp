<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<header>
	<div class="h-100 d-flex align-items-center justify-content-between ">
		<a href="#"><img src="/image/logo.png" class="logo-size" id="logoClick"></a>
	<c:if test="${not empty userName }">
		<div class="d-flex">${userName }님&nbsp;&nbsp; <a href="/user/sign_out" class="mr-2">로그아웃</a> </div>
	</c:if>
	</div>	
</header>