<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
    <!-- jquery -->
    <script type='text/javascript' src='<c:url value="/template/admin/js/2.1.4/jquery.min.js" />'></script>

    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/4.5.0/css/font-awesome.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/ace.min.css' />" class="ace-main-stylesheet"
          id="main-ace-style"/>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="<c:url value='/template/admin/css/style.css' />"/>

    <%--sweetalert--%>
    <script type='text/javascript'
            src='<c:url value="/template/admin/assets/sweetalert2/sweetalert2.min.js"/>'></script>
    <link rel="stylesheet" href="<c:url value="/template/admin/assets/sweetalert2/sweetalert2.min.css"/>">

    <%--datatable css--%>
    <link href="<c:url value="/template/admin/data-table/media/css/dataTables.bootstrap4_v4.6.1.min.css"/>"
          rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Chi tiết phòng</h3>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-4 col-lg-4 " align="center">
                        <c:if test="${not empty model.avatar}">
                            <c:set var="image" value="/repository/${model.avatar}"/>
                            <img alt="Building Pic"
                                 src='<c:url value="/repository/${model.avatar}"/>'
                                 class="img-thumbnail">
                        </c:if>
                        <c:if test="${empty model.avatar}">
                            <img src="<c:url value='/image/no-image.png'/>" id="viewImage" width="150px" height="150px">
                        </c:if>
                    </div>
                    <div class=" col-md-8 col-lg-8 ">
                        <table class="table table-user-information">
                            <tbody>
                            <tr>
                                <td>
                                    Mã Phòng
                                </td>
                                <td>
                                    ${model.name}
                                </td>
                            </tr>
                                <tr>
                                    <td>
                                        Vị trí
                                    </td>
                                    <td>
                                        ${model.street},${model.ward},${model.district}
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        Diện tích cho thuê
                                    </td>
                                    <td>
                                        ${model.rentArea}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Giá thuê
                                    </td>
                                    <td>
                                        ${model.price}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Người tạo hóa đơn
                                    </td>
                                    <td>
                                        ${model.unitPriceDto.createdBy}
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        Ngày hóa đơn được áp dụng
                                    </td>
                                    <td>
                                        ${model.unitPriceDto.effectiveDate}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Giá tiền điện
                                    </td>
                                    <td>
                                        ${model.unitPriceDto.electricityPrice}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Giá tiền nước
                                    </td>
                                    <td>
                                        ${model.unitPriceDto.waterPrice}
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Giá tiền phòng
                                    </td>
                                    <td>
                                        ${model.unitPriceDto.roomPrice}
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
        src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<script src="<c:url value='/template/admin/assets/js/ace-extra.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery-ui.custom.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.ui.touch-punch.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.easypiechart.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.sparkline.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.pie.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.resize.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/ace-elements.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/ace.min.js' />"></script>

<%--datatable js--%>
<script type="text/javascript" src="<c:url value="/template/admin/data-table/data_table-1.0.0.js"/>"></script>
<script type="text/javascript"
        src="<c:url value="/template/admin/data-table/data-table-bootstrap4-1.0.0.js"/>"></script>
</body>
</html>
