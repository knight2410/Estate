<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/admin/building"/>
<html>
<head>
    <title>Chỉnh sửa</title>
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
                <li class="active">Chỉnh sửa</li>
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
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">Mã Phòng</label>
                        <div class="col-sm-9">
                            <form:input path="name" id="name" cssClass="form-control"/>
                        </div>
                    </div>
                        <br>
                    <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Quận</label>
                            <div class="col-sm-9">
                                <form:select path="district" id="district">
                                    <form:option value="" label="--- Quận ---"/>
                                    <form:options items="${model.districts}" />
                                </form:select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Phường</label>
                            <div class="col-sm-9">
                                <form:input path="ward" id="ward" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Đường</label>
                            <div class="col-sm-9">
                                <form:input path="street" id="street" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Diện tích thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentArea" id="rentArea" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Giá thuê</label>
                            <div class="col-sm-9">
                                <form:input path="price" id="price" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Số Phòng</label>
                            <div class="col-sm-9">
                                <form:input path="numberOfRooms" id="numberOfRooms" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Chú thích</label>
                            <div class="col-sm-9">
                                <form:input path="description" id="description" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Link phòng</label>
                            <div class="col-sm-9">
                                <form:input path="link" id="link" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Bản đồ</label>
                            <div class="col-sm-9">
                                <form:input path="map" id="map" cssClass="form-control"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Ảnh đại diện</label>
                            <div class="col-sm-9">
                                <input type="file" name="file" multiple id="uploadImage" value="${model.avatar}"/>
                                    <%--<img id="imageUpload" src="#" alt="your image"/>--%>
                            </div>
                            <c:if test="${not empty model.avatar}">
                                <c:set var="image" value="/repository/${model.avatar}"/>
                                <img style="width: 200px;padding-top: 20px;" alt="Building Pic"
                                     src='<c:url value="/repository/${model.avatar}"/>'
                                >
                            </c:if>
                            <c:if test="${empty model.avatar}">
                                <img src="<c:url value='/image/no-image.png'/>" id="viewImage" width="150px"
                                     height="150px">
                            </c:if>
                        </div>
                        <br/>
                        <br/>

                        <div class="container">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div style="height: 300px !important; width: 100%" id="map"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật" id="btnAddOrUpdateBuilding"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới" id="btnAddOrUpdateBuilding"/>
                                </c:if>
                            </div>
                        </div>
                        <form:hidden path="id" id="buildingId"/>
                        <form:hidden path="createdBy" id="createdBy"/>
                        <form:hidden path="createdDate" id="createdDate"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    <%--var markers = ${jsonMap};--%>
    // console.log(markers);
    var check_valdidate;
    var typeArrays;
    $(document).ready(function () {
        check_valdidate = false;
        typeArrays = true;
        $("#formEdit").validate({
            rules: {
                "district": { // <-- assign by field name and use quotes
                    required: true,
                },
                "uploadImage": { // <-- assign by field name and use quotes
                    required: true,
                },
                "typeArrays": { // <-- assign by field name and use quotes
                    required: true,
                }

            },
            messages: {
                "district":{
                    required:"Please choose a district"
                },
                "uploadImage":{
                    required:"Please choose a Image"
                },
                "typeArrays":{
                    //required:"Please choose at least a option",
                }

            },
            submitHandler: function(form)
            {
                check_valdidate = true;
            }

        });
        if(!typeArrays){
            var buildingIdHidden = ' <label id="typeArrays-error" class="error" for="typeArrays">Please choose a option</label>';
            $('#fieldHidden').html(buildingIdHidden);
        }

    });

    $('#btnAddOrUpdateBuilding').click(function (event) {
        event.preventDefault();
        var formData = $("#formEdit").serializeArray();
        var dataArray = {};
        var typeArray = [];
        $.each(formData,function (i,v){
            if(v.name == 'typeArrays'){
                typeArray.push(v.value);
            }else if(v.name == 'createdBy' || v.name == 'createdDate'){
                return;
            }
            else{
                dataArray[""+v.name+""] = v.value;
            }
        });
        dataArray["typeArrays"] = typeArray;
        dataArray["district"] = $("#district").val();
        var files = $('#uploadImage')[0].files[0];
        var id = $('#buildingId').val();
        if (files == undefined && id != "") {//update
            dataArray["imageName"] = "";
            updateBuilding(dataArray, id);
        }
        else {
           // $('#formEdit').submit();
            if(files != undefined){
                dataArray["imageName"] = files.name;
                var reader = new FileReader();
                reader.onload = function (e) {
                    dataArray["avatarBase64"] = e.target.result;
                    if (id == "") {
                        addBuilding(dataArray);
                    } else {
                        // dataArray["createdBy"] = $("#createdBy").val();
                        // dataArray["createdDate"] = $("#createdDate").val();
                        updateBuilding(dataArray, id);
                    }
                }
                reader.readAsDataURL(files);
            }

        }


    });


    function addBuilding(data) {


        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/building/list?message=insert_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }

    function updateBuilding(data, id) {
        $.ajax({
            url: '${formUrl}/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/building/list?message=update_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }

    // function initMap() {
    //     if(markers.length == 1){
    //         var map = new google.maps.Map(document.getElementById("map"), {
    //             zoom : 17,
    //             center : markers[0]
    //         });
    //
    //         var marker = new google.maps.Marker({
    //             position : markers[0],
    //             map : map
    //         });
    //     }
    //
    //     else
    //     {
    //         var map = new google.maps.Map(document.getElementById("map"), {
    //             zoom : 11,
    //             center : {
    //                 lat : 35.694886,
    //                 lng : 139.760664
    //             }
    //         });
    //         for (let index = 0; index < markers.length; index++) {
    //             var marker = new google.maps.Marker({
    //                 position : markers[index],
    //                 map : map
    //             });
    //
    //         }
    //     }
    //
    // }

</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBXpKX5_kZ1rCDH1ql7ELptErbUKumGh5A&callback=initMap"
        type="text/javascript"></script>
</body>
</html>
