<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.estate.utils.SecurityUtils" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin/building/list"/>
<c:url var="API" value="/api/admin/building"/>
<c:url var="loadUser" value="/api/admin/user"/>
<c:url var="existsBuildingInUnitprice" value="/api/admin/building/exists-building-in-unitprice"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách tất cả phòng</title>
</head>
<body>
<div class="main-content">
    <form:form commandName="model" action="${formUrl}" id="listForm" method="GET">
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
                <li class="active">Danh sách phòng</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${messageResponse!=null}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="widget-box table-filter">
                                <div class="widget-header">
                                    <h4 class="widget-title">Tìm kiếm</h4>
                                    <div class="widget-toolbar">
                                        <a href="#" data-action="collapse">
                                            <i class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <div class="col-sm-4">
                                                    <div class="fg-line">
                                                        <form:select path="district" id="district">
                                                            <form:option value="" label="--- Quận ---"/>
                                                            <form:options items="${model.districts}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="fg-line">
                                                        <form:input path="ward" id="ward"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Phường"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="fg-line">
                                                        <form:input path="street" id="street"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Đường"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="number" path="areaFrom" id="areaFrom"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Diện tích từ"/>
                                                            <%--<input type="number" name="areaFrom"--%>
                                                            <%--class="form-control input-sm"--%>
                                                            <%--placeholder="Diện tích từ" id="areaFrom"/>--%>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="number" path="areaTo" id="areaTo"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Diện tích đến"/>
                                                            <%--<input type="number" name="areaTo"--%>
                                                            <%--class="form-control input-sm"--%>
                                                            <%--placeholder="Diện tích đến" id="areaTo"/>--%>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="number" path="priceFrom" id="priceFrom"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Gía thuê từ"/>
                                                            <%--<input type="number" name="priceFrom"--%>
                                                            <%--class="form-control input-sm"--%>
                                                            <%--placeholder="Gía thuê từ" id="priceFrom"/>--%>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="number" path="priceTo" id="priceTo"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Gía thuê đến"/>
                                                            <%--<input type="number" name="priceTo"--%>
                                                            <%--class="form-control input-sm"--%>
                                                            <%--placeholder="Gía thuê đến" id="priceTo"/>--%>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <c:if test="${urlMapping != '/admin/building/assignment'}">
                                                        <div class="col-sm-4">
                                                            <div class="fg-line">
                                                                <form:select path="staffName" id="staffName">
                                                                    <form:option value=""
                                                                                 label="--- Chọn nhân viên phụ trách ---"/>
                                                                    <form:options items="${staffMaps}"/>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </security:authorize>
                                                <div class="fg-line">
                                                    <form:select path="checkBuildingInContract"
                                                                 id="checkBuildingInContract">
                                                        <form:option value="" label="--- Trạng Thái Phòng ---"/>
                                                        <%--<form:options items="${model.processed1}" />--%>
                                                        <form:option value="1" label="Đang Kí Hợp Đồng"/>
                                                        <form:option value="0" label="Chưa Có Hợp Đồng"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-6">
                                                    <button id="btnSearch" type="button"
                                                            class="btn btn-sm btn-success">
                                                        <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110">Tìm
                                                            kiếm</i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">

                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip" title="Thêm phòng mới"
                                           href='<c:url value="/admin/building/edit"/>'>
                                                        <span>
                                                        <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                    </span>
                                        </a>
                                        <security:authorize access="hasRole('MANAGER')">
                                            <button id="btnDelete" type="button"
                                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                    disabled
                                                    data-toggle="tooltip" title="Xóa phòng"
                                                    onclick="warningBeforeDelete()">
                                                            <span>
                                                            <i class="fa fa-trash-o bigger-110 pink"></i>
                                                            </span>
                                            </button>
                                        </security:authorize>
                                    </div>
                                </div>
                            </div>

                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                                           requestURI="${formUrl}" uid="row"
                                           partialList="true" size="${model.totalItems}" id="tableList"
                                           pagesize="${model.maxPageItems}" export="false"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">
                                <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                headerClass="center select-cell">
                                    <fieldset>
                                        <input type="checkbox" name="checkList" value="${tableList.id}"
                                               id="checkbox_${tableList.id}" class="check-box-element"/>
                                    </fieldset>
                                </display:column>
                                <display:column headerClass="text-left" property="name" title="Mã Phòng"/>
                                <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                                <display:column headerClass="text-left" property="nameUser" title="Tên Q.Lý"/>
                                <display:column headerClass="text-left" property="phoneUser" title="Phone"/>
                                <display:column headerClass="text-left" property="rentArea" title="Diện tích sàn"/>
                                <display:column headerClass="text-left" property="price" title="Giá thuê"/>
                                <display:column headerClass="text-left" property="numberOfRooms" title="Số Phòng"/>
                                <%--<security:authorize access="hasRole('MANAGER')">--%>
                                <%--<display:column headerClass="" title="HOT">--%>
                                <%--<c:choose>--%>
                                <%--<c:when test="${tableList.prioritize == 1}">--%>
                                <%--<a href="#" buildingId="${tableList.id}" class="btnPriority" action="remove">--%>
                                <%--<span class="glyphicon glyphicon-ok"></span>--%>
                                <%--</a>--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${tableList.prioritize == 0}">--%>
                                <%--<a href="#" buildingId="${tableList.id}" class="btnPriority" action="add">--%>
                                <%--<span class="glyphicon glyphicon-plus"></span>--%>
                                <%--</a>--%>
                                <%--</c:when>--%>
                                <%--</c:choose>--%>
                                <%--</display:column>--%>

                                <%--                                     <display:column headerClass="" title="Status"> --%>
                                <%--                                             <c:choose> --%>
                                <%--                                                 <c:when test="${tableList.existsBuildingAndCustomer == 1}"> --%>
                                <%--                                                     <a href="#" buildingId="${tableList.id}"  action="remove"> --%>
                                <!-- <span class="glyphicon glyphicon-ok"></span> -->
                                <!-- </a> -->
                                <%--                                                 </c:when> --%>
                                <%--                                                 <c:when test="${tableList.existsBuildingAndCustomer == 0}"> --%>
                                <%--                                                     <a href="#" buildingId="${tableList.id}"  action="add"> --%>
                                <!-- <span class="glyphicon glyphicon-plus"></span> -->
                                <!-- </a> -->
                                <%--                                                 </c:when> --%>
                                <%--                                             </c:choose> --%>
                                <%--                                     </display:column> --%>
                                <%--</security:authorize>--%>
                                <display:column headerClass="col-actions" title="Trạng Thái">
                                    <c:if test="${tableList.existBuildingInContract == false}">
                                        <a class="btn btn-sm btn-danger btn-edit btnCreateContract"
                                           data-attr="${tableList.id}" data-toggle="tooltip"
                                           title="Chưa Có Hợp Đồng"
                                           create-unit-price="<c:url value='/admin/list-unitprice?idBuilding=${tableList.id}'/>"
                                           create-contract="<c:url value='/admin/create-contract?idBuilding=${tableList.id}'/>">Tạo
                                            Hợp
                                            Đồng</a>
                                    </c:if>
                                    <c:if test="${tableList.existBuildingInContract == true}">
                                        <a class="btn btn-sm btn-success btn-edit" data-toggle="tooltip"
                                           title="Đã Có Hợp Đồng"
                                           href='<c:url value="/admin/building/edit?id=${tableList.id}"/>'><span
                                                class="glyphicon glyphicon-ok"></span></a>
                                    </c:if>
                                </display:column>
                                <display:column headerClass="col-actions" title="Thao tác">
                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                       title="Hóa Đơn Phòng"
                                       href='<c:url value="/admin/list-unitprice?idBuilding=${tableList.id}"/>'>Hóa
                                        Đơn</a>
                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                       title="Cập nhật phòng"
                                       href='<c:url value="/admin/building/edit?id=${tableList.id}"/>'>Cập Nhật</a>

                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                       title="Chi tiết phòng"
                                       href='<c:url value="/admin/building/detail/${tableList.id}"/>'>Chi Tiết</i></a>

                                    <a style="margin-top: 10px;width:75px" class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                       title="Lịch Sử"
                                       href='<c:url value="/admin/contract-history-building?idBuilding=${tableList.id}"/>'>Lịch Sử</i></a>
                                    <%--<security:authorize access="hasRole('MANAGER')">--%>
                                    <%--<button type="button" class="btn btn-sm btn-primary btn-edit"--%>
                                    <%--data-toggle="tooltip"--%>
                                    <%--title="Giao phòng cho nhân viên" id="btnAssignBuilding"--%>
                                    <%--buildingId="${tableList.id}">--%>
                                    <%--<i class="fa fa-users" aria-hidden="true"></i>--%>
                                    <%--</button>--%>
                                    <%--</security:authorize>--%>
                                </display:column>
                            </display:table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</form:form>
