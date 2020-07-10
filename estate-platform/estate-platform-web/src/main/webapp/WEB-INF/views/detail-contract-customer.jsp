<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.estate.utils.SecurityUtils" %>
<%@include file="/common/taglib.jsp" %>
<%@ page import="java.util.Date" %>
<c:url var="formUrl" value="/customers/detail"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chi Tiết Hợp Đồng</title>
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
                <li class="active">Chi Tiết Hợp Đồng</li>
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
                    <form:hidden path="idContract" id="idContract" />
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
                                <display:column headerClass="text-left" property="nameBuilding" title="Mã Phòng"/>
                                <display:column headerClass="text-left" property="createByContract" title="Người Tạo"/>
                                <display:column headerClass="text-left" property="nameCustomer" title="Tên Khách Hàng"/>
                                <display:column headerClass="text-left" property="createDateConsume" title="Ngày Tạo Hóa Đơn"/>
                                <display:column headerClass="text-left" property="powerNumber" title="Số Điện"/>
                                <display:column headerClass="text-left" property="waterNumber" title="Số Nước"/>
                                <display:column headerClass="text-left" property="electricityPrice" title="Giá Điên"/>
                                <display:column headerClass="text-left" property="waterPrice" title="Giá Nước"/>
                                <display:column headerClass="text-left" property="roomPrice" title="Giá Tiền Thuê"/>
                                <display:column headerClass="text-left" property="percentDaysStay" title="Số Tiền Nợ"/>
                                <display:column headerClass="text-left" property="amountPaid" title="Số Tiền Đã Đóng"/>
                                <display:column headerClass="text-left" property="amountPayable" title="Số Tiền Phải Đóng"/>
                            </display:table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</form:form>
</div>
<script type="text/javascript">
    $(document).ready(function () {

    });
</script>
</body>
</html>
