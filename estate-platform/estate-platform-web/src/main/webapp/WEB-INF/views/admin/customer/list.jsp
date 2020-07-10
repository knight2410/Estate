<%@ page import="com.estate.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin/customer/list"/>
<c:url var="API" value="/api/admin/customer"/>
<c:url var="loadUser" value="/api/admin/user"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách khách hàng</title>
</head>
<body>
<div class="main-content">
    <form:form commandName="model" action="${formUrl}" id="listForm" method="GET">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/admin/home">Trang chủ</a>
                    </li>
                    <li class="active">Danh sách khách hàng</li>
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
                                                                <input type="text" name="name"
                                                                       class="form-control input-sm"
                                                                       placeholder="Tên khách hàng"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="fg-line">
                                                                <input type="text" name="phoneNumber"
                                                                       class="form-control input-sm"
                                                                       placeholder="Di động "/>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="fg-line">
                                                                <input type="text" name="email"
                                                                       class="form-control input-sm"
                                                                       value="${model.email}"
                                                                       placeholder="Email"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                            <div class="col-sm-6">
                                                                <button id="btnSearch" type="button"
                                                                        class="btn btn-sm btn-success">
                                                                    <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110">Tìm kiếm</i>
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
                                                    <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                                       data-toggle="tooltip" title="Thêm khách hàng mới" href='<c:url value="/admin/customer/edit"/>'>
                                                            <span>
                                                            <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                        </span>
                                                    </a>
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <button id="btnDelete" type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" disabled
                                                            data-toggle="tooltip" title="Xóa khách hàng" onclick="warningBeforeDelete()">
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
                                    <display:table name="model.listResult" cellspacing="0" cellpadding="0" requestURI="${formUrl}"
                                                   partialList="true" size="${model.totalItems}" id="tableList" pagesize="${model.maxPageItems}" export="false"
                                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                                   style="margin: 3em 0 1.5em;">
                                        <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                        headerClass="center select-cell">
                                                <fieldset>
                                                    <input type="checkbox" name="checkList" value="${tableList.id}" id="checkbox_${tableList.id}" class="check-box-element"/>
                                                </fieldset>
                                        </display:column>
                                        <display:column headerClass="text-left" property="name" title="Họ tên"/>
                                        <display:column headerClass="text-left" property="phoneNumber" title="Di động"/>
                                        <display:column headerClass="text-left" property="email" title="email"/>
                                        <display:column headerClass="text-left" property="modifiedBy" title="Người nhập"/>
                                        <display:column headerClass="text-left" property="createdDate" title="Ngày nhập"/>
                                        <display:column headerClass="col-actions" title="Trạng Thái">
                                            <c:if test="${tableList.existCustomerInContract == true}">
                                                <a class="btn btn-sm btn-success btn-edit" data-toggle="tooltip"
                                                   title="Đã Có Hợp Đồng">Đã Có HĐ</a>
                                            </c:if>
                                            <c:if test="${tableList.existCustomerInContract == false}">
                                                <a class="btn btn-sm btn-danger btn-edit btnCreateContract"
                                                   title="Chưa Có Hợp Đồng"> Chưa Có HĐ
                                                </a>
                                            </c:if>
                                        </display:column>
                                        <display:column headerClass="col-actions" title="Thao tác">
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật khách hàng" href='<c:url value="/admin/customer/edit?id=${tableList.id}"/>'>Chỉnh Sửa</a>

                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                               title="Lịch Sử" href='<c:url value="/admin/contract-history-customer?idCustomer=${tableList.id}"/>'>Lịch Sử</a>
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
    <security:authorize access="hasRole('MANAGER')">
        <!-- Modal -->
        <div class="modal fade" id="assignCustomerModal" role="dialog">
            <div class="modal-dialog modal-lg">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Giao User Cho Nhân Viên</h4>
                    </div>
                    <div class="modal-body">
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
                        <button type="button" class="btn btn-default" id="btnAssignCustomerForUser">Giao khách hàng cho nhân viên</button>
                    </div>
                </div>

            </div>
        </div>
    </security:authorize>
</div>
<script type="text/javascript">
    var users = "";
    $(document).ready(function () {
        $('#tableList #btnAssignCustomer').click(function (e) {
            e.preventDefault();
            openModelAssignCustomer();
            loadUserAssignCustomer($(this).attr('customerId'));
        });
    });

    $('#btnSearch').click(function () {
        $('#listForm').submit();
    });


    $('#btnAssignCustomerForUser').click(function (e) {
        e.preventDefault();
        var customerId = $('#fieldHidden').find('#customerId').val();
        var users = $('#userAssignTable').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        updateCustomer(users, customerId);
    });

    function updateCustomer(data, id) {
        $.ajax({
            url: '${API}/'+id+'/users',
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/customer/list?message=update_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/customer/list?message=error_system'/>";
            }
        });
    }

    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteCustomer(dataArray);
        });
    }
    function deleteCustomer(data) {
        $.ajax({
            url: '${API}',
            type: 'DELETE',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/customer/list?message=delete_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/customer/list?message=error_system'/>";
            }
        });
    }

    function openModelAssignCustomer() {
        $('#assignCustomerModal').modal();
    }

    function loadUserAssignCustomer(customerId) {
        var customerIdHidden = '<input type="hidden" name="customerId" value=' + customerId + ' id="customerId"></input>';
        $('#fieldHidden').html(customerIdHidden);
        $.ajax({
            url: '${loadUser}?role=USER&customerid='+customerId,
            type: 'GET',
            success: function(res) {
                var row = '';
                $.each(res, function (index,user) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" name="checkList" value="'+user.id+'" id="" class="check-box-element" ' + user.checked + '/></td>';
                    row += '<td class="text-center">' +user.fullName+ '</td>';
                    row += '</tr>';
                });
                $('#userAssignTable tbody').html(row);
            },
            error: function(res) {
                console.log(res);
            }
        });
    }
</script>
</body>
</html>
