<%@ page import="com.estate.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<div class="header">--%>
    <%--<div class="headertop_desc">--%>
        <%--<div class="account_desc">--%>
            <%--<ul>--%>
                <%--<security:authorize access="isAnonymous()">--%>
                    <%--<li>--%>
                        <%--<a href="#">Đăng ký</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href='<c:url value="/dang-nhap"/>'>Đăng nhập</a>--%>
                    <%--</li>--%>
                <%--</security:authorize>--%>
                <%--<security:authorize access="isAuthenticated()">--%>
                    <%--<li>--%>
                        <%--<a href="#">Wellcome <%=SecurityUtils.getPrincipal().getFullName()%></a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href='<c:url value="/logout"/>'>Thoát</a>--%>
                    <%--</li>--%>
                <%--</security:authorize>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="clear"></div>--%>
    <%--</div>--%>
    <%--<div class="header_top">--%>
        <%--<div class="logo">--%>
            <%--<a href="#">--%>
                <%--<img src="<c:url value='/image/logo-header.png' />" alt="">--%>
            <%--</a>--%>
        <%--</div>--%>
        <%--<script type="text/javascript">--%>
            <%--function DropDown(el) {--%>
                <%--this.dd = el;--%>
                <%--this.initEvents();--%>
            <%--}--%>
            <%--DropDown.prototype = {--%>
                <%--initEvents: function () {--%>
                    <%--var obj = this;--%>

                    <%--obj.dd.on('click', function (event) {--%>
                        <%--$(this).toggleClass('active');--%>
                        <%--event.stopPropagation();--%>
                    <%--});--%>
                <%--}--%>
            <%--}--%>

            <%--$(function () {--%>

                <%--var dd = new DropDown($('#dd'));--%>

                <%--$(document).click(function () {--%>
                    <%--// all dropdowns--%>
                    <%--$('.wrapper-dropdown-2').removeClass('active');--%>
                <%--});--%>

            <%--});--%>
        <%--</script>--%>
        <%--<div class="clear">--%>

        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="header_bottom">--%>
        <%--<div class="menu">--%>
            <%--<ul>--%>
                <%--<li class="active">--%>
                    <%--<a href='<c:url value="/trang-chu"/>'>Trang chủ</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#">Gioi thiệu</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#">Liên hệ</a>--%>
                <%--</li>--%>
                <%--<div class="clear"></div>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="clear"></div>--%>
    <%--</div>--%>

<%--</div>--%>

<div class="top">
    <div class="container">
        <div class="row d-flex align-items-center">
            <div class="col">
                <p class="social d-flex">
                    <a href="#"><span class="icon-facebook"></span></a>
                    <a href="#"><span class="icon-twitter"></span></a>
                    <a href="#"><span class="icon-google"></span></a>
                    <a href="#"><span class="icon-pinterest"></span></a>
                </p>
            </div>
            <div class="col d-flex justify-content-end" style="margin-right: 10px">
                <p class="num"><span class="icon-phone"></span> 0978687830
            </div>
        </div>
    </div>
</div>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="index.html">Royal<span>estate</span></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>

        <div class="collapse navbar-collapse" style="padding-left: 284px" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="index.html" class="nav-link">Home</a></li>
                <li class="nav-item"><a href="property.html" class="nav-link">Property</a></li>
                <li class="nav-item"><a href="agents.html" class="nav-link">Agents</a></li>
                <li class="nav-item"><a href="about.html" class="nav-link">About</a></li>
                <li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li>
                <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
                <li class="nav-item cta"><a href="contact.html" class="nav-link ml-lg-2"><span class="icon-user"></span> Sign-In</a></li>
                <li class="nav-item cta cta-colored"><a href="contact.html" class="nav-link"><span class="icon-pencil"></span> Sign-Up</a></li>

            </ul>
        </div>
    </div>
</nav>
<!-- END nav -->
