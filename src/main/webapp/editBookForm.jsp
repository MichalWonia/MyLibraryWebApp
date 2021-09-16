<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

<%@ page import="library.entity.Books"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Add new user</title>
<link href="css/style_form.css" type="text/css" rel="stylesheet" />

</head>
	<body>
	
		<h2 style="font-family: courier; text-align: center;">Fill the missing fields:</h2>
	
		<div>
				<form
					action="${pageContext.request.contextPath}/OperationController" id="login-form" method="post">
		
					<% Books book = (Books) request.getAttribute("book"); %>
		 
					<label>Title: </label> <input type="text" name="title" value="<%= book.getTitle() %>">
					<label>Author: </label> <input type="text" name="author" value="<%= book.getAuthor() %>">
					<label>Abstract: <br />
					</label>
					
					<textarea name="bookAbstract" rows="5" cols="61"><%= book.getBookAbstract() %></textarea>
					
					</br> 
					
					<label>Status:</label> <select name="status">
						<option value="unread book">unread book</option>
						<option value="book during reading">book during reading</option>
						<option value="book read">book read</option>
					</select>
		
					<input type="hidden" name="bookId" value="<%= book.getId() %>">
					<input type="hidden" name="operation" value="updateBook">
					<input type="submit" value="Submit">
				</form>
		</div>
	</body>
</html>