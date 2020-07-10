<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.estate.utils.SecurityUtils" %>
<%@include file="/common/taglib.jsp" %>
<%@ page import="java.util.Date" %>
<c:url var="formUrl" value="/admin/list-contract"/>
<c:url var="API" value="/api/admin/contract"/>
<c:url var="download" value="/api/admin/contract/download"/>


<%--<c:url var="API" value="/api/admin/building"/>--%>
<%--<c:url var="loadUser" value="/api/admin/user"/>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách tất cả hợp đồng</title>
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
                <li class="active">Danh sách hợp đồng</li>
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
                                                <div class="col-sm-3">

                                                    <div class="fg-line">
                                                        <form:select path="processed1" id="processed1">
                                                            <form:option value="" label="--- Trạng Thái Hợp Đồng ---"/>
                                                            <%--<form:options items="${model.processed1}" />--%>
                                                            <form:option value="1" label="Đã Xử Lý"/>
                                                            <form:option value="0" label="Chưa Xử Lý"/>
                                                        </form:select>
                                                    </div>
                                                    <br>
                                                    <input type="hidden" id="id_processed1" value="${model.processed1}">
                                                    <div class="fg-line" id="id_paidMoney">
                                                        <form:select path="paidMoney" id="paidMoney">
                                                            <form:option value="" label="--- Trạng Thái Đóng Tiền ---"/>
                                                            <%--<form:options items="${model.processed1}" />--%>
                                                            <form:option value="1" label="Đã Đóng Tiền"/>
                                                            <form:option value="0" label="Chưa Đóng Tiền"/>
                                                        </form:select>
                                                    </div>

                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input path="nameCustomer" id="nameCustomer"
                                                                    placeholder="Tên Khách Hàng"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <security:authorize access="hasRole('MANAGER')">
                                                            <div class="col-sm-4">
                                                                <div class="fg-line">
                                                                    <form:select path="nameUser" id="nameUser">
                                                                        <form:option value=""
                                                                                     label="--- Chọn nhân viên phụ trách ---"/>
                                                                        <form:options items="${staffMaps}"/>
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                    </security:authorize>
                                                </div>

                                            </div>
                                            <div class="form-group">
                                                <p style="margin-left: 13px">Ngày Tạo Hợp Đồng</p>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="startDate" id="startDate"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Từ ngày"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="endDate" id="endDate"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Đến ngày"/>
                                                    </div>
                                                </div>
                                                <br>
                                                <br>
                                                <p style="margin-left: 13px">Ngày Xử Lý Hóa Đơn</p>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="startDateConsume" id="startDateConsume"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Từ ngày"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="endDateConsume" id="endDateConsume"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Đến ngày"/>
                                                    </div>
                                                </div>
                                                <br>
                                                <br>
                                                <p style="margin-left: 13px">Ngày Hiệu Lực</p>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="startDateUnitprice" id="startDateUnitprice"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Từ ngày"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="endDateUnitPrice" id="endDateUnitPrice"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Đến ngày"/>
                                                    </div>
                                                </div>
                                                <br>
                                                <br>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:radiobutton path="deleteBuilding" value="1"/>
                                                        Chưa Xóa
                                                        <form:radiobutton path="deleteBuilding" value="0"/>Đã
                                                        Xóa
                                                    </div>
                                                </div>
                                                <br>
                                                <br>
                                                <br>
                                                <div class="form-group">
                                                    <div class="col-sm-6">
                                                        <button id="btnSearch" type="submit"
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
                        </div>
                        <div class="table-btn-controls">
                            <div class="pull-right tableTools-container">
                                <div class="dt-buttons btn-overlap btn-group">

                                    <a style="height: 30px;margin: auto;" id="btnDownload" flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                       data-toggle="tooltip" title="Tải Về">
                                                       Tải Về
                                    </a>

                                    <button id="btnDelete" type="button"
                                            class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" disabled
                                            data-toggle="tooltip" title="Xóa Hợp Đồng" onclick="warningBeforeDelete()">
                                                        <span>
                                                        <i class="fa fa-trash-o bigger-110 pink"></i>
                                                        </span>
                                    </button>
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
                                <security:authorize access="hasRole('MANAGER')">
                                <display:column headerClass="text-left" property="createdBy" title="Tên người tạo"/>
                                </security:authorize>
                                <display:column headerClass="text-left" property="nameBuilding" title="Mã Phòng"/>
                                <display:column headerClass="text-left" property="nameCustomer" title="Tên khách hàng"/>
                                <display:column headerClass="text-left" property="createdDate"
                                                title="Ngày kí hợp đồng"/>
                                <display:column headerClass="text-left" property="createdDateConsumer" title="Ngày tạo hóa đơn"/>
                                <display:column headerClass="text-left" property="powerNumber" title="Số điện SD"/>
                                <display:column headerClass="text-left" property="waterNumber" title="Số nước SD"/>

                                <display:column headerClass="text-left" property="effectiveDate" title="Ngày hiệu lực"/>
                                <display:column headerClass="text-left" property="electricityPrice" title="Giá điện"/>
                                <display:column headerClass="text-left" property="waterPrice" title="Giá nước"/>
                                <display:column headerClass="text-left" property="roomPrice" title="Giá phòng"/>
                                <display:column headerClass="text-left" property="amountPaid" title="Số tiền đã đóng"/>
                                <display:column headerClass="text-left" property="percentDaysStay" title="Số tiền nợ"/>
                                <display:column headerClass="text-left" property="amountPayable" title="Số tiền phải đóng"/>


                                <display:column headerClass="col-actions" title="Thao tác">

                                    <c:if test="${model.deleteBuilding == 1}">
                                        <c:if test="${tableList.monthCreatedDateConsumer != nowCreate}">
                                            <a class="btn btn-sm btn-danger btn-edit" data-toggle="tooltip"
                                               title="Chưa xử lý"
                                               href='<c:url value="/admin/invoice-processing?idContract=${tableList.id}"/>'>Chưa Xử
                                                Lý</a>
                                        </c:if>

                                        <c:if test="${tableList.monthCreatedDateConsumer == nowCreate}">
                                            <c:if test="${tableList.amountPaid != null}">
                                                <a style="width: 107px" class="btn btn-sm btn-success btn-edit" data-toggle="tooltip"
                                                   title="Đã Nhận Tiền">Đã Nhận Tiền</a>
                                            </c:if>

                                            <c:if test="${tableList.amountPaid == null}">
                                                <a class="btn btn-sm btn-warning btn-edit" data-toggle="tooltip"
                                                   title="Chờ Nhận Tiền"
                                                   href='<c:url value="/admin/waiting-money?idContract=${tableList.id}"/>'>Chờ
                                                    Nhận Tiền</a>
                                            </c:if>

                                        </c:if>
                                    </c:if>
                                    <a style="margin-top: 10px;" class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                       title="Chi Tiết"
                                       href='<c:url value="/admin/detail-contract?idContract=${tableList.id}"/>'>Chi Tiết HĐ</a>
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
<%--<input type="hidden" value="<%=SecurityUtils.getPrincipal().getId()%>" id="userId">--%>
<%--<security:authorize access="hasRole('MANAGER')">--%>
<%--<!-- Modal -->--%>
<%--<div class="modal fade" id="assignBuildingModal" role="dialog">--%>
<%--<div class="modal-dialog modal-lg">--%>
<%--<!-- Modal content-->--%>
<%--<div class="modal-content">--%>
<%--<div class="modal-header">--%>
<%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
<%--<h4 class="modal-title">Giao tòa nhà cho nhân viên</h4>--%>
<%--</div>--%>
<%--<div class="modal-body">--%>
<%--<span> Search :</span> <input type="text"  placeholder="Tên nhân viên" id = "btnSearchUserPopup" >--%>
<%--<table class="table" id="userAssignTable">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th>--%>
<%--Chọn nhân viên--%>
<%--</th>--%>
<%--<th>--%>
<%--Họ và tên--%>
<%--</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--<div id="fieldHidden">--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="modal-footer">--%>
<%--<button type="button" class="btn btn-default" id="btnAssignBuildingForUser">Giao tòa nhà cho nhân viên</button>--%>
<%--</div>--%>
<%--</div>--%>

