<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
<header class="masthead" style="background-image: url('/image/home-bg.jpg')">
            <div class="container position-relative px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <div class="site-heading">
                            <h1>Yong's blog</h1>
                            <span class="subheading">Let's start myBlog</span>
                        </div>
                    </div>
                </div>
            </div>
        </header>

<div class="container">
	<div class="row">
		<div class="col card" style="margin: .9rem!important">
			<img class="img-thumbnail card-img-top" src="/image/kim.jpg" alt="Card image" style="width: 100%">
			<div class="card-body">
				<h4 class="card-title" style="text-align:center">YongSeok</h4>
				<p class="card-text">배우는 개발자 김용석입니다.</p>
			</div>
		</div>
		
		<c:forEach var="board" items="${boards.content}">
			<div class="col card m-2" style="margin: .9rem!important; height: 15rem;">
				<div class="card-body">
					<h4 class="card-title">${board.title}</h4>
					<a href="/board/${board.id}" class="btn btn btn-primary">상세보기</a><br>
					<span>조회수 ${board.count}</span>
				</div>
			</div>
		</c:forEach>
		
	</div>
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${boards.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${startBlockPage-2}"><</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${startBlockPage-2}"><</a></li>
			</c:otherwise>
		</c:choose>

		<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
			<c:choose>
				<c:when test="${boards.pageable.pageNumber+1 == i}">
					<li class="page-item disabled"><a class="page-link" href="?page=${i-1}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${endBlockPage}">></a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${endBlockPage}">></a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>

<%@ include file="layout/footer.jsp"%>