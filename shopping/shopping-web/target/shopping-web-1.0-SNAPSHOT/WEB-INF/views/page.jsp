<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<spring:url var="css" value="/resources/css"/>
<spring:url var="jquery" value="/resources/jquery"/>
<spring:url var="images" value="/resources/images"/>
<spring:url var="bootstrap" value="/resources/bootstrap"/>
<spring:url value="/resources/js" var="js"/>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop Homepage - ${title}</title>

    <script>
        window.menu = '${title}';
        window.contextRoot = '${contextRoot}'
    </script>

    <!-- Bootstrap core CSS -->
    <link href="${bootstrap}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap bootstrap readable -->
    <link href="${bootstrap}/css/bootstrap-readable-theme.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${css}/shop-homepage.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs-3.3.7/dt-1.10.21/datatables.min.css"/>

</head>

<body>
<div class="wrapper">

    <!-- Navigation -->
    <%@include file="./shared/navbar.jsp" %>

    <!-- Page Content -->
    <div class="content">
        <!-- Loading the home content -->
        <c:if test="${userClickHome == true}">
            <%@include file="home.jsp" %>
        </c:if>

        <!-- Loading only when user click about -->
        <c:if test="${userClickAbout == true}">
            <%@include file="about.jsp" %>
        </c:if>

        <!-- Loading the home content -->
        <c:if test="${userClickContact == true}">
            <%@include file="contact.jsp" %>
        </c:if>

        <c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
            <%@include file="listProducts.jsp" %>
        </c:if>

        <c:if test="${userClickShowProduct == true}">
            <%@include file="singleProduct.jsp" %>
        </c:if>

        <c:if test="${userClickManageProducts == true}">
            <%@include file="manageProducts.jsp" %>
        </c:if>
    </div>

    <%@include file="shared/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="${jquery}/jquery.min.js"></script>
    <script src="${bootstrap}/js/bootstrap.min.js"></script>
    <!-- self coded js -->
    <script src="${js}/myapp.js"></script>

    <script src="${jquery}/jquery.dataTables.js"></script>
    <script src="${jquery}/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs-3.3.7/dt-1.10.21/datatables.min.js"></script>
</div>

</body>

</html>

