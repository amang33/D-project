<!-- 2021.11.25 윤상현 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="container-fluid">
	<div class="row">
		<div class="col-lg-3 offset-lg-1 product_02_main_img">
			<img src="${contextPath}/resources/img/common/img-box.jpg">
		</div>
		<div class="col-lg-1 product_02_sub_img">
			<img class="product_02_sub_img_top" src="${contextPath}/resources/img/common/img-box.jpg"> 
			<img src="${contextPath}/resources/img/common/img-box.jpg"> 
			<img src="${contextPath}/resources/img/common/img-box.jpg">
		</div>
		
		<div class="col-lg-5 offset-lg-1">
			<div class="product_02_title">[상품 상세 이름]</div>
			<div class="product_02_discript">[상품 간편 설명]</div>
			<div><span class="product_02_option">옵션</span></div> 

			
			<div class="product_02_form">
			<span class="product_02_option">수량</span> 
			
			<form name="itemCountBox" id="itemCountBox_form">
  			<div class="value-button" id="cart_decrease" onclick="decreaseValue()" value="Decrease Value">-</div>
  			<input type="number" id="cart_item_count" value="1" />
  			<div class="value-button" id="cart_increase" onclick="increaseValue()" value="Increase Value">+</div>
			</form>	
			</div>
			<hr>
			<img src="${contextPath}/resources/img/common/product_buy_now.png">
			<img src="${contextPath}/resources/img/common/product_get_cart.png">
			

		</div>
	</div>

	<div class="row">
		<div class="col-lg-10 offset-lg-1 text-left">
			<a href="#">상품 상세정보</a> <a href="#">고객 후기</a> <a href="#">배송 / 교환
				/반품 안내</a> <a href="#">상품 문의</a>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-10 offset-lg-1">
			<img src="${contextPath}/resources/img/common/img-box.jpg">
		</div>

	</div>



</div>

<script>
/* 수량 증감 버튼 */
function increaseValue() {
	  var value = parseInt(document.getElementById('cart_item_count').value, 10);

	  value = isNaN(value) ? 0 : value;
	  value++;
	  document.getElementById('cart_item_count').value = value;
	};

	function decreaseValue() {
	  var value = parseInt(document.getElementById('cart_item_count').value, 10);
	    if(value<=1){
	        alert("수량은 1보다 작을 수 없습니다.")
	    };
	  value = isNaN(value) ? 0 : value;
	  value < 2 ? value = 2 : '';
	  value--;
	  document.getElementById('cart_item_count').value = value;
	};
	 window.onload = eventPlus();

	             function eventPlus() {
	                  document.itemCountBox.cart_item_count.onblur = eventGo;
	             }
	              function eventGo() {
	                   if ( this.value == "" ) {
	                      alert( '수량을 입력해주세요.' );
	                       document.getElementById('cart_item_count').value = 1;       
	                   } 
	                  if (this.value <1) {


	                      alert("수량은 1보다 작을 수 없습니다.");
	                     document.getElementById('cart_item_count').value = 1;

	                   }

	              }
</script>