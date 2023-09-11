<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="bsh40_mvc.model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/test/com/css/product.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	width: 1080px;
	margin: 0 auto;
}
</style>
<%
	PageInfoVO bpiVO;
	int currentPageNo;
	bpiVO = (PageInfoVO)session.getAttribute("pageInfoVO");	
	currentPageNo=bpiVO.getCurrentPageNo();
%>
</head>
<body>
	<jsp:include page="./Header.jsp" />
	<jsp:include page="./header1.jsp" />
	<div class="products">
		<div class="product-list">
			<c:forEach var="vo" items="${productList}">
				<div class="product">
					<a href="javascript:funcgo(${vo.product_serial});"> <img
						src="/test/com/image/ThumbNail/${vo.product_picture_url}"
						width="120px" height="120px" />
					</a>
					<p>${vo.product_name}</p>
					<p>조회수 : ${vo.product_good}</p>
					<fmt:formatNumber value="${vo.product_price }" pattern="#,###원" />
				</div>
			</c:forEach>
		</div>
	<form method="post" class="frm" name="frm"
		style="display: inline-block;" action="./productPage.do">
		<input type="hidden" name="product_serial" value="">
	</form>
	<script type="text/javascript">
		function funcgo(serial){
			$.ajax({
				url  : './good.do',
				type : 'post',
				data : { serial_num : serial },
				success : function(data) {
					var frm = document.frm;
					frm.product_serial.value = serial;
					$('.frm').submit();
				}
			});
		}
	</script>
<div style="text-align : center; margin : 0 auto; display: inline-block;">
	<a href="./imageSelect2.do?currentPageNo=0">[FIRST]</a>
	<%
if( currentPageNo > 0) {
%>
	<a href="./imageSelect2.do?currentPageNo=<%= (currentPageNo-1) %>">
		[PRE]</a>
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
	<a href="./imageSelect2.do?currentPageNo=<%=i%>">[<%=(i+1)%>]
	</a>
	<%
		}
	}
%>

	<%
	if( currentPageNo <bpiVO.getPageCnt() - 1){
%>
	<a href="./imageSelect2.do?currentPageNo=<%=(currentPageNo+1) %>">[NXT]</a>
	<%
	}else{
%>
	[NXT]
	<%
	}
%>
	<a href="./imageSelect2.do?currentPageNo=<%=(bpiVO.getPageCnt()-1)%>">[END]</a>
	</div>
	</div>
</body>
</html>