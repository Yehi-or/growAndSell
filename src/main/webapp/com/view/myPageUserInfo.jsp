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
        margin:0 auto; 
      }
</style>
<jsp:include page="./myPage.jsp"/>
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
</script>
<%
	UserDTO ut;
	ArrayList<UserDTO> list = (ArrayList<UserDTO>)request.getAttribute("userInfo");
	ut = list.get(0);
%>
</head>
<body>
	<div style="margin : auto; text-align: center; margin-top: 40px;">
		<h1>내 정보 수정</h1>
		<div style="margin-top: 40px;">
		<form method="post" name="frm" action="./myInfoUpdate.do">
				<p style="margin: 10px auto 0;">이름</p>
            	<input type="text" name="name" required class="id" id="nickname" maxlength="20" value="<%=ut.getUserName()%>" size="35">
            	<div style="margin-top: 15px;">
            	<br><input type="button" value="우편번호찾기" onclick="kakaopost()" class="btn btn-outline-primary btn-block">
            	</div>
            	<div>
            	<p style="margin: 10px auto 0;">기본 배송지 주소</p>
				<input type="text" placeholder="우편 번호" name="zipcode" id="zipcode" size="35"  value="<%=ut.getUserZipcode()%>" readonly="readonly" style="margin: 5px auto 0;"> 
				<br><input type="text" placeholder="주소" name="address" id="address" size="35" value="<%=ut.getUserAddress()%>" readonly="readonly" style="margin: 5px auto 0;">
        		<br><input type="text" placeholder="상세 주소" required name="address_detail" id="address_detail" maxlength="30" size="35" value="<%=ut.getUserAddressDetail()%>" style="margin: 5px auto 0;">
    			<br><input type="hidden" name="user_id" value="<%=String.valueOf(session.getAttribute("userid"))%>"style="margin: 5px auto 0;">
            	<br><button type="submit" class="btn btn-outline-primary btn-block">수정</button>
            	</div>
		</form>
		</div>
	</div>
</body>
</html>