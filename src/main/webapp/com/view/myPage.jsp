<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="https://code.jquery.com/jquery-3.4.1.min.js?ver=1"></script>
<script>
var session = "<%=String.valueOf(session.getAttribute("loginState"))%>";
var user_id = "<%=String.valueOf(session.getAttribute("userid"))%>";
function go1() {
	if(session == "login") {
		window.location.href = "./myInfo.do?user_id=" + user_id;
	}else {
		alert('로그인 후 이용하세요');
		window.location.href = "./login2.do";
	}
}

function go2() {
	if(session == "login") {
		window.location.href = "./myProduct.do?user_id=" + user_id;
	}else {
		alert('로그인 후 이용하세요');
		window.location.href = "./login2.do";
	}
}

function go3() {
	if(session == "login") {
		window.location.href = "./myPasswd.do?user_id=" + user_id;
	}else {
		alert('로그인 후 이용하세요');
		window.location.href = "./login2.do";
	}
}

function go4() {
	if(session == "login") {
		window.location.href = "./myBuyProduct.do?user_id=" + user_id;
	}else {
		alert('로그인 후 이용하세요');
		window.location.href = "./login2.do";
	}
}

function go5() {
	if(session == "login") {
		window.location.href = "./userDelete.do?user_id=" + user_id;
	}else {
		alert('로그인 후 이용하세요');
		window.location.href = "./login2.do";
	}
}



</script>
</head>
<body>
<jsp:include page="./Header.jsp"/>
<jsp:include page="./header1.jsp"/>
<div style="width: 1080px;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-evenly;
    margin-top: 80px;
    margin: auto;">
<%--     <%if(session.getAttribute("loginState").equals("login")) {
    %> --%>
		<a href="javascript:go1()" style="margin-top: 50px">내정보 수정</a>
		<a href="javascript:go2()" style="margin-top: 50px">내가 등록한 상품</a>
		<a href="javascript:go3()" style="margin-top: 50px">내 비밀번호 변경</a>
		<a href="javascript:go4()" style="margin-top: 50px">내 구매 내역</a>
		<a href="javascript:go5()" style="margin-top: 50px">회원 탈퇴</a>    
<%--     <%	
    }else {
    %>
    	<a href="./login2.do">로그인하기</a>
    <%
    }
    %>
 --%>
</div>
</body>
</html>