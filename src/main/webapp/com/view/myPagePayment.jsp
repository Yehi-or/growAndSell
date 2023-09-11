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
<jsp:include page="./myPage.jsp"/>
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
			<th>상품번호</th>
			<th>배송주소</th>
			<th>받는사람</th>
			<th>배송주문일</th>
			<th>상품가격</th>
			<th>상품수량</th>
			<th>총결제금액</th>
			<th>결제방식</th>
		</tr>
	</thead>
	<tbody>
<c:forEach var = "vo" items = "${productList}">
		<tr>
			<td>${vo.pay_pdt_code}</td>
			<td>${vo.pay_addr}</td>
			<td>${vo.pay_recipient}</td>
			<td>${vo.pay_date}</td>
			<td>${vo.pay_price}</td>
			<td>${vo.pdt_cnt_req}</td>
			<td>${vo.pdt_total_price}</td>
			<td>${vo.pay_method}</td>
		</tr>
</c:forEach>
	</tbody>
</table>
	<form method="post" class="frm" name="frm" style="display: inline-block;" action = "./productPage.do">
    	<input type = "hidden" name= "product_serial" value="">
	</form>
	<script type="text/javascript">
		function funcgo(serial){
			var frm = document.frm;
			frm.product_serial.value = serial;
			$('.frm').submit();
		}
	</script>
<div style="text-align : center; margin : 0 auto; display: inline-block;">
<a href="./myBuyProduct.do?currentPageNo=0">[FIRST]</a>
<%
if( currentPageNo > 0) {
%>
<a href="./myBuyProduct.do?currentPageNo=<%= (currentPageNo-1) %>"> [PRE]</a>
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
			<a href="./myBuyProduct.do?currentPageNo=<%=i%>">[<%=(i+1)%>]</a>
<%
		}
	}
%>	

<%
	if( currentPageNo <bpiVO.getPageCnt() - 1){
%>
	<a href="./myBuyProduct.do?currentPageNo=<%=(currentPageNo+1) %>">[NXT]</a>
<%
	}else{
%>
	[NXT]
<%
	}
%>
<a href="./myBuyProduct.do?currentPageNo=<%=(bpiVO.getPageCnt()-1)%>">[END]</a>
</div>
</div>
</body>
</html>