<%--</div>--%>
<%--</div>--%>
<%--</security:authorize>--%>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var processed1 = $("#id_processed1").val();
        if (processed1 == 1) {
            $("#id_paidMoney").show();
        } else {
            $("#id_paidMoney").hide();
        }
        console.log(processed1);
    });

    $("#btnDownload").click(function (e) {
        e.preventDefault();
        var  data = {};
        data["processed1"] = $("#processed1").val();
        data["paidMoney"] = $("#paidMoney").val();
        data["nameCustomer"] = $("#nameCustomer").val();
        data["nameUser"] = $("#nameUser").val();
        data["startDate"] = $("#startDate").val();
        data["endDate"] = $("#endDate").val();
        data["startDateConsume"] = $("#startDateConsume").val();
        data["endDateConsume"] = $("#endDateConsume").val();
        data["startDateUnitprice"] = $("#startDateUnitprice").val();
        data["endDateUnitPrice"] = $("#endDateUnitPrice").val();
        data["deleteBuilding"] = $('input[name=deleteBuilding]:checked').val()

        $.ajax({
            url: '${download}',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                <%--window.location.href = "<c:url value='/admin/building/list?message=insert_success'/>";--%>
            },
            error: function(res) {
                <%--console.log(res);--%>
                <%--window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";--%>
            }
        });
    })

    $("#processed1").change(function () {

        if ($(this).val() == 1) {
            $("#id_paidMoney").show();
        } else {
            $("#id_paidMoney").hide();
            $("#paidMoney").val('');
        }
        console.log($(this).val());
    });
    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteContract(dataArray);
        });
    }
    function deleteContract(data) {
        $.ajax({
            url: '${API}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/list-contract?message=delete_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/list-contract?message=error_system'/>";
            }
        });
    }
    <%--var arrayUsers = [];--%>
    <%--var buildingIdGlobal ="" ;--%>
    <%--$(document).ready(function () {--%>
    <%--$('#tableList #btnAssignBuilding').click(function (e) {--%>
    <%--e.preventDefault();--%>
    <%--openModelAssignBuilding();--%>
    <%--loadUserAssignBuilding($(this).attr('buildingId'),'');--%>
    <%--});--%>
    <%--});--%>
    <%--$('#btnSearchUserPopup').keyup(function(e){--%>
    <%--console.log(arrayUsers);--%>
    <%--var username = $('#btnSearchUserPopup').val();--%>
    <%--var buildingIdHidden = '<input type="hidden" name="buildingId" value=' + buildingIdGlobal + ' id="buildingId"></input>';--%>
    <%--$('#fieldHidden').html(buildingIdHidden);--%>
    <%--var row = '';--%>
    <%--for(var i=0;i<arrayUsers.length;i++){--%>
    <%--if(arrayUsers[i].username.indexOf(username) > -1 ){--%>
    <%--row += '<tr>';--%>
    <%--// chú ý checked--%>
    <%--row += '<td class="text-center"><input type="checkbox" name="checkList" onchange = "changeRadio('+arrayUsers[i].userId+')" value="'+arrayUsers[i].userId+'"  id="checkbox_'+arrayUsers[i].userId+'" class="check-box-element" ' + arrayUsers[i].checked + '/></td>';--%>
    <%--row += '<td class="text-center">' +arrayUsers[i].username+ '</td>';--%>
    <%--row += '</tr>';--%>

    <%--}--%>
    <%--}--%>
    <%--$('#userAssignTable tbody').html(row);--%>
    <%--});--%>

    <%--$('#btnAssignBuildingForUser').click(function (e) {--%>
    <%--console.log(arrayUsers);--%>
    <%--var users = [];--%>
    <%--for(var i = 0;i<arrayUsers.length;i++){--%>
    <%--if(arrayUsers[i].checked == "checked"){--%>
    <%--users.push(arrayUsers[i].userId);--%>
    <%--}--%>
    <%--}--%>
    <%--console.log(users);--%>
    <%--console.log(buildingIdGlobal);--%>
    <%--//         e.preventDefault();--%>
    <%--//         var buildingId = $('#fieldHidden').find('#buildingId').val();--%>
    <%--//         var users = $('#userAssignTable').find('tbody input[type=checkbox]:checked').map(function () {--%>
    <%--//             return $(this).val();--%>
    <%--//         }).get();--%>

    <%--updateBuilding(users, buildingIdGlobal);--%>
    <%--});--%>


    <%--$('#btnSearch').click(function () {--%>
    <%--$('#listForm').submit();--%>
    <%--});--%>


    <%--$('.btnPriority').click(function (event) {--%>
    <%--event.preventDefault();--%>
    <%--buildingId = $(this).attr("buildingId");--%>
    <%--action = $(this).attr("action");--%>
    <%--updatePrioritize(action,buildingId);--%>
    <%--});--%>


    <%--function updateBuilding(data, id) {--%>
    <%--$.ajax({--%>
    <%--url: '${API}/'+id+'/users',--%>
    <%--type: 'POST',--%>
    <%--dataType: 'json',--%>
    <%--contentType:'application/json',--%>
    <%--data: JSON.stringify(data),--%>
    <%--success: function(res) {--%>
    <%--window.location.href = "<c:url value='/admin/building/list?message=update_success'/>";--%>
    <%--},--%>
    <%--error: function(res) {--%>
    <%--console.log(res);--%>
    <%--window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    <%--function updatePrioritize(action,id) {--%>
    <%--$.ajax({--%>
    <%--url: '${API}/'+id+'/priority?action='+action,--%>
    <%--type: 'PUT',--%>
    <%--dataType: 'json',--%>
    <%--contentType:'application/json',--%>
    <%--data: JSON.stringify(),--%>
    <%--success: function(res) {--%>
    <%--window.location.href = "<c:url value='/admin/building/list?message=update_success'/>";--%>
    <%--},--%>
    <%--error: function(res) {--%>
    <%--console.log(res);--%>
    <%--window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    <%--function deleteBuilding(data) {--%>
    <%--$.ajax({--%>
    <%--url: '${API}',--%>
    <%--type: 'DELETE',--%>
    <%--contentType:'application/json',--%>
    <%--data: JSON.stringify(data),--%>
    <%--success: function(res) {--%>
    <%--window.location.href = "<c:url value='/admin/building/list?message=delete_success'/>";--%>
    <%--},--%>
    <%--error: function(res) {--%>
    <%--console.log(res);--%>
    <%--window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    <%--function warningBeforeDelete() {--%>
    <%--showAlertBeforeDelete(function () {--%>
    <%--event.preventDefault();--%>
    <%--var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {--%>
    <%--return $(this).val();--%>
    <%--}).get();--%>
    <%--deleteBuilding(dataArray);--%>
    <%--});--%>
    <%--}--%>

    <%--function openModelAssignBuilding() {--%>
    <%--$('#assignBuildingModal').modal();--%>
    <%--}--%>

    <%--function User(userId, username, checked) {--%>
    <%--this.userId = userId;--%>
    <%--this.username = username;--%>
    <%--this.checked = checked;--%>
    <%--}--%>


    <%--function changeRadio(id){--%>
    <%--for(var i=0;i<arrayUsers.length;i++){--%>
    <%--if(arrayUsers[i].userId == id){--%>
    <%--if(arrayUsers[i].checked == ""){--%>
    <%--arrayUsers[i].checked = "checked";--%>
    <%--}else{--%>
    <%--arrayUsers[i].checked = "";--%>
    <%--}--%>
    <%--break;--%>
    <%--}--%>
    <%--}--%>
    <%--}--%>
    <%--//function User()--%>
    <%--function loadUserAssignBuilding(buildingId,username) {--%>
    <%--buildingIdGlobal = buildingId;--%>
    <%--$('#btnSearchUserPopup').val("");--%>
    <%--var buildingIdHidden = '<input type="hidden" name="buildingId" value=' + buildingId + ' id="buildingId"></input>';--%>
    <%--$('#fieldHidden').html(buildingIdHidden);--%>
    <%--$.ajax({--%>
    <%--url: '${loadUser}?role=USER&buildingid='+buildingId,--%>
    <%--type: 'GET',--%>
    <%--success: function(res) {--%>
    <%--var row = '';--%>
    <%--arrayUsers = [];--%>
    <%--$.each(res, function (index,user) {--%>
    <%--var users = new User(user.id,user.fullName,user.checked);--%>
    <%--arrayUsers.push(users);--%>

    <%--row += '<tr>';--%>
    <%--// chú ý checked--%>
    <%--row += '<td class="text-center"><input type="checkbox" name="checkList" onchange = "changeRadio('+user.id+')"  value="'+user.id+'" id="checkbox_'+user.id+'"   class="check-box-element" ' + user.checked + '/></td>';--%>
    <%--row += '<td class="text-center">' +user.fullName+ '</td>';--%>
    <%--row += '</tr>';--%>
    <%--});--%>
    <%--$('#userAssignTable tbody').html(row);--%>
    <%--console.log(arrayUsers)--%>

    <%--},--%>
    <%--error: function(res) {--%>
    <%--console.log(res);--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>
</script>
</body>
</html>
