<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listresult.jsp</title>
<script>
/* $(function(){
	$("a").click(function(){
		var url ="./down"
		var data = "fileName=" + $(this).html().trim();
		$.ajax({
			url:url,
			data:data,
			success:function(data){
				alert("download OK");
			}
		});
		return false;
	});
}); */
</script>
</head>
<body>
<c:forEach var = "i" items="${requestScope.list}">
	<a href="./down?fileName=${i}">${i}</a>
</c:forEach>
</body>
</html>