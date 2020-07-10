<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/admin/user"/>
<html>
<head>
    <title>Chỉnh sửa khách hàng</title>
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
                <li class="active">Chỉnh sửa người dùng</li>
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
                        <security:authorize access="hasRole('MANAGER')">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Loại người dùng</label>
                            <div class="col-sm-9">
                                <form:select path="roleCode" id="role">
                                    <form:option value="" label="--- Chọn loại người dùng ---"/>
                                    <form:options items="${model.roles}" />
                                </form:select>
                            </div>
                        </div>
                        </security:authorize>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên Đăng Nhập</label>
                            <div class="col-sm-9">
                                <form:input path="userName" id="userName" cssClass="form-control"  />
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Email</label>
                            <div class="col-sm-9">
                                <form:input path="email" id="email" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Điện thoại</label>
                            <div class="col-sm-9">
                                <form:input path="phone" id="phone" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Họ tên</label>
                            <div class="col-sm-9">
                                <form:input path="fullName" id="fullName" cssClass="form-control"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <%--<c:if test="${empty model.id}">--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mật Khẩu</label>
                                <div class="col-sm-9">
                                    <form:password path="passWord" id="passWord" cssClass="form-control"/>
                                </div>
                            </div>
                        <%--</c:if>--%>

                        <br>
                        <br>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật người dùng" id="btnAddOrUpdateUsers"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới người dùng" id="btnAddOrUpdateUsers"/>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="userId"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var check_valdidate;
    var check_exist;
    $(document).ready(function () {
        check_valdidate = false;
        check_exist =true;
        $("#formEdit").validate({

            rules: {
                "roleCode": { // <-- assign by field name and use quotes
                    required: true,
                },
                "userName": { // <-- assign by field name and use quotes
                    required: true,
                    minlength: 6,
                    maxlength: 40
                },
                "email": {
                    required: true,  // <-- this rule was misspelled 'equired'
                    email:true
                },
                "phone": {
                    required: true,
                },
                "fullName": {
                    required: true,  // <-- this rule was misspelled 'equired'
                    minlength: 6,
                    maxlength: 40
                },


            },
            messages: {
                "roleCode":{
                    required:"Please choose a role"
                },
                "userName": {
                    required: "username is required!",
                    minlength: "username must be at least 6 characters long"
                },
                "email": {
                    required: "Please enter a email",
                    email:"Email is not valid"
                },
                "phone": {
                    required: "Please enter a phone number",


                },
                "fullName": {
                    required: "Please enter a fullname",
                    minlength: "Name must be at least 6 characters long"
                },
            },
            submitHandler: function(form)
            {
                check_valdidate = true;
            }

        });
    });

    // $('#userName').blur(function (event) {
    //    bindDataAndCheckExist();
    // })

    $('#email').blur(function (event) {
        bindDataAndCheckExist();
    })

    function bindDataAndCheckExist(){
        var dataArray = {};
        dataArray["userName"] = $('#userName').val();
        dataArray["email"] = $('#email').val();
        var id = $('#userId').val();
        if(id == ""){
            id = 0;
        }
        dataArray["id"] = id;
        if(email != "" && userName != ""){
            checkUserNameOrEmailExist(dataArray);
        }
    }

    $('#btnAddOrUpdateUsers').click(function (event) {
        event.preventDefault();
        var dataArray = {};
        dataArray["roleCode"] = $('#role').val();
        dataArray["userName"] = $('#userName').val();
        dataArray["fullName"] = $('#fullName').val();
        dataArray["email"] = $('#email').val();
        dataArray["phone"] = $('#phone').val();
        dataArray["passWord"] = $('#passWord').val();
        var id = $('#userId').val();
        $('#formEdit').submit();
        if(check_valdidate && check_exist){
            if (id == "") {
                addUser(dataArray);
            } else{
                updateUser(dataArray, id);
            }
        }

    });

    function checkUserNameOrEmailExist(data) {
        $.ajax({
            url: '${formUrl}/usages',
            type: 'GET',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                check_exist = true; // ko trùng
                var message = res.messageException;
                if(message != null){
                    check_exist = false; //bị trùng
                    alert(message);
                }
            },
            error: function(res) {
                console.log(res);
            }
        });
    }



    function addUser(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/user/list?message=insert_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/user/list?message=error_system'/>";
            }
        });
    }

    function updateUser(data, id) {
        $.ajax({
            url: '${formUrl}/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                alert("Cập Nhật Thành Công !");
            },
            error: function(res) {
                alert("Cập Nhật Thất Bại !");
            }
        });
    }
</script>
</body>
</html>
