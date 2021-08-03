let index = {
	init: function() {
		$('#btn-save').on("click", () => {  //function(){}, ()=>{} this를 바인딩하기 위해서!!
			this.save();
		});
		$('#btn-update').on("click", () => {  //function(){}, ()=>{} this를 바인딩하기 위해서!!
			this.update();
		});
	},
	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		// 한줄복사 ctrl+alt+방향키다운
		// ajax 호출시 default가 비동기 호출
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열
			// 생긴게 json이라면 =>javascript 객체로 변경

		}).done(function(resp) {
			if (resp.status == 500) {
				alert("회원가입에 실패하였습니다.")
			} else {
				alert("회원가입이 완료되었습니다.");
				console.log(resp);
				location.href = "/";  // 한줄주석 CRTL+SHIFT+C, 여러줄주석 CRTL+SHIFT+L 
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}) // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert  요청
	},

	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		console.log(data);
		$.ajax({
			// 회원가입 수행 요청
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열

		}).done(function(resp) {
			if (resp.status === 500) {
				alert("회원 수정이 실패하였습니다.");
			} else {
				alert("회원 수정이 완료되었습니다.");
				console.log(resp);
				location.href = "/";
			}
			//			location.href = "/";  // 한줄주석 CRTL+SHIFT+C, 여러줄주석 CRTL+SHIFT+L 
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}) // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert  요청
	}
}

index.init();