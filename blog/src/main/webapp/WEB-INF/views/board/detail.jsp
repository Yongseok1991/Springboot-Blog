<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
.btn {
    padding: .2rem .7rem;
}
</style>
<div class="container" style="margin-top: 3rem;">
	<div class="btn-group">
		<button class="btn btn-secondary" style="font-size:1.0rem;" onclick="history.back()">돌아가기</button>
		<c:if test="${board.user.id == principal.user.id}">
			<a href="/board/${board.id}/updateForm" style="font-size:1.0rem;" class="btn btn-warning">수정</a>
			<button id="btn-delete" style="font-size:1.0rem;" class="btn btn-danger">삭제</button>
		</c:if>
	</div>
	<br> <br>
	<div>
		글번호 <span id="id"><i>${board.id}</i></span> 작성자 <span><i>${board.user.username} </i></span> 조회수 <span><i>${board.count} </i></span>
	</div>
	<div>
		<h3>${board.title}</h3>
	</div>
	<hr>
	<div>
		<div>${board.content}</div>
	</div>
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>