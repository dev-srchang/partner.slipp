$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
	// event가 서버로 전송되지 않게 hold
	e.preventDefault();
	
	// 콘솔 프린트 출력
	console.log("click!!!");
	
	// 답변 내용 담기
	var queryStr = $(".answer-write ").serialize();
	console.log(queryStr);
	
	// answer-write class의 action 정보 담기
	var url = $(".answer-write").attr("action");
	console.log(url);
	
	$.ajax({
		type : 'post',
		url : url,
		data : queryStr,T
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
	
}
