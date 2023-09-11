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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
var engNum =  /^[a-zA-Z0-9]*$/;

function checkPwd() {
        var inputed = $('#mem_passwd').val();
        var reinputed = $('#mem_passwd_check').val();
        if((!engNum.test(inputed) && !engNum.test(reinputed)) || !engNum.test(inputed) || !engNum.test(reinputed)) {
			$("#passwdCheck").text("형식에 맞지 않습니다.(영어 숫자만 가능)");
			$("#mem_passwd").css("background-color", "#FFCECE");
			$("#mem_passwd_check").css("background-color", "#FFCECE");
			$(".signupbtn").prop("disabled", true);
		}else if(engNum.test(inputed) && engNum.test(reinputed)) {
			$("#passwdCheck").text("");
			if((reinputed=="" || inputed=="") && (inputed != reinputed || inputed == reinputed)){
	            $(".signupbtn").prop("disabled", true);
	            $("#mem_passwd_check").css("background-color", "#FFCECE");
	        }
	        else if (inputed == reinputed) {
	            $("#mem_passwd_check").css("background-color", "#B0F6AC");
	            $("#mem_passwd").css("background-color", "#B0F6AC");
	            $(".signupbtn").prop("disabled", false);
	        } else if (inputed != reinputed) {
	            $(".signupbtn").prop("disabled", true);
	            $("#mem_passwd").css("background-color", "#FFCECE");
	            $("#mem_passwd_check").css("background-color", "#FFCECE");
	        }	
		}
    }
</script>
</head>
<body>
<jsp:include page="./myPage.jsp"/>
	<div style="margin-top: 40px;">
	<div style="margin : auto; text-align: center; margin-top: 40px;">
		<form name="frm" class="frm" method="post" action="passwdUpdateLast.do">
		변경할 비밀번호 <input type="password" size="30" name="mem_passwd" id="mem_passwd" oninput="checkPwd()" maxlength="18">
		<br>비밀번호 확인 <input type="password" size="30" name="mem_passwd_check" id="mem_passwd_check" oninput="checkPwd()" maxlength="18" style="margin: 5px auto 0;">
		<input type="hidden" name="user_id" value="<%=String.valueOf(session.getAttribute("userid"))%>">
		<p id=passwdCheck></p>
		<button type="submit" class="signupbtn" disabled="disabled">비밀번호 변경</button>
	</form>
	</div>
	</div>
</body>
</html>