<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<header>
	<div class="h-100 d-flex align-items-center justify-content-between ">
		<img src="/image/logo.jpg" class="logo-size ml-4">
	<c:if test="${not empty userName }">
		<div class="d-flex">${userName }님&nbsp;&nbsp; <a href="/user/sign_out" class="mr-2">로그아웃</a> </div>
	</c:if>
	</div>	
</header>