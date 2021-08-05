let index = {
	init: function() {
		$('#btn-save').on("click", () => {  //function(){}, ()=>{} this를 바인딩하기 위해서!!
			this.save();
		});
		$('#btn-delete').on("click", () => {  //function(){}, ()=>{} this를 바인딩하기 위해서!!
			this.deleteById();
		});
		$('#btn-update').on("click", () => {  //function(){}, ()=>{} this를 바인딩하기 위해서!!
			this.update();
		});
		$('#btn-reply-save').on("click", () => {  //function(){}, ()=>{} this를 바인딩하기 위해서!!
			this.replySave();
		});
		$(document).on("click", '#btn-reply-update', () => {  //function(){}, ()=>{} this를 바인딩하기 위해서!!
			this.replyUpdate();
		});
	},
	save: function() {
		let title = $("#title").val();
		let content = $("#content").val();

		if (title == "") {
			alert("제목을 입력하세요");
			$("#title").focus();
			return false;
		}

		if (content == "") {
			alert("내용을 입력하세요");
			$("#content").focus();
			return false;
		}

		let data = {
			title: title,
			content: content
		};

		$.ajax({

			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	},

	deleteById: function() {
		let id = $("#id").text();

		$.ajax({

			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"

		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	},

	update: function() {
		let id = $('#id').val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({

			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			alert("수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})

	},

	replySave: function() {

		if ($("#reply-content").val() == "") {
			alert('내용을 입력하세요');
			return false;
		}

		let data = {
			userId: $("#userId").val(),
			content: $("#reply-content").val(),
			boardId: $("#boardId").val()
		};
		console.log(data);
		$.ajax({

			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"

		}).done(function(resp) {
			if (resp.content != "") {
				alert("댓글작성이 완료되었습니다.");
				console.log(resp);
				location.href = `/board/${data.boardId}`;
			} else {
				alert("내용을 입력하세요");
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	},

	replyDelete: function(boardId, replyId) {
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"

		}).done(function(resp) {
			alert("댓글삭제가 완료되었습니다.");

			location.href = `/board/${boardId}`;

		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	},

	replyUpdate: function() {

		let boardId = $("#boardId").val();
		
		let data = {
			replyId: $('#replyId').val(),
			content: $("#reply-editContent").val()
		}
		$.ajax({
			type: "PUT",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			url: `/api/board/${boardId}/reply/${data.replyId}`,
			dataType: "json"
		}).done(function(resp) {

			alert("댓글 수정이 완료되었습니다.")
			location.href = `/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	}
}

function updateViewBtn(replyId) {

	var htmls = "";
	htmls += "<form><div id='reply-" + replyId + "class='list-group-item d-flex justify-content-between'>";
	htmls += "<input type='hidden' id='replyId' value=" + replyId + ">";
	htmls += `<div class="card-body"><textarea id="reply-editContent" class="form-control" rows="1" cols=""></textarea>`;
	htmls += "<button type='button' id= 'btn-reply-update' class='badge' style='color: black;'>완료</button></div></div></form>";


	$("#reply-" + replyId).replaceWith(htmls);
	$('#reply-editContent').focus();
}

index.init();