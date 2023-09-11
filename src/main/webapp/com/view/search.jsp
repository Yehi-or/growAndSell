<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
.search-box {
  width:100%;
  text-align: center;
}
</style>
</head>
<body>
<div class="search-box">
	<form method="post" action="./imageSelect2.do">
			<select name="search">
				<option value="allproduct">전체</option>
				<option value="pdt_name">상품명</option>
				<option value="pdt_cate">종류</option>
				<option value="pdt_sebu_cate">세부종류</option>
			</select>
		<input type= "text" name="searchValue" size="30" placeholder="검색어를 입력해 주세요">
		<button class="search-btn" type="submit">
		 <i class="fas fa-search"></i>
		</button>
		</form>
	</div>
</body>
</html>