<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/admin/building"/>
<html>
<head>
    <title>Giao phòng cho user</title>
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
                <li class="active">Giao phòng cho user</li>
            </ul><!-- /.breadcrumb -->
        </div>


        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mã phòng</label>
                            <div class="col-sm-9">
                                <form:input path="name" id="name" cssClass="form-control"  />
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Danh sách user</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="userName">
                                </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" id ="save">Lưu</button>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var check_valdidate;
    $(document).ready(function () {

    });




</script>
</body>
</html>
