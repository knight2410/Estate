<%--
  Created by IntelliJ IDEA.
  User: vuthe
  Date: 6/21/2020
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <ol class="breadcrumb">
                <li><a href="${contextRoot}/home">Home</a> </li>
                <li><a href="${contextRoot}/show/all/products">Products</a> </li>
                <li class="active">${product.name} </li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 col-sm-4">
            <div class="thumbnail">
                <img src="${images}/${product.code}.jpg" class="img img-responsive">
            </div>
        </div>
        <div class="col-xs-12 col-sm-8">
            <h3>${product.name}</h3>
            <hr/>
            <p>${product.description}</p>
            <hr/>
            <h4>Price: <strong>${product.unitPrice} /- </strong></h4>
            <hr/>
            <c:choose>
                <c:when test="${product.quantity < 1}">
                    <h6>Qty. Available: <span style="color: red">Out of stock</span></h6>
                    <a href="javascript:void(0)" class="btn btn-success disabled"><strike>
                        <span class="glyphicon glyphicon-shopping-cart"></span>Add to cart</strike>
                    </a>
                </c:when>
                <c:otherwise>
                    <h6>Qty. Available: ${product.quantity}</h6>
                    <a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">
                        <span class="glyphicon glyphicon-shopping-cart"> Add to cart</span>
                    </a>
                </c:otherwise>
            </c:choose>
            <a href="${contextRoot}/show/all/products" class="btn btn-primary">
                Back
            </a>
        </div>
    </div>
</div>
