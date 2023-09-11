<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="bsh40_mvc.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	width: 1080px;
	margin: 0 auto;
}
</style>

</head>
<body>
<jsp:include page="./Header.jsp" />
<jsp:include page="./header1.jsp" />
<%
	PageInfoVO bpiVO;
	int currentPageNo;
	bpiVO = (PageInfoVO)session.getAttribute("pageInfoVO");	
	currentPageNo=bpiVO.getCurrentPageNo();
%>
<div style="margin-top: 40px;">
		<div class="all_check_input_div">
			<input type="checkbox" class="all_check_input input_size_20" checked="checked"><span class="all_chcek_span">전체선택</span>
		</div>
<table border="1" style="text-align: center" class="table">
	<thead>
		<tr>
			<th></th>
			<th>상품이미지</th>
			<th>상품명</th>
			<th>가격</th>
			<th>상품수량</th>
			<th>합계</th>
			<th>장바구니 삭제</th>
		</tr>
	</thead>
	<tbody>
<c:forEach var = "vo" items = "${productList}">
	<tr>
		<td class="cart_info_td">
			<input type="checkbox" class="individual_cart_checkbox input_size_20" checked="checked">
			<input type="hidden" class="individual_productPrice_input" value="${vo.product_price}">
			<input type="hidden" class="individual_totalPrice_input" value="${vo.product_price * vo.product_cnt}">
			<input type="hidden" class="individual_bookId_input" value="${vo.product_code}">
			<input type="hidden" class="individual_pdt_cnt_input" value="${vo.product_cnt}">
		</td>
		<td>
			<a href="javascript:funcgo(${vo.product_serial});">
			<img src="/test/com/image/ThumbNail/${vo.product_picture_url}" width="60px" height="60px"/>
			</a>
		</td>
		<td> 상품명 : ${vo.product_name} </td>
 		<td>
 		 <p class="price">가격 : ${vo.product_price}</p>
 		 </td>
 		<td class="quantity_td">
			<input type="number" value="${vo.product_cnt}" class="quantity" readonly="readonly" style="width:37px;border:0 solid black;">
			<input type="hidden" class="individual_pdt_serial_input" value="${vo.product_serial}">
			<input type="hidden" class="pdtPrice" value="${vo.product_price}">
			<input type="hidden" class="pdtCnt" value="${vo.total_product_cnt}">
			<input type="hidden" class="total_price" value="${vo.product_price * vo.product_cnt}">
			<button class="quantity_btn plus_btn" style="width:35px;">+</button>
			<button class="quantity_btn minus_btn" style="width:35px;">-</button>
 		</td>
 		<td class="price_td">
			<p class="pdt_total_price">${vo.product_price * vo.product_cnt}원</p>
 		</td>
 		<td>
 			<form method="post" action="./basketDelete.do">
 				<input type="hidden" name="pdt_serial" value="${vo.product_serial}">
 				<input type="hidden" name="user_id" value="<%=String.valueOf(session.getAttribute("userid"))%>">
 				<input type="submit" value="삭제">
 			</form>
 		</td>
	</tr>
</c:forEach>
	</tbody>
