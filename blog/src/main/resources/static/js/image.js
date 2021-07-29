
$(function() {
	$('.summernote').summernote({
		height: 300,                 // 에디터 높이
		minHeight: null,             // 최소 높이
		maxHeight: null,             // 최대 높이
		focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		callbacks: {	//여기 부분이 이미지를 첨부하는 부분
			onImageUpload: function(files) {
				for (let i = 0; i < files.length; i++) {
					uploadSummernoteImageFile(files[i], this);
				}
			},
			onPaste: function(e) {
				var clipboardData = e.originalEvent.clipboardData;
				if (clipboardData && clipboardData.items && clipboardData.items.length) {
					for (let i = 0; i < clipboardData.items.length; i++) {
						let item = clipboardData.items[i];
					}
					if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
						e.preventDefault();
					}
				}
			}
		}
	});
})


/**
* 이미지 파일 업로드
*/
function uploadSummernoteImageFile(file, editor) {
	data = new FormData();
	data.append("file", file);
	$.ajax({
		data: data,
		type: "POST",
		url: "/uploadSummernoteImageFile",
		contentType: false,
		processData: false,
		success: function(data) {
			//항상 업로드된 파일의 url이 있어야 한다.
			$(editor).summernote('insertImage', data.url);
		}
	});
}