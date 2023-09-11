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
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	width: 1080px;
	margin: 0 auto;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function kakaopost() {
		new daum.Postcode({
    		oncomplete: function(data) {
       		document.querySelector("#zipcode").value = data.zonecode;
       		document.querySelector("#address").value =  data.address
    		}
		}).open();
	}
	function checkNull() {
		if($('#recipient').val()=="") {
			alert('받는 사람을 입력해 주세요');
			return false;
		}else if ($('#zipcode').val() == "" || $('#address').val()== "") {
			alert(' 기본 주소를 입력해주세요');
			return false;
		}else if ($('#address_detail').val() == "") {
			alert('상세주소를 입력해주세요.');
			return false;
		}
	}
</script>
</head>
<body>
<div>
<jsp:include page="./Header.jsp"/>
<jsp:include page="./header1.jsp"/>
</div>
<div style="margin-top: 40px;">
<form method="post" action="./payment.do" name="payment" class="payment" onsubmit="return checkNull()">
<table border="1" style="text-align: center" class="table">
	<thead>
		<tr>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
<c:forEach var = "vo" items = "${productlist}">
	<tr>
		<td>
			<a href="javascript:funcgo(${vo.product_serial});">
			<img src="/test/com/image/ThumbNail/${vo.product_picture_url}" width="60px" height="60px"/>
			</a>
		</td>
		<td> 상품명 : ${vo.product_name}</td>
		<td> <p class="price"> 가격 : <fmt:formatNumber value="${vo.product_price}" pattern="#,###원" /></p></td>
        <td> <p>선택 수량 : ${vo.product_cnt}</p></td>
        <td> <p>합친가격 : <fmt:formatNumber value="${vo.product_price * vo.product_cnt}" pattern="#,###원"/></p></td>
 		<td class="allPrice">
        	<input type="hidden" class="individual_totalPrice_input" value="${vo.product_price * vo.product_cnt}">
 		</td>
	</tr>
</c:forEach>
	</tbody>
</table>
	<p>총 결제금액 : <span class="totalPrice_span"></span>원</p>
<%
  ProductDTO pv;
  ArrayList<ProductDTO> apv = (ArrayList<ProductDTO>)request.getAttribute("productlist2");
  ArrayList<ProductDTO> apv2 = (ArrayList<ProductDTO>)request.getAttribute("productlist");
  session.setAttribute("product", apv2);
  pv = apv.get(0);
%>
				받는 사람 : <input type="text" size="30" name="recipient" id="recipient" value="<%=pv.getProduct_user_recipient()%>" style="border:none;">
				<br><input type="button" value="우편번호찾기" onclick="kakaopost()" style="margin-top:10px;">
            	<br><input type="text" style="margin-top:10px;" placeholder="우편 번호" name="zipcode" id="zipcode" size="30"  value="<%=pv.getProduct_user_zipcode()%>" style="margin-top:5px;" readonly="readonly"> 
            	<br><input type="text" placeholder="주소" name="address" id="address" size="30" value="<%=pv.getProduct_user_addr()%>"style="margin-top:5px;" readonly="readonly">
        		<br><input type="text" placeholder="상세 주소" name="address_detail" id="address_detail" maxlength="30" size="30" value="<%=pv.getProduct_user_addrDetail()%>"style="margin-top:5px;">
    			<h3>주문시 요청사항</h3>
					<select name="requests">
						<option value="조심히 배달 해 주세요">조심히 배달 해 주세요</option>
						<option value="문 앞에 놓고 가세요">문 앞에 놓고 가세요</option>
						<option value="경비실에 맞겨 주세요">경비실에 맞겨 주세요</option>
				</select>
				<h2>결제 방법</h2>
					<select name="method">
						<option value="카드">카드</option>
						<option value="무통장">무통장</option>
						<option value="공찌">공짜</option>
				</select>
				<input type="hidden" name="pdt_code" value="<%=pv.getProduct_code()%>">
				<input type="hidden" name="user_id" value="<%=String.valueOf(session.getAttribute("userid"))%>">
				<input type="hidden" name="pdt_regi" value="<%=pv.getProduct_regi()%>">
				<input type="hidden" name="pdt_price" value=<%=pv.getProduct_price()%>>
				<button type="submit">결제하기</button>
		</form>
		</div>
		<script>
			$(document).ready(function(){
				let totalPrice = 0;
				$(".allPrice").each(function(index, element) {
					totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
				});
				$(".totalPrice_span").text(totalPrice.toLocaleString());
			});
		</script>
</body>
</html>