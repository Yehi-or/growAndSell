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
</head>
<body>
<jsp:include page="./Header.jsp"/>
<jsp:include page="./header1.jsp"/>
<div style="width: 1080px;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-evenly;
    margin-top: 40px;
    margin: auto;">
<a href="./allUser.do">전체 회원 조회</a>
<a href="./allProduct.do">전체 상품 조회</a>
<a href="./allPayment.do">전체 결제 조회</a>
</div>
</body>
</html>