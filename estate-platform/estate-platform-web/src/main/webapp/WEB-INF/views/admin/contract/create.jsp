<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="create" value="/api/admin/contract"/>
<html>
<head>
    <title>Tạo Hợp Đồng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Tạo hợp đồng</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <form:form id="formEdit" commandName="model">
                        <label class="col-sm-3 control-label no-padding-right">Chọn Khách Hàng</label>
                        <form:hidden path="idBuilding" id="idBuilding"/>
                        <form:hidden path="unitPriceDto.id" id="idUnitPrice"/>
                        <div class="form-group">
                            <div class="col-sm-4">
                                <div class="fg-line">
                                    <form:select path="idCustomer" id="idCustomer">
                                        <form:option value="" label="--- Chọn Khách Hàng ---"/>
                                        <form:options items="${customders}"/>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Nước/1 Số</label>
                            <div class="col-sm-9">
                                <form:input cssStyle="width: 180px" path="unitPriceDto.waterPrice" readonly="true" id="waterPrice" type="number" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Điện/1 Số</label>
                            <div class="col-sm-9">
                                <form:input cssStyle="width: 180px" path="unitPriceDto.electricityPrice" readonly="true" type="number" id="electricityPrice"
                                            cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Giá Tiền Phòng Hiện Tại </label>
                            <div class="col-sm-9">
                                <form:input path="unitPriceDto.roomPrice" readonly="true" id="roomPrice" type="number" roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Điện Tiêu Thụ Hiện Tại </label>
                            <div class="col-sm-9">
                                <form:input path="powerNumber" id="powerNumber" type="number" roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Nước Tiêu Thụ Hiện Tại </label>
                            <div class="col-sm-9">
                                <form:input path="waterNumber" id="waterNumber" type="number" roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Đã Đóng </label>
                            <div class="col-sm-9">
                                <form:input path="amountPaid" id="amountPaid" type="number" roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Ghi Chú </label>
                            <div class="col-sm-9">
                                <form:textarea cssStyle="width: 500px;height: 150px;" path="noteContract" id="noteContract"  roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Tạo Hợp Đồng Mới"
                               id="btnAddOrUpdateContract"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    CKEDITOR.replace( 'noteContract' );
</script>
<script>
    $('#btnAddOrUpdateContract').click(function (event) {
        event.preventDefault();
        var dataArray = {};
        dataArray["idBuilding"] = $("#idBuilding").val();
        dataArray["idCustomer"] = $("#idCustomer").val();
        dataArray["noteContract"] = CKEDITOR.instances['noteContract'].getData();
        dataArray["idUnitPrice"] = $("#idUnitPrice").val();
        dataArray["waterNumber"] = $("#waterNumber").val();
        dataArray["powerNumber"] = $("#powerNumber").val();
        dataArray["amountPaid"] = $("#amountPaid").val();
        $.ajax({
            url: '${create}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(dataArray),
            success: function (res) {
                window.location.href = "<c:url value='/admin/list-contract?message=insert_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='/admin/list-contract?message=error_system'/>";
            }
        });
    });

</script>
</body>
</html>
