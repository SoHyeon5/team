<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Favicon-->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/logo4.png">
<!-- Author Meta -->
<meta name="author" content="colorlib">
<!-- Meta Description -->
<meta name="description" content="">
<!-- Meta Keyword -->
<meta name="keywords" content="">
<!-- meta character set -->
<meta charset="UTF-8">
<!-- Site Title -->
<title>Being</title>

<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700"
	rel="stylesheet">
<!--
			CSS
			============================================= -->

<!-- <style>@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');</style> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/linearicons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/lightgallery/1.6.11/css/lightgallery.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/nice-select.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/animate.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/list.css">
</head>

<body>
	<%@ include file="../include/header.jspf"%>
<body>

	<div class="interior-list">
		<div class="search-board">
		<form action="listQuery" method="post">
			<div class="search-board">
				<select name="query">
					<option value="cname">?????? ??????</option>
				</select> <input type="text" name="content" size="30" placeholder="search">
				<button>
					<img class="serchicon"
						src="${pageContext.request.contextPath}/resources/img/searchicon.png"
						width="30px" height="30px">
				</button>
		</form>

		<c:if test="${login.lvl eq 1}">
			<p class="writebtn">
				<a class="btn"
					href="${pageContext.request.contextPath}/interior/newInteriorForm">??????
					????????????</a>
			</p>
		</c:if>
		</div>
		<c:forEach var="interiorVO" items="${listInterior}">
			<ul>
				<li>
					<!-- 		????????? --> <a
					href='${pageContext.request.contextPath}/interior/readInterior?num=${interiorVO.num}'>
						<img
						src="${pageContext.request.contextPath}/board/fileDownload?filename=${interiorVO.fileName}&downname=${interiorVO.fileRealName}"
						width="200px" height="200px" style="border-radius: 7px;">
						<div class="intname">
							<c:out value="${interiorVO.cname}" />
						</div>
				</a> <!-- 	?????? ?????? -->
<!-- 					<div> -->
<%-- 						<span> ${interiorVO.introduce}</span> --%>
<!-- 					</div> -->
				</li>
			</ul>
		</c:forEach>

		<table class="page">
			<%-- <c:if test="${interiorPage.hasInterior()}"> --%>
			<tr>
				<td colspan="4"><c:if test="${pageMaker.prev}">
						<li><a
							href="listInterior${pageMaker.makeSearch(pageMaker.startPage - 1) }">[??????]</a></li>
					</c:if> <c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<li
							<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
							<a href="listInterior${pageMaker.makeSearch(idx)}">${idx}</a>
						</li>
					</c:forEach> <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li><a
							href="listInterior${pageMaker.makeSearch(pageMaker.endPage +1) }">[??????]</a>
					</c:if></td>
			</tr>
			<%-- </c:if> --%>
		</table>
	</div>
	</div>

	<%@ include file="../include/footer.jspf"%>

	<script
		src="${pageContext.request.contextPath}/resources/js/vendor/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/vendor/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/hoverIntent.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/superfish.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.ajaxchimp.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/isotope.pkgd.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.lightbox.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/mail-script.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

	<!-- ?????????????????? -->
	<script src='${pageContext.request.contextPath}/resources/js/swipe.js'></script>
	<script
		src='${pageContext.request.contextPath}/resources/js/jquery.bxslider.min.js'></script>

</body>

</body>
</html>