<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bsh40_mvc.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/test/com/css/product.css">
</head>
<body>
	<div class="products">
		<div class="product-list">
			<c:forEach var="vo" items="${productList}">
				<div class="product">
					<a href="javascript:funcgo(${vo.product_serial});"> 
					<img src="/test/com/image/ThumbNail/${vo.product_picture_url}"
						width="120px" height="120px" />
					</a>
					<p>${vo.product_name}</p>
					<p>조회수 : ${vo.product_good}</p>
					<fmt:formatNumber value="${vo.product_price }" pattern="#,###원" />
				</div>
			</c:forEach>
		</div>
	</div>
	<form method="post" class="frm" name="frm"
		style="display: inline-block;" action="./productPage.do">
		<input type="hidden" name="product_serial" value="">
	</form>
	<script type="text/javascript">
		function funcgo(serial) {
			$.ajax({
				url : './good.do',
				type : 'post',
				data : {
					serial_num : serial
				},
				success : function(data) {
					var frm = document.frm;
					frm.product_serial.value = serial;
					$('.frm').submit();
				}
			});
		}
	</script>
</body>
</html>