<input type="hidden" value="<%=SecurityUtils.getPrincipal().getId()%>" id="userId">
<security:authorize access="hasRole('MANAGER')">
    <!-- Modal -->
    <div class="modal fade" id="assignBuildingModal" role="dialog">
        <div class="modal-dialog modal-lg">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Giao phòng cho nhân viên</h4>
                </div>
                <div class="modal-body">
                    <span> Search :</span> <input type="text" placeholder="Tên nhân viên" id="btnSearchUserPopup">
                    <table class="table" id="userAssignTable">
                        <thead>
                        <tr>
                            <th>
                                Chọn nhân viên
                            </th>
                            <th>
                                Họ và tên
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div id="fieldHidden">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="btnAssignBuildingForUser">Giao phòng cho nhân
                        viên
                    </button>
                </div>
            </div>

        </div>
    </div>
</security:authorize>
</div>
<script type="text/javascript">

    var arrayUsers = [];
    var buildingIdGlobal = "";
    $(document).ready(function () {
        $('#tableList #btnAssignBuilding').click(function (e) {
            e.preventDefault();
            openModelAssignBuilding();
            loadUserAssignBuilding($(this).attr('buildingId'), '');
        });
    });
    $('#btnSearchUserPopup').keyup(function (e) {
        console.log(arrayUsers);
        var username = $('#btnSearchUserPopup').val();
        var buildingIdHidden = '<input type="hidden" name="buildingId" value=' + buildingIdGlobal + ' id="buildingId"></input>';
        $('#fieldHidden').html(buildingIdHidden);
        var row = '';
        for (var i = 0; i < arrayUsers.length; i++) {
            if (arrayUsers[i].username.indexOf(username) > -1) {
                row += '<tr>';
                // chú ý checked
                row += '<td class="text-center"><input type="checkbox" name="checkList" onchange = "changeRadio(' + arrayUsers[i].userId + ')" value="' + arrayUsers[i].userId + '"  id="checkbox_' + arrayUsers[i].userId + '" class="check-box-element" ' + arrayUsers[i].checked + '/></td>';
                row += '<td class="text-center">' + arrayUsers[i].username + '</td>';
                row += '</tr>';

            }
        }
        $('#userAssignTable tbody').html(row);
    });

    $(".btnCreateContract").click(function (event) {
        event.preventDefault();
        var idBuilding = $(this).attr("data-attr");
        var urlUnitPrice = $(this).attr("create-unit-price");
        var urlContract = $(this).attr("create-contract");
        var data = {}
        data["id"] = idBuilding;
        $.ajax({
            url: '${existsBuildingInUnitprice}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                if (res) {
                    window.location.href = urlContract;
                } else {
                    var r = confirm("Phòng Này Chưa Có Hóa Đơn . Bạn Phải Tạo Mới !");
                    if (r == true) {
                        window.location.href = urlUnitPrice;
                    }
                }
            },
            error: function (res) {
            }
        });

    });

    $('#btnAssignBuildingForUser').click(function (e) {
        console.log(arrayUsers);
        var users = [];
        for (var i = 0; i < arrayUsers.length; i++) {
            if (arrayUsers[i].checked == "checked") {
                users.push(arrayUsers[i].userId);
            }
        }
        console.log(users);
        console.log(buildingIdGlobal);
//         e.preventDefault();
//         var buildingId = $('#fieldHidden').find('#buildingId').val();
//         var users = $('#userAssignTable').find('tbody input[type=checkbox]:checked').map(function () {
//             return $(this).val();
//         }).get();

        updateBuilding(users, buildingIdGlobal);
    });


    $('#btnSearch').click(function () {
        $('#listForm').submit();
    });


    $('.btnPriority').click(function (event) {
        event.preventDefault();
        buildingId = $(this).attr("buildingId");
        action = $(this).attr("action");
        updatePrioritize(action, buildingId);
    });


    function updateBuilding(data, id) {
        $.ajax({
            url: '${API}/' + id + '/users',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/building/list?message=update_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }

    function updatePrioritize(action, id) {
        $.ajax({
            url: '${API}/' + id + '/priority?action=' + action,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(),
            success: function (res) {
                window.location.href = "<c:url value='/admin/building/list?message=update_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }

    function deleteBuilding(data) {
        $.ajax({
            url: '${API}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/building/list?message=delete_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
    }

    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteBuilding(dataArray);
        });
    }

    function openModelAssignBuilding() {
        $('#assignBuildingModal').modal();
    }

    function User(userId, username, checked) {
        this.userId = userId;
        this.username = username;
        this.checked = checked;
    }


    function changeRadio(id) {
        for (var i = 0; i < arrayUsers.length; i++) {
            if (arrayUsers[i].userId == id) {
                if (arrayUsers[i].checked == "") {
                    arrayUsers[i].checked = "checked";
                } else {
                    arrayUsers[i].checked = "";
                }
                break;
            }
        }
    }

    //function User()
    function loadUserAssignBuilding(buildingId, username) {
        buildingIdGlobal = buildingId;
        $('#btnSearchUserPopup').val("");
        var buildingIdHidden = '<input type="hidden" name="buildingId" value=' + buildingId + ' id="buildingId"></input>';
        $('#fieldHidden').html(buildingIdHidden);
        $.ajax({
            url: '${loadUser}?role=USER&buildingid=' + buildingId,
            type: 'GET',
            success: function (res) {
                var row = '';
                arrayUsers = [];
                $.each(res, function (index, user) {
                    var users = new User(user.id, user.fullName, user.checked);
                    arrayUsers.push(users);

                    row += '<tr>';
                    // chú ý checked
                    row += '<td class="text-center"><input type="checkbox" name="checkList" onchange = "changeRadio(' + user.id + ')"  value="' + user.id + '" id="checkbox_' + user.id + '"   class="check-box-element" ' + user.checked + '/></td>';
                    row += '<td class="text-center">' + user.fullName + '</td>';
                    row += '</tr>';
                });
                $('#userAssignTable tbody').html(row);
                console.log(arrayUsers)

            },
            error: function (res) {
                console.log(res);
            }
        });
    }
</script>
</body>
</html>
