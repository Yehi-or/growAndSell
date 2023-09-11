<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="bsh40_mvc.model.*" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		
		if($('#Recipient').val()=="") {
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
<!--썸네일 있는걸로 받아옴 -->
<%
  ProductDTO pv;
  ArrayList<ProductDTO> apv = (ArrayList<ProductDTO>)request.getAttribute("productlist");
  int size = apv.size();
  pv = apv.get(0);
%>
<img src="/test/com/image/ThumbNail/<%=pv.getProduct_picture_url()%>" width="100px" height="100px"/>
<p><%=pv.getProduct_title()%></p>
<h3><%=pv.getProduct_price()%></h3>
		<form method="post" action="./payment.do" name="payment" class="payment" onsubmit="return checkNull()">
				받는 사람<input type="text" size="30" name="recipient" id="recipient" value="<%=pv.getProduct_user_recipient()%>">
				<table border="1">
        		<tr>
            	<td width="200">우편번호</td>
            	<td><input type="text" placeholder="우편 번호" name="zipcode" id="zipcode" size="7"  value="<%=pv.getProduct_user_zipcode()%>"> 
                <input type="button" value="우편번호찾기" onclick="kakaopost()"></td>
        		</tr>
        		<tr>
            	<td>주소</td>
            	<td><input type="text" placeholder="주소" name="address" id="address" size="70" value="<%=pv.getProduct_user_addr()%>"></td>
        		</tr>
        		<tr>
        		<td>상세주소</td>
        		<td><input type="text" placeholder="상세 주소" name="address_detail" id="address_detail" maxlength="30" size="70" value="<%=pv.getProduct_user_addrDetail()%>"></td>
    			</table>
    			<h2>주문시 요청사항</h2>
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
</body>
</html>