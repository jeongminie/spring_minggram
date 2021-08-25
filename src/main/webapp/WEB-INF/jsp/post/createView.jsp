<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>    
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	<link rel="stylesheet" href="/css/style.css">
<title>타임라인</title>
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />	
		<section class="d-flex justify-content-center align-items-center p-4">
			<div class="userNameInfo">
				<c:if test="${not empty userName }">
					${userName }
				</c:if>
			</div>
			<div class="text-box">
				<div class="d-flex justify-content-center align-items-center">
					<textarea style="border: none; outline: none" class="form-control col-10" rows="5" placeholder="내용을 입력해주세요 ..." id="contentInput"></textarea>
				</div>	
				<div class="d-flex justify-content-center align-items-center mt-2">
					<input type="file" accept="image/*" id="fileInput" class="col-10">
					<button type="button" class="btn btn-info btn-sm" id="saveBtn">업로드</button>
				</div>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	
	<script>
		$(document).ready(function(){
			$("#saveBtn").on("click", function(){
				var content = $("#contentInput").val().trim();
				
				if(content == null || content == "") {
					alert("내용을 입력하세요");
					return ;
				}
				
				var formData = new FormData();
				formData.append("content", content);
				formData.append("file", $("#fileInput")[0].files[0]);
				
				
				$.ajax({
					enctype:"multipart/form-data",
					type:"post",
					url:"/post/create",
					processData:false,
					contentType:false,
					data:formData,
					success:function(data){
						if(data.result == "success") {
							alert("글쓰기 성공");
							//location.href="";
						} else {
							alert("글쓰기 실패");
						}
					},
					error:function(e) {
						alert("error");
					}
				});
				
			});
		});
	
	
	</script>
	
</body>
</html>