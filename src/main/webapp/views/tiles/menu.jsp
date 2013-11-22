<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">

<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">

<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet"
	type="text/css" />

<!-- Bootstrap -->
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>

<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>

<link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />"
	rel="stylesheet" type="text/css" />

<div id="accordion" align="left">

	<h6>Home</h6>
	<div class="divmenu">
		<p>
			<a class="button" href="welcomes" title="Home">Home</a>
		</p>
	</div>
	<sec:authorize access="hasRole('ROLE_USER')">

		<h6>Statistics:</h6>
		<div class="divmenu">
			<p>
				<a class="button" href="secure_inProgres" title="Charts">Charts</a>
			</p>

			<p>
				<a class="button" href="secure_showIpListFromWL"
					title="Show Ip list from White List">Show Ip list from White
					List</a>
			</p>

			<p>
				<a class="button" href="secure_showIpListFromBL"
					title="Show Ip list from Black List">Show Ip list from Black
					List</a>
			</p>

			<p>
				<a class="button" href="secure_blackListMap" title="Blacklist map">Blacklist
					map</a>
			</p>

			<p>
				<a class="button" href="secure_whiteListMap" title="Whitelist map">Whitelist
					map</a>
			</p>

			<p>
				<a class="button" href="secure_inProgres" title="Top oldest IP">Top
					oldest IP</a>
			</p>

		</div>

		<h6>Ip data:</h6>
		<div class="divmenu">

			<p>
				<a class="button" href="secure_showIpListByCity" title="IP by city">
					IP by city</a>
			</p>

			<p>
				<a class="button" href="secure_showIpListByCountry"
					title="Ip by country">Ip by country</a>
			</p>

		</div>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h6>Feeds:</h6>
		<div class="divmenu">

			<p>
				<a class="button" href="admin_addNewFeed" id="getContentAddNewFeed"
					title="Add new Feed">Add new Feed</a>
			</p>

			<p>
				<a class="button" href="admin_deleteSource"
					id="getContentDeleteFeed" title="Delete Feed">Delete Feed</a>
			</p>

			<p>
				<a class="button" href="admin_addIpToList" id="getContentAddIpToWl"
					title="Add Ip to list">Add Ip to list</a>
			</p>

			<p>
				<a class="button" href="admin_deleteIpFromList"
					id="getContentDeleteWlIp" title="Delete IP from list">Delete IP
					from list</a>
			</p>

			<p>
				<a class="button" href="admin_updateSources" title="updateSources">Update
					Sources</a>
			</p>

		</div>
	</sec:authorize>
</div>

<script>
	$("#accordion").accordion({
		collapsible : true
	});
</script>