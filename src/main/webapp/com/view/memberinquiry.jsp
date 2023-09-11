<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="bsh40_mvc.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	width: 1080px;
	margin: 0 auto;
}
</style>
</head>
<body>
<div>
<jsp:include page="./managePage.jsp"/>
</div>
<%
	PageInfoVO bpiVO;
	int currentPageNo;
	bpiVO = (PageInfoVO)session.getAttribute("pageInfoVO");	
	currentPageNo=bpiVO.getCurrentPageNo();
%>
<div style="margin-top: 40px;">
<table border="1" style="text-align: center" class="table">
	<thead>
		<tr>
			<th>회원아이디</th>
			<th>회원이름</th>
			<th>회원우편번호</th>
			<th>회원주소</th>
			<th>회원상세주소</th>
			<th>회원등급</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
<c:forEach var = "vo" items = "${userList}">
		<tr>
			<td>${vo.userId}</td>
			<td>${vo.userName}</td>
			<td>${vo.userZipcode}</td>
			<td>${vo.userAddress}</td>
			<td>${vo.userAddressDetail}</td>
			<td>${vo.userCheck}</td>
			<td><button type="button" onclick="location.href='./userDelete.do?user_id=${vo.userId}&check=1'">삭제</button></td>
		</tr>
</c:forEach>
	</tbody>
</table>
<div style="text-align : center; margin : 0 auto; display: inline-block;">
<a href="./allUser.do?currentPageNo=0">[FIRST]</a>
<%
if( currentPageNo > 0) {
%>
<a href="./allUser.do?currentPageNo=<%= (currentPageNo-1) %>"> [PRE]</a>
<%
}else{
%>
[PRE]
<%
}
	for (int i = bpiVO.getStartPageNo(); i<bpiVO.getEndPageNo(); i++){
		if(i== currentPageNo ) {
%>
		[<%= (i+1)%>]
<%
		}else{
%>
			<a href="./allUser.do?currentPageNo=<%=i%>">[<%=(i+1)%>]</a>
<%
		}
	}
%>	

<%
	if( currentPageNo <bpiVO.getPageCnt() - 1){
%>
	<a href="./allUser.do?currentPageNo=<%=(currentPageNo+1) %>">[NXT]</a>
<%
	}else{
%>
	[NXT]
<%
	}
%>
<a href="./allUser.do?currentPageNo=<%=(bpiVO.getPageCnt()-1)%>">[END]</a>
</div>
</div>
</body>
</html>