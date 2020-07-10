<%@ page import="com.estate.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/web-home"/>
<div class="hero-wrap" style="background-image: url('<c:url value='/template/home/images/bg_1.jpg'/>');">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">

            </div>
        </div>
    </div>
</div>
<form:form commandName="model" action="${formUrl}" id="listForm" method="GET">
    <form:hidden path="page" id="page"/>
    <section class="ftco-search">
        <div class="container">
            <div class="row">
                <div class="col-md-12 search-wrap">
                    <form action="#" class="search-property">
                        <div class="row">
                            <div class="col-md align-items-end">
                                <div class="form-group">
                                    <label for="#">Quận</label>
                                    <div class="form-field">
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>

                                            <form:select path="district" id="district" cssClass="form-control">
                                                <form:option value="" label="Quận"/>
                                                <form:options items="${model.districts}"/>
                                            </form:select>

                                                <%--<select name="" id="" class="form-control">--%>
                                                <%--<option value="">Type</option>--%>
                                                <%--<option value="">Commercial</option>--%>
                                                <%--<option value="">- Office</option>--%>
                                                <%--<option value="">Residential</option>--%>
                                                <%--<option value="">Villa</option>--%>
                                                <%--<option value="">Condominium</option>--%>
                                                <%--<option value="">Apartment</option>--%>
                                                <%--</select>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md align-items-end">
                                <div class="form-group">
                                    <label for="#">Tên Đường</label>
                                    <div class="form-field">
                                        <div class="icon"><span class="icon-my_location"></span></div>
                                        <form:input path="street" type="text" cssClass="form-control"
                                                    placeholder="Tên Đường"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md align-items-end">
                                <div class="form-group">
                                    <label for="#">Diện Tích Từ</label>
                                    <div class="form-field">
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>

                                            <form:select path="areaFrom" id="areaFrom" cssClass="form-control">
                                                <form:option value="" label="Diện Tích Từ"/>
                                                <form:options items="${areaMin}"/>
                                            </form:select>

                                                <%--<select name="" id="" class="form-control">--%>
                                                <%--<option value="">Type</option>--%>
                                                <%--<option value="">Rent</option>--%>
                                                <%--<option value="">Sale</option>--%>
                                                <%--</select>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md align-items-end">
                                <div class="form-group">
                                    <label for="#">Diện Tích Đến</label>
                                    <div class="form-field">
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                                <%--<select name="" id="" class="form-control">--%>
                                                <%--<option value="">Any</option>--%>
                                                <%--<option value="">Jonh Doe</option>--%>
                                                <%--<option value="">Doe Mags</option>--%>
                                                <%--<option value="">Kenny Scott</option>--%>
                                                <%--<option value="">Emily Storm</option>--%>
                                                <%--</select>--%>
                                            <form:select path="areaTo" id="areaTo" cssClass="form-control">
                                                <form:option value="" label="Diện Tích Đến"/>
                                                <form:options items="${areaMax}"/>
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md align-items-end">
                                <div class="form-group">
                                    <label for="#">Giá Từ</label>
                                    <div class="form-field">
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                            <form:select path="priceFrom" id="priceFrom" cssClass="form-control">
                                                <form:option value="" label="Giá Từ"/>
                                                <form:options items="${priceMin}"/>
                                            </form:select>
                                                <%--<select name="" id="" class="form-control">--%>
                                                <%--<option value="">1</option>--%>
                                                <%--<option value="">2</option>--%>
                                                <%--<option value="">3</option>--%>
                                                <%--<option value="">4</option>--%>
                                                <%--<option value="">5</option>--%>
                                                <%--</select>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md align-items-end">
                                <div class="form-group">
                                    <label for="#">Giá Đến</label>
                                    <div class="form-field">
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                            <form:select path="priceTo" id="priceTo" cssClass="form-control">
                                                <form:option value="" label="Giá Đến"/>
                                                <form:options items="${priceMax}"/>
                                            </form:select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md align-self-end">
                                <div class="form-group">
                                    <div class="form-field">
                                        <input id="btnSearch" type="button" value="Search" class="form-control btn btn-primary">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</form:form>

<section class="ftco-section bg-light">
    <div class="container">
        <div class="row">
            <c:forEach var="item" items="${model.listResult}">




                <div class="col-md-4 ftco-animate">
                    <div class="properties">
                        <a href="property-single.html" class="img img-2 d-flex justify-content-center align-items-center"
                           >
                            <img alt="Building Pic"
                                 src='<c:url value="/repository/${item.avatar}"/>'
                                 class="img-thumbnail">
                            <%--<div class="icon d-flex justify-content-center align-items-center">--%>
                                <%--<span class="icon-search2"></span>--%>
                            <%--</div>--%>
                        </a>
                        <div class="text p-3">

                            <div class="d-flex">
                                <div class="one">
                                    <h3><a href="property-single.html">${item.street}</a></h3>
                                    <p>Apartment</p>
                                </div>
                                <div >
                                    <span style="color: red"><fmt:formatNumber type = "currency" value = "${item.price}" /></span>
                                </div>
                            </div>
                            <p>${item.contentWeb}</p>
                            <hr>
                            <p class="bottom-area d-flex">
                                <span><i class="flaticon-selection"></i> ${item.rentArea} m2</span>
                                <span class="ml-auto"></span>
                                <span><i class="flaticon-bed"></i> ${item.numberOfRooms}</span>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>
                        <li><a href="#">&lt;</a></li>
                        <c:forEach begin="1" end="${model.totalPages}"  varStatus="loop">
                            <c:if test="${model.page == loop.count}">
                                <li class="active"><span class="pagination-page">${loop.count}</span></li>
                            </c:if>
                            <c:if test="${model.page != loop.count}">
                                <li><span class="pagination-page">${loop.count}</span></li>
                            </c:if>
                        </c:forEach>
                        <li><a href="#">&gt;</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section-parallax">
    <div class="parallax-img d-flex align-items-center">
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-md-7 text-center heading-section heading-section-white ftco-animate">
                    <h2>Subcribe to our Newsletter</h2>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there
                        live the blind texts. Separated they live in</p>
                    <div class="row d-flex justify-content-center mt-5">
                        <div class="col-md-8">
                            <form action="#" class="subscribe-form">
                                <div class="form-group d-flex">
                                    <input type="text" class="form-control" placeholder="Enter email address">
                                    <input type="button" value="Subscribe" class="submit px-3">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>

$(".pagination-page").click(function () {
    $("#page").val($(this).text())
    $("#listForm").submit();
});

$("#btnSearch").click(function () {
    $("#page").val(1);
    $("#listForm").submit();
})
</script>