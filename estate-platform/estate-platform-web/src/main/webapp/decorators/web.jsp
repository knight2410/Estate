<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><dec:title default="Home" /></title>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<%--web-home--%>
	<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
	<link rel="stylesheet" href="<c:url value='/template/home/css/open-iconic-bootstrap.min.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/animate.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/owl.carousel.min.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/owl.theme.default.min.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/magnific-popup.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/aos.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/ionicons.min.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/bootstrap-datepicker.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/flaticon.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/icomoon.css' />" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value='/template/home/css/style.css' />" type="text/css" media="all">

	<!-- css -->
	<%--<link href="<c:url value='/template/web/css/style.css' />" rel="stylesheet" type="text/css" media="all"/>--%>
	<%--<link href="<c:url value='/template/web/css/slider.css' />" rel="stylesheet" type="text/css" media="all"/>--%>
	
	<!-- jquery -->
	<script type="text/javascript" src="<c:url value='/template/web/js/jquery-1.7.2.min.js' />"></script>
	
	<link rel="stylesheet" href="<c:url value='/template/admin/assets/css/bootstrap.min.css' />" />
	
	<%--<script src="<c:url value="/template/web/js/paging/jquery.twbsPagination.js"/>" type="text/javascript"></script>--%>

</head>
<style type="text/css">
	option {
		background-color: #26baee;
	}
</style>
<body>
	<%--<div class="wrap">--%>
		<!-- header -->
		<%@ include file="/common/web/header.jsp" %>
		<!-- header -->		
		<dec:body/>
	<%--</div>--%>
	
	<!-- footer -->
	<%@ include file="/common/web/footer.jsp" %>
	<!-- footer -->
	
	<%--<script type="text/javascript">--%>
		<%--$(document).ready(function () {--%>
			<%--$().UItoTop({--%>
				<%--easingType: 'easeOutQuart'--%>
			<%--});--%>

		<%--});--%>
	<%--</script>--%>
	<a href="#" id="toTop">
		<span id="toTopHover"> </span>
	</a>
	
	<!-- javascript -->
	<%--web-home--%>
	<script type="text/javascript" src="<c:url value='/template/home/js/jquery.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/jquery-migrate-3.0.1.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/popper.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/bootstrap.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/jquery.easing.1.3.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/jquery.waypoints.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/jquery.stellar.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/owl.carousel.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/jquery.magnific-popup.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/aos.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/jquery.animateNumber.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/home/js/bootstrap-datepicker.js' />"></script>
	<%--<script type="text/javascript" src="<c:url value='/template/home/js/jquery.timepicker.min.js' />"></script>--%>
	<script type="text/javascript" src="<c:url value='/template/home/js/scrollax.min.js' />"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<%--<script type="text/javascript" src="<c:url value='/template/home/js/google-map.js' />"></script>--%>
	<script type="text/javascript" src="<c:url value='/template/home/js/main.js' />"></script>
	<%--<script type="text/javascript" src="<c:url value='/template/web/js/move-top.js' />"></script>--%>
	<%--<script type="text/javascript" src="<c:url value='/template/web/js/easing.js' />"></script>--%>
	<%--<script type="text/javascript" src="<c:url value='/template/web/js/startstop-slider.js' />"></script>--%>
	
</body>
</html>