</table>
	<form method="post" class="frm" name="frm" style="display: inline-block;" action = "./productPage.do">
    	<input type = "hidden" name= "product_serial" value="">
	</form>
	<form action="./order.do" method="post" class="order_form" name="order_form">
	</form>
	
	<div class="content_total_section">
				<div class="total_wrap">
					<table>
						<tr>
						<td>
						<table>
							<tr>
								<td>총 상품 가격</td>
								<td>
								<span class="totalPrice_span">70000</span> 원
								</td>
							</tr>								
									<!-- <tr>
										<td>총 주문 상품수</td>
										<td><span class="totalKind_span"></span>종 <span class="totalCount_span"></span>권</td>
									</tr> -->
						</table>
						</td>
						<td>
							<table>
								<tr>
									<td></td>
									<td></td>
								</tr>
							</table>							
						</td>
					</tr>
					</table>
					<table>
						<tr>
							<td>
								<table>
									<tbody>
										<tr>
											<td>
												<strong>총 결제 예상 금액</strong>
											</td>
											<td>
												<span class="finalTotalPrice_span">70000</span> 원
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 구매 버튼 영역 -->
			<div class="content_btn_section">
				<a class="order_btn">주문하기</a>
			</div>
	<script type="text/javascript">
		var session = '<%=(String)session.getAttribute("loginState")%>';	
	
		$(document).ready(function(){
			setTotalInfo();
		});
		
		$(".individual_cart_checkbox").on("change", function(){
			setTotalInfo($(".cart_info_td"));
		});
		
		/* 체크박스 전체 선택 */
		$(".all_check_input").on("click", function(){
			if($(".all_check_input").prop("checked")){
				$(".individual_cart_checkbox").attr("checked", true);
			} else{
				$(".individual_cart_checkbox").attr("checked", false);
			}
			
			setTotalInfo($(".cart_info_td"));
		});
		
		var arr = [];
	
		function funcgo(serial){
			var frm = document.frm;
			frm.product_serial.value = serial;
			$('.frm').submit();
		}
		
		function setTotalInfo() {
			let totalPrice = 0;
			let totalCount = 0;
			let finalTotalPrice = 0;
			$(".cart_info_td").each(function(index, element){
				if($(element).find(".individual_cart_checkbox").is(":checked") === true){
					totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
					totalCount += parseInt($(element).find(".individual_pdt_cnt_input").val());
				}
			});
			/* 최종 가격 */
			finalTotalPrice = totalPrice;
			$(".totalPrice_span").text(totalPrice.toLocaleString());
			$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());	
		}
		
		$(".order_btn").on("click", function(){
			var orderform = document.order_form;
			let form_contents ='';
			let orderNumber = 0;
			var arr = [];
			$(".cart_info_td").each(function(index, element){
				if($(element).find(".individual_cart_checkbox").is(":checked") === true){
				let bookId = $(element).find(".individual_bookId_input").val();
				let pdt_cnt = $(element).find(".individual_pdt_cnt_input").val();
				let bookId_input = "<input name='orders[" + orderNumber + "].bookId' type='hidden' value='" + bookId + "'>";
				bookId_input += "<input name='orders[" + orderNumber + "].pdt_cnt' type='hidden' value= '" + pdt_cnt + "'>";
				form_contents += bookId_input;
				orderNumber += 1;
				}
		});
			if(orderNumber==0) {
				alert('장바구니에 물건을 담아주세요!');
			}else{
				if(session=="login") {
					$(".order_form").html(form_contents);
					$(".order_form").submit();	
				}else {
					alert('로그인 후 이용하세요');
					window.location.href = "./login2.do";
				}
			}
	});
		//ajax 통신해서 실시간으로 장바구니 수량 업데이트 되고 가격 반영
		$(".plus_btn").on("click", function(){
			let quantity = $(this).parent(".quantity_td").find(".quantity").val();
			let cnt = $(this).parent(".quantity_td").find(".pdtCnt").val();
			if(parseInt(quantity) < parseInt(cnt)) {
				let pdt_serial = $(this).parent(".quantity_td").find(".individual_pdt_serial_input").val();
				$.ajax({
					url : './basketUpdate.do',
					type : 'post',
					data : {
						user_id : "<%=String.valueOf(session.getAttribute("userid"))%>",
						bookmark : pdt_serial,
						pdt_cnt : parseInt(quantity) + 1
					},
					success : function(data) {
						$(this).parent(".quantity_td").find(".quantity").val(++quantity);
						location.reload();
					}
				});
					
			}
		});
		$(".minus_btn").on("click", function(){
			let quantity = $(this).parent(".quantity_td").find(".quantity").val();
			if(quantity > 1){
				let pdt_serial = $(this).parent(".quantity_td").find(".individual_pdt_serial_input").val();
				$.ajax({
					url : './basketUpdate.do',
					type : 'post',
					data : {
						user_id : "<%=String.valueOf(session.getAttribute("userid"))%>",
						bookmark : pdt_serial,
						pdt_cnt : quantity - 1
					},
					success : function(data) {
						$(this).parent(".quantity_td").find(".quantity").val(--quantity);
						location.reload();
					}
				});
			}
		});
	</script>
<div style="text-align : center; margin : 0 auto; display: inline-block;">
<a href="./basket.do?currentPageNo=0">[FIRST]</a>
<%
if( currentPageNo > 0) {
%>
<a href="./basket.do?currentPageNo=<%= (currentPageNo-1) %>"> [PRE]</a>
<%
}else{
%>
[PRE]
<%
}
	for (int i = bpiVO.getStartPageNo(); i<bpiVO.getEndPageNo(); i++){
		if(i== currentPageNo ) {
%>
		[<%= (i+1)%>]
<%
		}else{
%>
			<a href="./basket.do?currentPageNo=<%=i%>">[<%=(i+1)%>]</a>
<%
		}
	}
%>	

<%
	if( currentPageNo <bpiVO.getPageCnt() - 1){
%>
	<a href="./basket.do?currentPageNo=<%=(currentPageNo+1) %>">[NXT]</a>
<%
	}else{
%>
	[NXT]
<%
	}
%>
<a href="./basket.do?currentPageNo=<%=(bpiVO.getPageCnt()-1)%>">[END]</a>
</div>
</div>
</body>
</html>