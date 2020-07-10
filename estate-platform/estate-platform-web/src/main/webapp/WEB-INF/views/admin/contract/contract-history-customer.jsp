<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.estate.utils.SecurityUtils" %>
<%@include file="/common/taglib.jsp" %>
<%@ page import="java.util.Date" %>
<c:url var="formUrl" value="/admin/contract-history-customer"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lịch Sử Hợp Đồng</title>
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
                    <form:hidden path="idCustomer" id="idCustomer" />
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
                                            <div style="margin-left: -12px;width: 253px" class="col-sm-3">
                                                <div class="fg-line">
                                                    <form:input path="nameBuilding" cssStyle="width: 253px" id="nameBuilding"
                                                                placeholder="Mã Phòng"/>
                                                </div>

                                            </div>

                                            <div style="margin-left: 27px;width: 253px" class="col-sm-3">
                                                <div class="fg-line">
                                                    <form:input cssStyle="width: 253px" path="nameCustomer" id="nameCustomer"
                                                                placeholder="Tên Khách Hàng"/>
                                                </div>
                                            </div>
                                            <br>
                                            <br>
                                            <div class="form-group">
                                                <p style="margin-left: 13px">Ngày Tạo Hợp Đồng</p>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="startContract" id="startContract"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Từ ngày"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="endContract" id="endContract"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Đến ngày"/>
                                                    </div>
                                                </div>
                                                <br>
                                                <br>
                                                <p style="margin-left: 13px">Ngày Xóa</p>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="startDelete" id="startDelete"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Từ ngày"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="fg-line">
                                                        <form:input type="date" path="endDelete" id="endDelete"
                                                                    cssClass="form-control input-sm"
                                                                    placeholder="Đến ngày"/>
                                                    </div>
                                                </div>
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
                                <display:column headerClass="text-left" property="createdDate" title="Ngày Tạo Hợp Đồng"/>
                                <display:column headerClass="text-left" property="nameUser" title="Người Tạo Hợp Đồng"/>
                                <display:column headerClass="text-left" property="nameCustomer" title="Tên Khách Hàng"/>
                                <display:column headerClass="text-left" property="isDelete" title="Ngày Hết Hợp Đồng"/>
                                <display:column headerClass="col-actions" title="Thao tác">
                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
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
</div>
<script type="text/javascript">
    $(document).ready(function () {

    });
</script>
</body>
</html>
