<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script>

function checkInput() {
	var inputed = $('#firstFile').val();
	var form = document.fileForm;
	
	var file1 = form.product_file1.value;
	var file2 = form.product_file2.value;
	var file3 = form.product_file3.value;
	
	var idx1 = file1.lastIndexOf('.');
	var idx2 = file2.lastIndexOf('.');
	var idx3 = file3.lastIndexOf('.');
	
	var extension1 = file1.substring(idx1 + 1, file1.length);
	var extension2 = file2.substring(idx2 + 1, file2.length);
	var extension3 = file3.substring(idx3 + 1, file3.length);
	var check = /^[0-9]+$/; 
	
	var arr = [];
	
	arr[0] = extension1.toLowerCase();
	arr[1] = extension2.toLowerCase();
	arr[2] = extension3.toLowerCase();
	
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
	}else if(file1 == "" && file2 == "" && file3 == "") {
		alert("이미지 파일을 하나 이상 넣어야 합니다.");
		form.product_file1.focus();
		return false;
	}else if((arr[0] != 'jpg' && arr[0] != 'png' && arr[0] != '') || (arr[1] != 'jpg' && arr[1] != 'png' && arr[1] != '') ||
			  (arr[2] != 'jpg' && arr[2] != 'png' && arr[2] != '')) {
		alert('파일 확장자 명은 jpg, png만 가능합니다.');
		return false;
	}else {
		form.submit();
		
	}
}
</script>
</head>
<body>
<div style="margin : auto; text-align: center;">
<c:set var="check" value = "${val}" scope="request"/>
<c:if test="${check.get(0) eq '모종'}">
<form name="fileForm" class="fileForm" method="post" action="./productInput.do" enctype="multipart/form-data" onsubmit="return checkInput()">
    <h2>모종상품등록하기</h2>
	<select name="mojong">
		<option value="야생화">야생화</option>
		<option value="채소">채소</option>
		<option value="mojong기타">기타</option>
	</select>
	<br>상품이름 : <input type="text" name="product_name" id="product_name" size="30">
	<br>상품가격 : <input type="text" name="product_price" id="product_price" size="30">
	<br>타이틀 : <input type="text" name="product_title" id="product_title" size="30">
	<br>상품설명 : <input type="text" name="product_description" id="prodcut_description" size="30">
	<br>파일1 : <input type="file" name="product_file1" id="firstFile" onclick="check()"><br>
	<br>파일2 : <input type="file" name="product_file2"><br>
	<br>파일3 : <input type="file" name="product_file3"><br>
	<br>상품 등록자 : <input type="text" style="border:0 solid black;" name="product_regi" value="<%=(String)session.getAttribute("userid")%>" readonly="readonly">
	<input type="hidden" name="action" value="모종">
	<button type="submit">등록하기</button>
</form>
</c:if>

<c:if test="${check.get(0) eq '묘목'}">
<form name="fileForm" class="fileForm" method="post" action="./productInput.do" enctype="multipart/form-data" onsubmit="return checkInput()">
    <h2>묘목상품등록하기</h2>
	<select name = "momok">
		<option value="과일나무">과일나무</option>
		<option value="꽃나무">꽃나무</option>
		<option value="momok기타">기타</option>
	</select>
	<br>상품이름 : <input type="text" name="product_name" id="product_name" size="30">
	<br>상품가격 : <input type="text" name="product_price" id="product_price" size="30">
	<br>타이틀 : <input type="text" name="product_title" id="product_title" size="30">
	<br>상품설명 : <input type="text" name="product_description"id="prodcut_description"  size="30">
	<br>파일1 : <input type="file" name="product_file1"><br>
	<br>파일2 : <input type="file" name="product_file2"><br>
	<br>파일3 : <input type="file" name="product_file3"><br>
	<br>상품 등록자 : <input type="text" style="border:0 solid black;" name="product_regi" value="<%=(String)session.getAttribute("userid")%>" readonly="readonly">
	<input type="hidden" name="action" value="묘목">
	<button type="submit">등록하기</button>
</form>
</c:if>
</div>


</body>
</html>