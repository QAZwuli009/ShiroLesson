<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>layout.html</title>

<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<meta name="content-type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<style type="text/css">
body {
	font-size: 14px;
}
</style>
<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript" src="jquery.easyui.min.js"></script>
<script type="text/javascript">
	function urlClick(myTitle,myUrl){
		var ifExist=$("#myTabs").tabs("exists",myTitle);
		if(!ifExist){
			$("#myTabs").tabs("add",{
				title:myTitle,
				closable:true,
				content:"<iframe frameborder='0' width='100%' height='100%' scrolling='no' style='display:block' src='"+myUrl+"'></iframe>",
			});
		}
		$("#myTabs").tabs("select",myTitle);
	}
</script>
</head>

<body style="padding:0.5px;margin: 0.5px">
	<div class="easyui-layout" data-options="fit:true">
		<!-- 北部  只能设置高度  一般不会设置宽度 -->
		<div data-options="region:'north'"
			style="height:20%;background-image: url('dd.png');background-repeat: repeat-x;;background-size:665px 203px ">
			<div style="height: 85%"></div>
			<div style="text-align: right;width: 80%">
				<a href="#" style="color:red;text-decoration: none;">安全退出</a>
			</div>
		</div>
		<div data-options="region:'west',split:true" title="导航菜单"
			style="width:15%;">
			<div class="easyui-accordion" style="width:500px;height:300px;">
				<div title="权限管理" style="overflow:auto;padding:10px;">
					<c:forEach var="menu" items="${requestScope.menuList}" >				
						<a href="javascript:urlClick('${menu.menuName }','${pageContext.request.contextPath }${menu.menuUrl }')" style="text-decoration: none;"><img alt=""src="themes/icons/search.png" style="margin-top: 4px">${menu.menuName }<br /></a>
					</c:forEach>
				</div>
				<div title="系统设置" style="padding:10px;"></div>
			</div>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<div id="myTabs" class="easyui-tabs" style="width:100%;height:100%">
				<div title="欢迎使用" style="padding:10px"></div>
			</div>
		</div>
	</div>
</body>
</html>
