<%@page import="com.my.vo.PageBean"%>
<%@page import="com.my.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%String contextPath = request.getContextPath();%>
<style>
div.board{
  width:70%;
}
div.tr{
  width:100%;  
  clear:both;
}
div.tr:hover{
  background-color: yellow;
  font-weight:bold;
}
div.board_no, div.board_subject, div.board_writer, div.board_time{
  width:30%;
  float:left;

}
div.board_no{
  width:10%;
  clear:both;
}

span.underline{
  text-decoration: underline;
  font-weight: bold;
}

button[id=prev]{
	display: none;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
    var $spanArr = $("div.pageGroup > span");
    $spanArr.click(function(){
    	var pageNum = $(this).html(); //1,2,3,4,5
    	$.ajax({
    		url: "<%=contextPath%>/boardlist",
    		data:'currentPage='+pageNum,
    		success:function(data){
    		    $("section").empty();
    		    $("section").html(data);    		
    		}    		
    	});//end ajax    	
    });//end click
});//end $(function(){
	
$(function(){
	var $prevObj = $("button[id=prev]");
	var $nextObj = $("button[id=next]");
	
	// id 입력란에 focus이벤트 감시: focus되엇을 시 submit 버튼 숨기기
	$idObj.focus(function(){
		$submitObj.hide();
	});	
}
	
</script>
<%
int status = (Integer)request.getAttribute("status");
if(status != 1){
%>게시물 목록이 없습니다	
<%
  return;
}	
PageBean pb = (PageBean)request.getAttribute("pb");
int currentPage = pb.getCurrentPage();
int maxPage = pb.getMaxPage();
int	cntPerPage = pb.getCntPerPage();
int totalCnt = pb.getTotalCnt();
int startPage = pb.getStartPage();
int endPage = pb.getEndPage();
List<Board> list = pb.getList();
%><h2>게시판</h2>
<div class="board">
<%
 for(Board b: list){
%>
<div class="tr">
<div class="board_no">
<%for(int i=1; i<b.getLevel(); i++){ %>&nbsp;&nbsp;<%} %>
<%=b.getBoard_no() %>
</div>
<div class="board_subject"><%=b.getBoard_subject() %></div>
<div class="board_writer"><%=b.getBoard_writer()%></div>
<div class="board_time"><%=b.getBoard_time()%></div><br><br>
</div>  
<%}//end for
%>
</div>
<div class="pageGroup">
<button type = "button" id="prev">PREV</button>
<%for(int i=1; i<=maxPage; i++){
    if(i == currentPage){
%>[<span><%=i%></span>]&nbsp;&nbsp;
<%  }else{
%>  
  [<span class="underline"><%=i%></span>]&nbsp;&nbsp;
<%  }//end if
}//end for %>

<button type = "button" id="next">NEXT</button>

</div>




