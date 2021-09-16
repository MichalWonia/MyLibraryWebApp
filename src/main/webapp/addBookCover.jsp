<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link href="css/style_form.css" type="text/css" rel="stylesheet" />
<title>Add new user</title>
</head>

	<body>
		<h2 style="font-family: courier; text-align: center;"> Select cover for book with title: <%=request.getParameter("title") %>  </h2>
			<div>
				<form action="${pageContext.request.contextPath}/OperationController?operation=addBookCover&bookId=<%= request.getAttribute("bookId") %>" id="login-form" method="post" enctype="multipart/form-data">
		
					<label> Select book cover:</label> </br> 
					<input type="file" name="files" multiple> <br /> 
					<input type="submit" value="Submit">
		
				</form>
			</div>
	</body>
</html>