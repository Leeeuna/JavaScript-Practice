<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout.html</title>
<style>
.sub_news,.sub_news th,.sub_news td{border:0}
.sub_news a{color:#383838;text-decoration:none}
.sub_news{width:100%;border-bottom:1px solid #999;color:#666;font-size:12px;table-layout:fixed}
.sub_news caption{display:none}
.sub_news th{padding:5px 0 6px;border-top:solid 1px #999;border-bottom:solid 1px #b2b2b2;background-color:#f1f1f4;color:#333;font-weight:bold;line-height:20px;vertical-align:top}
.sub_news td{padding:8px 0 9px;border-bottom:solid 1px #d2d2d2;text-align:center;line-height:18px;}
.sub_news .date,.sub_news .hit{padding:0;font-family:Tahoma;font-size:11px;line-height:normal}
.sub_news .title{text-align:left; padding-left:15px; font-size:13px;}
.sub_news .title .pic,.sub_news .title .new{margin:0 0 2px;vertical-align:middle}
.sub_news .title a.comment{padding:0;background:none;color:#f00;font-size:12px;font-weight:bold}
.sub_news tr.reply .title a{padding-left:16px;background:url(첨부파일/ic_reply.png) 0 1px no-repeat}
header>h1 {
	text-align: center;
}
/*자식 선택자를 이용한 선택*/
header>nav>ul>li {
	display: inline-block;
	margin: 10px;
}

header>nav>ul>li>a {
	text-decoration: none;
}

header>nav>ul>li>a:hover {
	background-color: yellow;
	font-weight: bold;
}

section {
	margin: 30px 30px 30px 30px;
	padding-right: 30px;
	width: 100%;
	height: 500px;
	/*높이는 비율 조절이 불가능하기 때문에 px로 고정된 길이를 줘야한다 */
	/*height: 50%;*/
	/*section이 부모 역할을 할 때, 
	absolute 포지션인 자식은 부모를 상대 기준 삼아서 위치값을 조절*/
	position: relative;
}

.original {
	float: left;
	width: 70%;
	height: 100%;
}

article {
	/*display: inline-block;   	무조건 왼쪽 정렬로 됨 */
	/*float: left;				왼쪽 오른쪽 정렬 선택 가능 */
	border: 1px solid;
	/*width: 300px;				길이를 고정된 px로 설정하는 것 보다는 비율로 표현하는게 반응성이 좋다 */
	
	height: 30%; /*높이를 상위 요소에서 미리 설정해두면 하위 요소에서 비율을 쓸수 있다*/
	/*article의 영역에서 내용이 벗어날 경우에만 스크롤 표시 */
	overflow: auto;
	margin-top: 10px;
}

aside {
	border: 1px solid;
	background-color: teal;
	/*display: inline-block;*/
	float: right; /* 오른쪽 정렬 */
	/*width: 200px;*/
	width: 20%;
	height: 100%;
	text-align: center;
}

footer {
	background-color: gray;
	color: white;
	text-align: center;
	margin-top: 10px;
	padding: 15px;		/*안쪽 여백: padding*/
	position: absolute;
	bottom: 0;
	left: 0;	/*left=0 , right=0 == width:100%*/
	right: 0;	/*=0픽셀*/
}
</style>

</head>
<body>
  <header style="background-color: #123456; border: 1px solid;">
	<h1 style="color: white">KITRI</h1>
	<!-- 메뉴 생성하기 nav(navigator) -->
	<nav style="background-color: white;">
	 <!-- <ul>
	  
		<li><a href="#">게시판</a></li>
		<li><a href="#">공지사항</a></li>
		<li><a href="login.html">로그인</a></li>
		<li><a href="join.html">가입</a></li>
		<li><a href="display.html">시군구</a></li>
	  </ul> -->
	  <!--  menu.html포함 rd.include() -->
	  <jsp:include page="menu.jsp"/>
	</nav>
  </header>

  <section>
	<div class="original">
	  
<table class="sub_news" border="1" cellspacing="0" summary="게시판의 글제목 리스트">
<caption>게시판 리스트</caption>
<colgroup>
<col>
<col width="110">
<col width="100">
<col width="80">
</colgroup>
<thead>
<tr>
<th scope="col">글번호</th>
<th scope="col">부모글번호</th>
<th scope="col">게시물제목</th>
<th scope="col">작성자이름</th>
<th scope="col">작성시간</th>
</tr>
</thead>
<tbody>
<tr>
<td class="title">
<a href="#">게시판 제목이 들어갑니다</a>
<a class="comment" href="#"></a> 
</td>
<td class="name">글번호/td>
<td class="date">2008/02/14</td>
<td class="hit">1234</td>
</tr>
<tr class="reply">
<td class="title" style="padding-left:30px;">
<a href="#">블로그 개편 답글</a>
</td>
<td class="name">벤쿠버지사</td>
<td class="date">2008/02/14</td>
<td class="hit">1234</td>
</tr>

<!-- tr이 제목 1줄입니다 보일 list 갯수만큼 li반복합니다.-->
</tbody>
</table>
	</div>

	<!-- 사이드바 광고 등에 사용되는 semantic tag -->
	<aside>광고</aside>
  </section>

  <footer>주소: 서울시 구로구 디지털로 34길 연락처: 02-686-8301 </footer>
</body>
</html>