<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

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
					action="${pageContext.request.contextPath}/FilesController" id="login-form" method="post" enctype="multipart/form-data">

					<label> Select book cover:</label> </br>
					<input type="file" name="files" multiple>
					<br/>
						 		
					<label>Title: </label> <input type="text" name="title" required>
					<label>Author: </label> <input type="text" name="author" required>
					<label>Abstract: </label>
					<br />
					
					<textarea name="bookAbstract" rows="5" cols="61"></textarea>
					</br> 
					<label>Status:</label> <select name="status">
						<option value="unread book">unread book</option>
						<option value="book during reading">book during reading</option>
						<option value="book read">book read</option>
					</select>
		
					<input type="submit" value="Submit">
				</form>
			</div>
	</body>
</html>