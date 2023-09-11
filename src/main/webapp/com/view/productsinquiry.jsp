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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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
			<th>상품썸네일</th>
			<th>상품시리얼넘버</th>
			<th>상품코드</th>
			<th>상품이름</th>
			<th>상품가격</th>
			<th>상품카테고리</th>
			<th>상품등록자</th>
			<th>상품조회수</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
<c:forEach var = "vo" items = "${productList}">
		<tr>
			<td>
				<a href="javascript:funcgo(${vo.product_serial});">
				<img src="/test/com/image/ThumbNail/${vo.product_picture_url}" width="60px" height="60px"/>
				</a>
			</td>
			<td>${vo.product_serial}</td>
			<td>${vo.product_code}</td>
			<td>${vo.product_name}</td>
			<td>${vo.product_price}</td>
			<td>${vo.product_category}</td>
			<td>${vo.product_regi}</td>
			<td>${vo.product_good}</td>
			<td><button type="button" onclick="location.href='./managePdtDelete.do?pdt_code=${vo.product_code}&currentPageNo=<%=currentPageNo%>'">삭제</button></td>
		</tr>
</c:forEach>
	</tbody>
</table>
	<form method="post" class="frm" name="frm" style="display: inline-block;" action = "./productPage.do">
    	<input type = "hidden" name= "product_serial" value="">
	</form>
<script>
function funcgo(serial){
			var frm = document.frm;
			frm.product_serial.value = serial;
			$('.frm').submit();
		}
</script>
<div style="text-align : center; margin : 0 auto; display: inline-block;">
<a href="./allProduct.do?currentPageNo=0">[FIRST]</a>
<%
if( currentPageNo > 0) {
%>
<a href="./allProduct.do?currentPageNo=<%= (currentPageNo-1) %>"> [PRE]</a>
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
			<a href="./allProduct.do?currentPageNo=<%=i%>">[<%=(i+1)%>]</a>
<%
		}
	}
%>	

<%
	if( currentPageNo <bpiVO.getPageCnt() - 1){
%>
	<a href="./allProduct.do?currentPageNo=<%=(currentPageNo+1) %>">[NXT]</a>
<%
	}else{
%>
	[NXT]
<%
	}
%>
<a href="./allProduct.do?currentPageNo=<%=(bpiVO.getPageCnt()-1)%>">[END]</a>
</div>
</div>
</body>
</html>