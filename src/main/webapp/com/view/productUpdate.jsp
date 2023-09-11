<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="bsh40_mvc.model.*" %>
<%@ page import="java.util.*" %>
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
<script src="https://code.jquery.com/jquery-3.4.1.min.js?ver=1"></script>
<script src="/test/com/js/indexproduct.js?ver=1"></script>
<script>
var inputed = $('#firstFile'.val())

function checkInput() {
	
	var check = /^[0-9]+$/;
	
	if($('#product_name').val()=="") {
		alert('상품명을 입력해주세요');
		return false;
	}else if($('#product_price').val()=="") {
		alert('가격을 입력해주세요.');
		return false;
	}else if(!check.test($('#product_price').val())) {
		alert('가격에는 숫자만 입력가능합니다.');
		return false;
	}else if($('#product_title').val()=="") {
		alert('타이틀을 입력해주세요.');
		return false;
	}else {
		form.submit();
	}
}
</script>
</head>
<%
	ProductDTO pdto;
	ArrayList<ProductDTO> list = (ArrayList<ProductDTO>)request.getAttribute("productInfo");
	pdto = list.get(0);
%>
<body>
<jsp:include page="./myPage.jsp"/>
<div style="margin-top: 40px;">
<%
if(pdto.getProduct_category().equals("모종")) {
%>
	 <form name="fileForm" class="fileForm" method="post" action="./productUpdate2.do" onsubmit="return checkInput()">
		<select name="mojong">
			<option value="야생화">야생화</option>
			<option value="채소">채소</option>
			<option value="mojong기타">기타</option>
		</select>
	<br>상품이름 : <input type="text" name="product_name" id="product_name" size="30" value="<%=pdto.getProduct_name()%>">
	<br>상품가격 : <input type="text" name="product_price" id="product_price" size="30" value="<%=pdto.getProduct_price()%>">
	<br>타이틀 : <input type="text" name="product_title" id="product_title" size="30" value="<%=pdto.getProduct_title()%>">
	<br>상품설명 : <input type="text" name="product_description" id="prodcut_description" size="30" value="<%=pdto.getProduct_description()%>">
	<input type="hidden" name="cate" value="모종">
	<input type="hidden" name="pdt_code" value="<%=pdto.getProduct_code()%>">
	<button type="submit">수정하기</button>
	</form>
<%
 }else {
%>
<form name="fileForm" class="fileForm" method="post" action="./productUpdate2.do" onsubmit="return checkInput()">
	<select name="momok">
		<option value="과일나무">과일나무</option>
		<option value="꽃나무">꽃나무</option>
		<option value="momok기타">기타</option>
	</select>
	<br>상품이름 : <input type="text" name="product_name" id="product_name" size="30" value="<%=pdto.getProduct_name()%>">
	<br>상품가격 : <input type="text" name="product_price" id="product_price" size="30" value="<%=pdto.getProduct_price()%>">
	<br>타이틀 : <input type="text" name="product_title" id="product_title" size="30" value="<%=pdto.getProduct_title()%>">
	<br>상품설명 : <input type="text" name="product_description" id="prodcut_description" size="30" value="<%=pdto.getProduct_description()%>">
	<input type="hidden" name="cate" value="묘목">
	<input type="hidden" name="pdt_code" value="<%=pdto.getProduct_code()%>">
	<button type="submit">수정하기</button>
</form>
<%
 }
%>
</div>
</body>
</html>