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
			<th>상품구매자아이디</th>
			<th>배송주소</th>
			<th>결제방법</th>
			<th>상품코드</th>
			<th>주문가격</th>
			<th>배송받을사람</th>
			<th>배송시요청</th>
			<th>상품등록자</th>
			<th>배송일자</th>
		</tr>
	</thead>
	<tbody>
<c:forEach var = "vo" items = "${paymentList}">
		<tr>
			<td>${vo.pay_user_id}</td>
			<td>${vo.pay_addr}</td>
			<td>${vo.pay_method}</td>
			<td>${vo.pay_pdt_code}</td>
			<td>${vo.pay_price}</td>
			<td>${vo.pay_recipient}</td>
			<td>${vo.pay_request}</td>
			<td>${vo.pay_pdt_regi}</td>
			<td>${vo.pay_date}</td>
		</tr>
</c:forEach>
	</tbody>
</table>
<div style="text-align : center; margin : 0 auto; display: inline-block;">
<a href="./allPayment.do?currentPageNo=0">[FIRST]</a>
<%
if( currentPageNo > 0) {
%>
<a href="./allPayment.do?currentPageNo=<%= (currentPageNo-1) %>"> [PRE]</a>
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
			<a href="./allPayment.do?currentPageNo=<%=i%>">[<%=(i+1)%>]</a>
<%
		}
	}
%>	

<%
	if( currentPageNo <bpiVO.getPageCnt() - 1){
%>
	<a href="./allPayment.do?currentPageNo=<%=(currentPageNo+1) %>">[NXT]</a>
<%
	}else{
%>
	[NXT]
<%
	}
%>
<a href="./allPayment.do?currentPageNo=<%=(bpiVO.getPageCnt()-1)%>">[END]</a>
</div>
</div>
</body>
</html>