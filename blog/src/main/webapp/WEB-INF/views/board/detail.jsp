<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
.btn {
	padding: .2rem .7rem;
}
</style>
<div class="container" style="margin-top: 3rem;">
	<div class="btn-group">
		<button class="btn btn-secondary" style="font-size: 1.0rem;" onclick="history.back()">돌아가기</button>
		<c:if test="${board.user.id == principal.user.id}">
			<a href="/board/${board.id}/updateForm" style="font-size: 1.0rem;" class="btn btn-warning">수정</a>
			<button id="btn-delete" style="font-size: 1.0rem;" class="btn btn-danger">삭제</button>
		</c:if>
	</div>
	<br> <br>
	<div>
		글번호 <span id="id"><i>${board.id}</i></span> 작성자 <span><i>${board.user.username} </i></span> 조회수 <span><i>${board.count} </i></span> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${board.createDate}</span>
	</div>
	<div>
		<h3>${board.title}</h3>
	</div>
	<hr>
	<div>
		<div>${board.content}</div>
	</div>
	<hr />
	<div class="card">
		<form>
			<input type="hidden" id="userId" value="${principal.user.id}" />
			 <input type="hidden" id="boardId" value="${board.id}" />
			<div>
				<div class="card-body">
					<textarea id="reply-content" class="form-control" rows="1" cols=""></textarea>
				</div>
				<div class="card-footer">
					<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
				</div>
			</div>
		</form>
	</div>
	<br />
	<div class="card">
		<div class="card-header">댓글리스트</div>
		<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${board.reply}">
				<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
						<c:if test="${reply.user.id == principal.user.id}">
							<button onClick="updateViewBtn(${reply.id})" class="badge" style="color: black;">수정</button>
							<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge" style="color: black;">삭제</button>
						</c:if>
					</div>
				</li>
			</c:forEach>

		</ul>
	</div>

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>