<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../base/ctx.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<body>
<h2>终于把项目配置好了</h2>
<table>
	<tr>
		<th>编号</th>
		<th>姓名</th>
	</tr>
	<tr>
		<c:if test="${empty list}">
			<tr>
				<td colspan="2" >明明就没得信息，看个毛</td>
			</tr>
		</c:if> 
		<c:forEach items="${list}" var="test">
			<td>${test.id}</td>
			<td>${test.name}</td>
		</c:forEach>
	</tr>
</table>

</body>
</html>
