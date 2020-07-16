<%--
  Created by IntelliJ IDEA.
  User: vuthe
  Date: 6/20/2020
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<p class="my-4">Shop Name</p>
<div class="list-group">
    <c:forEach items="${categories}" var="category">
        <a href="${contextRoot}/show/category/${category.id}/products" class="list-group-item"
           id="a_${category.name}">${category.name}</a>
    </c:forEach>
</div>
