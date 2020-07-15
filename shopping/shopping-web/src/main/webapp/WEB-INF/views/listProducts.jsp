<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vuthe
  Date: 6/20/2020
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <%@include file="shared/sidebar.jsp"%>
        </div>
        <!-- to display the actual products-->
        <div class="col-md-9">
            <div class="row">
                <div class="col-lg-12">
                    <c:if test="${userClickAllProducts == true}">
                        <script>
                            window.categoryId = '';
                        </script>
                        <ol class="breadcrumb">
                            <li><a href="${pageContext}/home">Home</a> </li>
                            <li class="active">All Products </li>
                        </ol>
                    </c:if>
                    <c:if test="${userClickCategoryProducts == true}">
                        <script>
                            window.categoryId = '${category.id}';
                        </script>
                        <ol class="breadcrumb">
                            <li><a href="${pageContext}/home">Home</a> </li>
                            <li class="active">Category</li>
                            <li class="active">${category.name}</li>
                        </ol>
                    </c:if>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <table id="productListTable" class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Name</th>
                            <th>Brand</th>
                            <th>Price</th>
                            <th>Qty. Available</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th></th>
                            <th>Name</th>
                            <th>Brand</th>
                            <th>Price</th>
                            <th>Qty. Available</th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
