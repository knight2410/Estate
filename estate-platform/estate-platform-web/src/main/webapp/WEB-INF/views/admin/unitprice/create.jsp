<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="create" value="/api/admin/unit-price"/>
<html>
<head>
    <title>Tạo Đơn Giá</title>
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
                <li class="active">Tạo đơn giá</li>
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
                        <form:hidden path="idBuilding" id="idBuilding"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Nước/1 Số</label>
                            <div class="col-sm-9">
                                <form:input path="waterPrice" id="waterPrice" type="number" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Tiền Điện/1 Số</label>
                            <div class="col-sm-9">
                                <form:input path="electricityPrice" type="number" id="electricityPrice"
                                            cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Giá Tiền Phòng Tháng Này </label>
                            <div class="col-sm-9">
                                <form:input path="roomPrice" id="roomPrice" type="number" roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Ngày Hiệu Lực </label>
                            <div class="col-sm-3">
                                <div class="fg-line">
                                    <form:input type="date" path="effectiveDate" id="effectiveDate"
                                                cssClass="form-control input-sm"
                                                placeholder="Từ ngày"/>
                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Soạn Mail Gửi Khách Hàng </label>
                            <div class="col-sm-9">
                                <form:textarea cssStyle="width: 500px;height: 150px;" path="noteUnitPrice"
                                               id="noteUnitPrice" roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <input list-success="<c:url value='/admin/list-unitprice?message=insert_success&idBuilding=${idBuilding}'/>"
                               list-error="<c:url value='/admin/list-unitprice?message=error_system&idBuilding=${idBuilding}'/>"
                               type="button" class="btn btn-white btn-warning btn-bold" value="Tạo Hóa Đơn Mới"
                               id="btnAddOrUpdateUnitPrice"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    CKEDITOR.replace( 'noteUnitPrice' );
</script>
<script>
    $('#btnAddOrUpdateUnitPrice').click(function (event) {
        event.preventDefault();
        var datea = $('#effectiveDate').val();
        if (new Date(datea) < new Date()){
            alert("Ngày Hiệu Lực Phải Lớn Hơn Hoặc Bằng Ngày Hiên Tại");
        } else {
            var idBuilding = $('#idBuilding').val();
            var dataArray = {};
            var urlSuccess = $(this).attr("list-success");
            dataArray["idBuilding"] = idBuilding;
            dataArray["waterPrice"] = $('#waterPrice').val();
            dataArray["electricityPrice"] = $('#electricityPrice').val();
            dataArray["roomPrice"] = $('#roomPrice').val();
            dataArray["effectiveDate"] = $('#effectiveDate').val();
            dataArray["noteUnitPrice"] = CKEDITOR.instances['noteUnitPrice'].getData();
            $.ajax({
                url: '${create}',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(dataArray),
                success: function (res) {
                    window.location.href = urlSuccess;
                },
                error: function (res) {
                    window.location.href = url;
                }
            });
        }

    });

</script>
</body>
</html>
