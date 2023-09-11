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
<script src="/test/com/js/indexproduct.js?ver=1"></script>
</head>
<body>
<jsp:include page="./Header.jsp"/>
<jsp:include page="./header1.jsp"/>
	<div style="width: 100%;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-evenly;
    margin: 40px;">
		<a class="btn btn-secondary" href="javascript:mojong();">모종상품 등록하기</a>
		<a class="btn btn-secondary" href="javascript:momok();">묘목상품 등록하기</a>
	</div>
	<div id="product">
<jsp:include page="./productRegiSecond.jsp"/>
</div>
</body>
</html>