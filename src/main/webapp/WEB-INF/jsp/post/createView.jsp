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
  	
  	 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  	<link rel="stylesheet" href="/css/style.css">
<title>타임라인</title>
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />	
		<section>
			<div class="d-flex justify-content-center align-items-center p-4">
			
				<div class="text-box rounded">
					<div class="userNameInfo mt-2 mb-2">
						<i class="bi bi-person-circle mr-2"></i>
						<c:if test="${not empty userName }">
							${userName }
						</c:if>
					</div>
					<div class="d-flex justify-content-center align-items-center">
						<textarea class="form-control col-10 border-0 non-resize" rows="5" placeholder="내용을 입력해주세요 ..." id="contentInput"></textarea>
					</div>	
					<div class="d-flex justify-content-between align-items-center m-2">
						<input type="file" accept="image/*" id="fileInput" class="col-10 mb-2 d-none">
						<a href="#" id="imageUploadBtn"><i class="bi bi-card-image mr-2 text-dark"></i><span class="text-dark">파일 첨부하기</span></a>
						<button type="button" class="btn btn-info btn-sm mb-2" id="saveBtn">업로드</button>
					</div>
				</div>
			</div>
			
			<c:forEach var="postWithComments" items="${post }" varStatus="status">
				<div class="d-flex justify-content-center align-items-center p-4">
					<div class="timeLine rounded">
						<div class="userNameInfo mt-2 mb-2 d-flex justify-content-between">
							<div>
								<i class="bi bi-person-circle mr-2"></i>
								<span>${postWithComments.post.userName }</span>
							</div>
							<i class="bi bi-three-dots mr-2"></i>
						</div>
						<div>
							<c:if test="${not empty postWithComments.post.imagePath }">
								<img src="${postWithComments.post.imagePath }" class="imagePath-size w-100">
							</c:if>
						</div>
						<div class="mt-2">
							<c:choose>
								<c:when test="${postWithComments.isLike eq false}">
									<a href="#" class="likeBtn" data-post-id="${postWithComments.post.id }">
										<i class="bi bi-heart text-dark ml-2"></i>
									</a>	
								</c:when>
								<c:otherwise>
									<a href="#" class="likeBtn" data-post-id="${postWithComments.post.id }">
										<i class="bi bi-heart-fill ml-2"></i>
									</a>	
								</c:otherwise>
							</c:choose>
							<i class="bi bi-chat"></i>
							<br>
							<div class="mt-1 ml-2">좋아요</div>		
						</div>
						
						<div class="ml-2">
							<b class="mr-2">${postWithComments.post.userName }</b>
							${postWithComments.post.content }
						</div>
						<div>
							<div>
								<span class="ml-2 text-secondary">댓글 보기</span>
								<c:forEach var="comment" items="${ postWithComments.commentList}">
									<div class="ml-2">
										<b>${comment.userName }</b> ${comment.comment }
									</div>
								</c:forEach>						
							</div>
							<div class="comment-box w-100 d-flex justify-content-center align-items-center" >
								<input type="text" class="form-control col-10 mt-2" id="commentInput-${postWithComments.post.id }" style="border:none" placeholder="댓글 달기...">
								<button class="btn btn-link btn-sm commentBtn" data-post-id="${postWithComments.post.id }">게시</button> 
							</div>
						</div>
					</div>			
				</div>
			</c:forEach>
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
				
				if($("#fileInput")[0].files.length ==0) {
					alert("파일을 추가하세요");
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
							alert("업로드 성공");
							 location.href="/post/create_view";
						} else {
							alert("업로드 실패");
						}
					},
					error:function(e) {
						alert("error");
					}
				});
				
			});
			
			$("#imageUploadBtn").on("click", function(){
				$("#fileInput").click();
			});
			
			$(".commentBtn").on("click", function(){
				//           버튼에 대한 정보를 가져옴 
				var postId = $(this).data("post-id");
				var comment = $("#commentInput-" + postId).val().trim();
				
				if(comment == null || comment == "") {
					alert("댓글을 입력하세요");
					return ;
				}
				
				$.ajax({
					type:"post",
					url:"/post/comment/create",
					data:{"postId":postId, "comment":comment},
					success:function(data) {
						if(data.result == "success") {
							location.reload();
						} else {
							alert("댓글 작성 실패");
						}
					},
					error:function(e) {
						alert("error");
					}
					
				});
			});
			
			$(".likeBtn").on("click", function(e){
				e.preventDefault();
				var postId = $(this).data("post-id");
				//a태그 기본 기능이 스크롤을 맨위로 이동시킴 이 기능을 삭제
				
				$.ajax({
					type:"get",
					url:"/post/like",
					data:{"postId":postId},
					success:function(data){
						if(data.result == "success") {
							location.reload();
						}
					},
					error:function(e) {
						alert("error");
					}
						
				});	
			});
			
			$("#logoClick").on("click", function(){
				location.reload();
			});
		});
	
	
	</script>
	
</body>
</html>