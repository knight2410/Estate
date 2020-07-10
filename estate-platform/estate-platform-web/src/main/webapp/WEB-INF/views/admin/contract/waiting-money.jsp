<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="updateConsume" value="/api/admin/contract/updateConsume"/>
<c:url var="sendMail" value="/api/admin/contract/sendMail"/>
<html>
<head>
    <title>Kiểm tra hóa đơn</title>
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
                <li class="active">Kiểm tra hóa đơn</li>
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
                        <form:hidden path="id" id="idConsume"/>
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
                            <label class="col-sm-3 control-label no-padding-right">Số Điện Tiêu Thụ </label>
                            <div class="col-sm-9">
                                <form:input path="powerNumber" readonly="true" id="powerNumber" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Nước Tiêu Thụ </label>
                            <div class="col-sm-9">
                                <form:input path="waterNumber" readonly="true" id="waterNumber" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Nợ Tháng Trước </label>
                            <div class="col-sm-9">
                                <form:input readonly="true" path="percentDaysStay" id="percentDaysStay" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Nợ Tháng Này </label>
                            <div class="col-sm-9">
                                <form:input path="debt" id="debt" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Khách Hàng Đóng</label>
                            <div class="col-sm-9">
                                <form:input path="amountPaid" id="amountPaid" type="number"
                                            roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tổng Số Tiền Phải Đóng </label>
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
                                <form:textarea cssStyle="width: 500px;height: 150px;" path="note"
                                               id="note" roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Xác Nhận Đóng Tiền"
                               id="btnComplete"/>
                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Gửi Mail"
                               id="btnSendMail"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    CKEDITOR.replace('note');
</script>
<script>

    $("#btnSendMail").click(function (event) {
        event.preventDefault();
        var dataArray = {};
        dataArray["note"] = CKEDITOR.instances['note'].getData();
        dataArray["idCustomer"] = $("#idCustomer").val();
        dataArray["id"] = $("#idConsume").val();
        $.ajax({
            url: '${sendMail}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(dataArray),
            success: function (res) {
                if (res) {
                    alert("Gửi Mail Thành Công")
                } else {
                    alert("Gửi Mail Thất Bại");
                }
            },
            error: function (res) {
                alert("Gửi Mail Thất Bại");
            }
        });

    });

    $('#btnComplete').click(function (event) {
        event.preventDefault();
        if ($("#amountPaid").val() == '' || $("#debt").val() == '') {
            alert("Bạn Chưa Nhập Đủ");
        } else {
            var dataArray = {};
            dataArray["note"] = CKEDITOR.instances['note'].getData();
            dataArray["idCustomer"] = $("#idCustomer").val();
            dataArray["id"] = $("#idConsume").val();
            dataArray["amountPaid"] = $("#amountPaid").val();
            dataArray["debt"] = $("#debt").val();
            $.ajax({
                url: '${updateConsume}',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(dataArray),
                success: function (res) {
                    if (res) {
                        window.location.href = "<c:url value='/admin/list-contract?message=update_success'/>";
                    } else {
                        window.location.href = "<c:url value='/admin/list-contract?message=error_system'/>";
                    }
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
