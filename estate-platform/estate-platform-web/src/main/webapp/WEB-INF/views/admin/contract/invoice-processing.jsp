<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="processing" value="/api/admin/contract/invoice-processing"/>
<html>
<head>
    <title>Xử Lý Hợp Đồng</title>
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
                <li class="active">Xử Lý Hợp Đồng</li>
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
                        <form:hidden path="contractId" id="contractId"/>
                        <form:hidden path="idBuilding" id="idBuilding"/>
                        <form:hidden path="unitPriceDto.id" id="idUnitPrice"/>
                        <form:hidden path="idCustomer" id="idCustomer"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Nước/1 Số</label>
                            <div class="col-sm-9">
                                <form:input cssStyle="width: 180px" path="unitPriceDto.waterPrice" readonly="true"
                                            id="waterPrice" type="number" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Điện/1 Số</label>
                            <div class="col-sm-9">
                                <form:input cssStyle="width: 180px" path="unitPriceDto.electricityPrice" readonly="true"
                                            type="number" id="electricityPrice"
                                            cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Giá Tiền Phòng Hiện Tại </label>
                            <div class="col-sm-9">
                                <form:input path="unitPriceDto.roomPrice" readonly="true" id="roomPrice" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Điện Tiêu Thụ Tháng Trước</label>
                            <div class="col-sm-9">
                                <form:input path="powerNumber" readonly="true" id="powerNumber" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Nước Tiêu Thụ Tháng Trước </label>
                            <div class="col-sm-9">
                                <form:input path="waterNumber" readonly="true" id="waterNumber" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Nợ Tháng Tháng Trước </label>
                            <div class="col-sm-9">
                                <form:input path="percentDaysStay" readonly="true" id="percentDaysStay" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Nước Tiêu Thụ Tháng Này </label>
                            <div class="col-sm-9">
                                <form:input path="waterNumberNew" id="waterNumberNew" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Điện Tiêu Thụ Tháng Này </label>
                            <div class="col-sm-9">
                                <form:input path="powerNumberNew" id="powerNumberNew" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Phải Đóng </label>
                            <div class="col-sm-9">
                                <form:input path="amountPayable" readonly="true" id="amountPayable" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Ghi Chú </label>
                            <div class="col-sm-9">
                                <form:textarea cssStyle="width: 500px;height: 150px;" path="noteContract"
                                               id="noteContract" roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Xử Lý Hóa Đơn"
                               id="btnAddOrUpdateContract"/>
                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Tạm Tính"
                               id="btnProvisional"/>
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
    $("#btnProvisional").click(function (event) {
        event.preventDefault();
        var waterNumberNew = $("#waterNumberNew").val();
        var powerNumberNew = $("#powerNumberNew").val();
        var powerNumber = $("#powerNumber").val();
        var waterNumber = $("#waterNumber").val();
        var waterPrice = $("#waterPrice").val();
        var electricityPrice = $("#electricityPrice").val();
        var roomPrice = $("#roomPrice").val();
        var percentDaysStay = $("#percentDaysStay").val();
        if (waterNumberNew == '' || powerNumberNew == '') {
            alert("Bạn Phải Nhập Số Điện Và Số Nước Tiêu Thụ !");
        } else {
            var water = (parseFloat(waterNumberNew) - parseFloat(waterNumber)) * parseFloat(waterPrice);
            var power = (parseFloat(powerNumberNew) - parseFloat(powerNumber)) * parseFloat(electricityPrice);
            var room = parseFloat(roomPrice);
            var percentDays = parseFloat(percentDaysStay);
            $("#amountPayable").val(water + power + room + percentDays);
        }
    });

    $('#btnAddOrUpdateContract').click(function (event) {
        event.preventDefault();
        if ($("#amountPayable").val() == '') {
            alert("Bạn Chưa Tính Tiền");
        } else {
            var dataArray = {};
            dataArray["idBuilding"] = $("#idBuilding").val();
            dataArray["idUnitPrice"] = $("#idUnitPrice").val();
            dataArray["contractId"] = $("#contractId").val();
            dataArray["idCustomer"] = $("#idCustomer").val();
            dataArray["noteContract"] = CKEDITOR.instances['noteContract'].getData();
            dataArray["waterNumberNew"] = $("#waterNumberNew").val();
            dataArray["powerNumberNew"] = $("#powerNumberNew").val();
            dataArray["amountPayable"] = $("#amountPayable").val();
            dataArray["percentDaysStay"] = $("#percentDaysStay").val();
            $.ajax({
                url: '${processing}',
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
        }
    });

</script>
</body>
</html>
