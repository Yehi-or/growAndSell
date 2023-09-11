<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

.divv {
	display: inline-block;
	float : left;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
function check() {
	var session = "<%=(String)session.getAttribute("loginState")%>";
	
		if (session == "login") {
			$.ajax({
				url : './bookmark.do',
				type : 'post',
				data : {
					bookmark : $('#bookmark2').val(),
					bookmarkId : $('#bookmarkId').val(),
					pdt_cnt : $(".quantity").val()
				},
				success : function(data) {
					obj = JSON.parse(data);
					if(obj.result == "fail") {
						alert('장바구니에 추가되었습니다.');
					}else if(obj.result == "ok") {
						alert('이미 장바구니에 등록된 상품입니다.');
					}
				}
			});
		} else {
			alert('로그인 후 이용하세요');
			window.location.href = "./login2.do";
		}
	};

function checkBuy() {
	var form = document.buy
	var session = '<%=(String) session.getAttribute("loginState")%>';
	if(session != "login") {
		alert('로그인 후 이용하세요');
		window.location.href = "./login2.do";
	}else if(session == "login") {
		let pdt_cnt = "<input name='product_cnt' type='hidden' value= '" + $(".quantity").val() + "'>";
		pdt_cnt += "<input name='pdt_code' type='hidden' value= '" + $("#code_num").val() + "'>";
		pdt_cnt += "<input name='user_id' type='hidden' value= '<%=String.valueOf(session.getAttribute("userid"))%>'>";
		$('.buy').html(pdt_cnt);
		$('.buy').submit();
	}
};
</script>
</head>
<body>
<%
  ProductDTO pv;
  ArrayList<ProductDTO> apv = (ArrayList<ProductDTO>)request.getAttribute("product");
  pv = apv.get(0);
  ArrayList<String> arr = pv.getProduct_image();
%>
<div>
<jsp:include page="./Header.jsp"/>
<jsp:include page="./header1.jsp"/>
</div>
<div style="margin-top: 40px;">
<%
if(arr!=null) {
	for(int i=arr.size()-1; i>=0; i--) {
		%>
		<img src="/test/com/image/<%=arr.get(i)%>" width="250px" height="230px"/>		
		<%
	}	
}
%>
<p>상품명 : <%=pv.getProduct_name()%></p>
<p>상품 설명 : <%=pv.getProduct_description()%></p>
<p>가격 : <%=pv.getProduct_price()%></p>
<p>수량 : <input type="number" id="quantity" class="quantity" value="1" readonly="readonly" style="width:37px;border:0 solid black;">
<input type="hidden" class="pdt_cnt" value="<%=pv.getProduct_cnt()%>">
<span>
	<button id="plus_btn" onclick="plus()" style="width:35px; fontsize:20px;">+</button>
	<button id="minus_btn" onclick="minus()" style="width:35px; fontsize:20px;">-</button>
</span>
</p>
<%if(pv.getProduct_cnt() <= 100) {
	%>
	<p>재고수량 : <%=pv.getProduct_cnt()%></p>
	<%
}
%>
<div>
	<div style="float:left; width:auto; height:auto; margin-right: 5px">
		<form class="buy" name="buy" method="post" action="./buy.do">
		<input type="hidden" id="code_num" value="<%=pv.getProduct_code()%>">
		<input type="button" value="구매하기" onclick="checkBuy()" class="btn btn-outline-primary">
		</form>
	</div>
<%-- <button id="bookmark" onclick="check()" value="<%=pv.getProduct_serial()%>">즐겨찾기</button> --%>
		<input type="hidden" name="bookmark2" id="bookmark2" value="<%=pv.getProduct_serial()%>">
<%
 if(String.valueOf(session.getAttribute("loginState")).equals("login")) {
%>
		<input type="hidden" name="bookmarkId" id="bookmarkId" value="<%=String.valueOf(session.getAttribute("userid"))%>">
<%
 }
%>
	<div style="float:left; width:auto; height:auto;">
		<input type="button" id="bookmark" onclick="check()" value="장바구니" class="btn btn-outline-primary">
	</div>
</div>
<script>
let quantity = $("#quantity").val();
function plus() {
	if(quantity < $(".pdt_cnt").val()){
		$("#quantity").val(++quantity);	
	}
};
function minus() {
	if(quantity > 1){
		$("#quantity").val(--quantity);	
	}
};
</script>
</div>
</body>
</html>