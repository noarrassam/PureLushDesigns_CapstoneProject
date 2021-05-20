<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty requestScope.ErrCtlMsg}">
	 		<script type="text/javascript">
	 			insertMessage("${requestScope.ErrCtlMsg}", true);
	 		</script>
        </c:if>
        
        <c:if test="${not empty requestScope.SucCtlMsg}">
	 		<script type="text/javascript">
	 			insertMessage("${requestScope.SucCtlMsg}", false);
	 		</script>
        </c:if>  
</body>
</html>