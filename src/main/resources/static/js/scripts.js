$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
	// event가 서버로 전송되지 않게 hold
	e.preventDefault();
	
	// 콘솔 프린트 출력
	console.log("click!!!");
	
	// 답변 내용 담기
	var queryStr = $(".answer-write ").serialize();
	console.log("queryStr : " + queryStr);
	
	// answer-write class의 action 정보 담기
	var url = $(".answer-write").attr("action");
	console.log("url : " + url);
	
	$.ajax({
		type : 'post',
		url : url,
		data : queryStr,
		dataType : 'json',
		error : onError,
		success : onSuccess
	}); 
}

function onError() {
	console.log("onError()");
}

function onSuccess(data, status) {
	console.log(data);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.writer.partnerId, data.formattedCreatedDate, data.contents, data.question.id, data.id); // 아래 템플릿 사용
	
	$(".qna-comment-partner-articles").prepend(template);
	
	$(".answer-write textarea").val("");
}

$("a.link-delete-article").click(deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault();
	
	var deleteBtn = $(this);
	var url = deleteBtn.attr("href");
	console.log("url : " + url);
	
	$.ajax({
		type : 'delete',
		url : url,
		dataType : 'json',
		error : function(xhr, status) {
			console.log("error");
		},
		success : function(data, status) {
			console.log("success : " + data);
			if (data.valid) {
				deleteBtn.closest("article").remove();
			} else {
				alert(data.errorMessage);
			}
		}
	});
}

// 동적 영역 리플래시를 위한 template
String.prototype.format = function() {
  var args = arguments;
  
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};
