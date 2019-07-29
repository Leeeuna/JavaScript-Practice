<%@page contentType="text/html;charset=utf-8"%>
<%
String contextPath = request.getContextPath();
%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
var loadBoardList = function(data){
	$("section").empty();
	$("section").html(data);
};

var loadLogin = function(data){
	$("section").empty();
	$("section").html(data);
	// $("section>div>article:nth-child(1)").html(data);
};
	
var loadJoin = function(data){
	$("section").empty();
	$("section").html(data);
	// $("section>div>article:nth-child(2)").html(data);
};

var loadMenu = function(u, callback){
	$.ajax({
		url: u,
		method: 'GET',
		success: function(data){
			// 정상 응답 시 동작할 함수
			callback(data);
		}	
	});
};

$(function(){
	var $menuArr = $("header>nav>ul>li>a");
	$menuArr.click(function(){
		var url = $(this).attr('href');
		console.log(url);
		switch(url){
		case '<%=contextPath%>/boardlist':
			loadMenu(url, loadBoardList);
			break;
		case '<%=contextPath%>/jq/login.html':
			loadMenu(url, loadLogin);
			break;
		case '<%=contextPath%>/jq/join.html':
			loadMenu(url, loadJoin);
			break;
		case '<%=contextPath%>/jq/display.html':
			loadMenu(url, function(data){
				$("section").empty();xxxxxxxxxxxxxxxxxxx
				$("section").html(data);
			});
			break;
		}
		return false; // 기본 이벤트 핸들러 막기 + 이벤트 전달 중지
	});
});
</script>

<ul>
	<li><a href='<%=contextPath%>/boardlist'>게시판</a></li>
	<li><a href="#">공지사항</a></li>
	<li><a href='<%=contextPath%>/jq/login.html'>로그인</a></li>
	<li><a href='<%=contextPath%>/jq/join.html'>가입</a></li>
	<li><a href='<%=contextPath%>/jq/display.html'>시군구</a></li>
</ul>
</body>
</html>