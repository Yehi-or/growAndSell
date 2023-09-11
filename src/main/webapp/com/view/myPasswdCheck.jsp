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
	margin: 0 auto;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	$('#mem_passwd').blur(function(){
    	if($('#mem_passwd').val()==0){
     	     $('#result').text('비밀번호를 입력하세요');
     	     return;
     	    }
    	});
    });
    
    function check() {
 	      $.ajax({
 	         url:'./login3.do',
 	         type:'post',
 	         data: {mem_id: '<%=String.valueOf(session.getAttribute("userid"))%>', mem_pass:$('#mem_passwd').val()},
 	     	 success : function(data) {
 	         	obj = JSON.parse(data);
 	         	if(obj.result == "ok"){
 	        		window.location.href = "./myPasswdUpdate.do";
				} else if (obj.result == "fail") {
					$('#result').text("비밀번호가 일치 하지 않습니다.");
				}
			}
			});
	}
</script>
</head>
<body>
<div>
<jsp:include page="./myPage.jsp"/>
</div>
<div style="margin-top: 40px;">
<div style="margin : auto; text-align: center; margin-top: 40px;">
	<h2>현재 비밀번호를 입력해 주세요</h2>
	현재 비밀번호 : <input type="text" id="mem_passwd" name="mem_passwd" size="30" style="margin: 10px auto 0;"><p id="result"></p>
	<input type="button" id="check" onclick="check()" value="확인" class="btn btn-outline-primary btn-block">
</div>
</div>
</body>
</html>