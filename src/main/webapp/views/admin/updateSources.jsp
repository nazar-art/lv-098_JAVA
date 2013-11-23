<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/ajax.js" />"></script>

<!-- Bootstrap -->
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>

<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>

<link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />"
	rel="stylesheet" type="text/css" />

<link href="<c:url value="/resources/css/admin.css" />" rel="stylesheet"
	type="text/css" />

<form action="" id="updateSource">
	<div>
		<fieldset>
			<legend align="left">
				<strong>Source for update:</strong>
			</legend>
			<select name="sources" size="1">
				<c:forEach var="Source" items="${listSource}">
					<option value="${Source.getSourceName()}">${Source.getSourceName()}</option>
				</c:forEach>
			</select> <br> <input class="btn btn-primary" type="button"
				value="Update source" onclick="doAjaxUpdateSource()">

		</fieldset>
	</div>
</form>
<br>
<div id="upSource"></div>