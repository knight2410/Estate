<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/admin/customer"/>
<c:url var="presentForm" value="/admin/customer/edit?id=${model.id}"/>
<html>
<head>
    <title>Chỉnh sửa người dùng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa khách hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form:form id="formEdit" commandName="model" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >Họ tên </label>
                            <div class="col-sm-9">
                                <form:input path="name" id = "name" cssClass="form-control"/>
                            </div>

                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Số điện thoại </label>
                            <div class="col-sm-9">
                                <form:input path="phoneNumber" id = "phoneNumber" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Email </label>
                            <div class="col-sm-9">
                                <form:input path="email" id = "email"  cssClass="form-control"/>
                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"  > Mật Khẩu </label>
                            <div class="col-sm-9">
                                <form:password path="password" id = "password"  cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Soạn Mail Gửi Khách Hàng </label>
                            <div class="col-sm-9">
                                <form:textarea cssStyle="width: 500px;height: 150px;" path="note"
                                               id="note" roomPrice="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật khách hàng" id="btnAddOrUpdateCustomer"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới khách hàng" id="btnAddOrUpdateCustomer"/>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="customerId"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    CKEDITOR.replace( 'note' );
</script>
<script>
    var check_valdidate;
    $(document).ready(function () {
        check_valdidate = false;
        $("#formEdit").validate({
            rules: {
                "name": { // <-- assign by field name and use quotes
                    required: true,
                },
                "email": {
                    required: true,  // <-- this rule was misspelled 'equired'
                    email:true
                },
                "phoneNumber": {
                    required: true,
                },
                "need": {
                    required: true,  // <-- this rule was misspelled 'equired'
                },

            },
            messages: {
                "name":{
                    required:"Hãy nhập họ tên"
                },
                "email": {
                    required: "Hãy nhập email",
                    email:"Email is not valid"
                },
                "phoneNumber": {
                    required: "Hãy nhập số điện thoại",
                },
                "need": {
                    required: "Hãy nhập nhu cầu",
                },
            },
            submitHandler: function(form)
            {
                check_valdidate = true;
            }

        });

    });
    $('#btnAddOrUpdateCustomer').click(function (event) {
        var formData = $("#formEdit").serializeArray();
        var dataArray = {};
        $.each(formData,function (i,v) {
            dataArray[""+v.name+""] = v.value;
        });
        dataArray["note"] = CKEDITOR.instances['note'].getData();
        var id = $('#customerId').val();
        $('#formEdit').submit();
        if(check_valdidate){
            if(id == ""){
                addCustomer(dataArray)
            }else{
                updateCustomer(dataArray,id);
            }
        }

    });

    function addCustomer(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/customer/list?message=insert_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/customer/list?message=error_system'/>";
            }
        });
    }
    function updateCustomer(data, id) {
        $.ajax({
            url: '${formUrl}/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {

                window.location.href = "<c:url value='/admin/customer/list?message=update_success'/>";
            },
            error: function(res) {
                window.location.href = "<c:url value='/admin/customer/list?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
