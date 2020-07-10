<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/admin/customer"/>
<html>
<head>
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
                <li class="active">Chi tiết báo giá</li>
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
                            <div class="tabs_div">
                                <ul>
                                    <li>Thông tin khách hàng</li>
                                    <li>Quá trình CSKH </li>
                                    <li>Tình trạng hợp đồng</li>
                                    <li>Tạo hợp đồng</li>
                                </ul>
                                <div>
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <td class="success">Tên khách hàng </td>
                                            <td>${model.name}</td>
                                        </tr>
                                        <tr>
                                            <td class="success">Di động: </td>
                                            <td>${model.phoneNumber}</td>
                                        </tr>
                                        <tr>
                                            <td class="success">Email: </td>
                                            <td>${model.email}</td>
                                        </tr>
                                        <tr>
                                            <td class="success">Người nhập: </td>
                                            <td>${model.createdBy}</td>
                                        </tr>
                                        <tr>
                                            <td class="success">Nhu cầu: </td>
                                            <td>${model.need}</td>
                                        </tr>
                                        <tr>
                                            <td class="success">Ngày gửi email: </td>
                                            <td>${model.dateMail}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div>
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <td class="success">Ngày </td>
                                            <td class="success">Nội dung: </td>
                                        </tr>
                                        <c:forEach items="${model.careDetail}" var="item">
                                            <tr>
                                                <td class="success">
                                                    <div class="form-group">
                                                        <input type="text" class="form-control"  value="${item.date}">
                                                    </div>
                                                </td>
                                                <td class="success">
                                                    <div class="form-group">
                                                        <input type="text" class="form-control" value="${item.content}">
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td class="success">
                                                <div class="form-group">
                                                    <input type="text" class="form-control" id="date">
                                                </div>
                                            </td>
                                            <td class="success">
                                                <div class="form-group">
                                                    <input type="text" class="form-control" id="content">
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <button type="button" class="btn btn-primary btn-md" id="btnCareDetail">Xác nhận</button>
                                </div>
                                <div>
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <td class="success">Tình trạng: </td>
                                            <td class="success">
                                                <div class="radio">
                                                    <form:radiobutton path="status" value="1"/>Ký hợp đồng
                                                </div>
                                                <div class="radio">
                                                    <form:radiobutton path="status" value="2"/>Không ký hợp đồng
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="success">Ghi chú: </td>
                                            <td class="success">
                                                <div class="form-group">
                                                    <form:textarea path="note" id = "note"  cssClass="form-control"/>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <button type="button" class="btn btn-primary btn-md" id="btnStatus">Xác nhận</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
    </div>
</div>


<!-- you need to include the shieldui css and js assets in order for the charts to work -->
<link rel="stylesheet" type="text/css" href="http://www.shieldui.com/shared/components/latest/css/light-glow/all.min.css" />
<script type="text/javascript" src="http://www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>

<script type="text/javascript">
    function getDate() {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();
        if(dd<10){
            dd='0'+dd;
        }
        if(mm<10){
            mm='0'+mm;
        }
        today = dd+'/'+mm+'/'+yyyy;
        return today;
    }

    $(document).ready(function () {
        var today = getDate();
        $('#date').val(today);
    });

    jQuery(function ($) {
        $(".tabs_div").shieldTabs();
    });
</script>

<script>

    $('#btnCareDetail').click(function (event) {
        event.preventDefault();
        var dataArray = {};
        var id = ${model.id};
        dataArray["date"] = $('#date').val();
        dataArray["content"] = $('#content').val();
        updateCustomerService(dataArray,id);
    });

    $('#btnStatus').click(function (event) {
        event.preventDefault();
        var dataArray = {};
        var id = ${model.id};
        if ($("input[name='status'][value='1']").prop("checked")){
            dataArray["status"] = 1;
        }else {
            dataArray["status"] = 2;
        }
        dataArray["note"] = $('#note').val();
        updateStatus(dataArray,id);
    });

    function updateStatus(data, id) {
        $.ajax({
            url: '${formUrl}/'+id+'/status',
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/customer/detail/"+res.id+"?message=update_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/customer/detail/"+res.id+"?message=error_system'/>";
            }
        });
    }

    function updateCustomerService(data, id) {
        $.ajax({
            url: '${formUrl}/'+id+'/customer_service',
            type: 'PUT',
            dataType: 'json',
            contentType:'application/json',
            data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/customer/detail/"+res.id+"?message=update_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/customer/detail/"+res.id+"?message=error_system'/>";
            }
        });
    }
</script>

<style>
    .pb-product-details-ul {
        list-style-type: none;
        padding-left: 0;
        color: black;
    }

    .pb-product-details-ul > li {
        padding-bottom: 5px;
        font-size: 15px;
    }

    #gradient {
        /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#feffff+0,ddf1f9+31,a0d8ef+62 */
        background: #feffff; /* Old browsers */
        background: -moz-linear-gradient(left, #feffff 0%, #ddf1f9 31%, #a0d8ef 62%); /* FF3.6-15 */
        background: -webkit-linear-gradient(left, #feffff 0%,#ddf1f9 31%,#a0d8ef 62%); /* Chrome10-25,Safari5.1-6 */
        background: linear-gradient(to right, #feffff 0%,#ddf1f9 31%,#a0d8ef 62%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#feffff', endColorstr='#a0d8ef',GradientType=1 ); /* IE6-9 */
        border: 1px solid #f2f2f2;
        padding: 20px;
    }

    #hits {
        border-right: 1px solid white;
        border-left: 1px solid white;
        vertical-align: middle;
        padding-top: 15px;
        font-size: 17px;
        color: white;
    }

    #fan {
        vertical-align: middle;
        padding-top: 15px;
        font-size: 17px;
        color: white;
    }

    .pb-product-details-fa > span {
        padding-top: 20px;
    }
</style>
<!-- Responsive Product Details - END -->

</div>

</body>
</html>