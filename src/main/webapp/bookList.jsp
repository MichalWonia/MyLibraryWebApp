<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of your books</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />

<style type="text/css">

	@font-face {
	  font-family: myFont;
	  src: url(fonts/Lobster-Regular.ttf);
	}

</style>

</head>

<body>

	<%@ include file="include/navbar.jsp" %>

 	<div class="wrapper">
		<div style="margin-top: 40px;">
		
			<h1 style="font-family: myFont;">List of your books</h1>
			
			<h3>
				Click here to add new book: <button type="button" id="button_add_book" onclick="window.location='${pageContext.request.contextPath}/FilesController?action=addBook';">Add new book</button>
			</h3>
			
			<table border="1">
				<thead>
					<tr>
						<th>Book cover</th>
						<th>Informations</th>
						<th>Abstract</th>
						<th>Action</th>
					</tr>
				</thead>
					<c:forEach items="${books}" var="book">  
					
						<c:url var="addCoverURL" value="OperationController">
							<c:param name="operation" value="addBookCoverForm"></c:param>
							<c:param name="bookId" value="${book.id}"></c:param>
							<c:param name="title" value="${book.title}"></c:param>
						</c:url>
						
						<c:url var="deleteCoverURL" value="OperationController">
							<c:param name="operation" value="deleteBookCover"></c:param>
							<c:param name="bookId" value="${book.id}"></c:param>
						</c:url>
						
						<c:url var="viewBookURL" value="SiteController">
							<c:param name="page" value="viewBook"></c:param>
							<c:param name="bookId" value="${book.id}"></c:param>
						</c:url>
						
						<c:url var="editBookURL" value="OperationController">
							<c:param name="operation" value="editBook"></c:param>
							<c:param name="bookId" value="${book.id}"></c:param>
						</c:url>
						
						<c:url var="deleteBookURL" value="OperationController">
							<c:param name="operation" value="deleteBook"></c:param>
							<c:param name="bookId" value="${book.id}"></c:param>
						</c:url>
				
						<tr>
							<td style="text-align: center; vertical-align: middle;">  
								<img src="${path}${book.fileName}" height='280' width='200'>
								<br/>
								<label> Delete cover: </label>
							
								<a href="${deleteCoverURL}">
								<button class="button_delete_cover" 
								onclick="if(!confirm('Are you sure to delete the book cover ?')) return false">
								Delete
								</button>
								</a>
							
								<br/>
								  
								<label> Add cover: </label>
								<button type="button" class="button_add_cover" onclick="window.location='${addCoverURL}';">Add</button>
							</td>
							
							<td>
								Book ID: ${book.id} 
								</br></br>
								Author: ${book.author} 
								</br></br>
								Title: ${book.title} 
								</br></br>
								Status: ${book.status} 
								</br></br>
							</td>
							
							<td> Abstract: </br> ${book.bookAbstract} </td>
							
							<td style="width:100px; text-align: center; vertical-align: middle;"> 
								<button type="button" class="button_view_book" onclick="window.location='${viewBookURL}';">View</button>
								</br>
								
								<button type="button" class="button_edit_book" onclick="window.location='${editBookURL}';">Edit</button>
								</br>
								
								<a href="${deleteBookURL}">
								<button class="button_delete_book" 
								onclick="if(!confirm('Are you sure to delete the book ?')) return false">
								Delete
								</button>
								</a>	
							</td>	
						</tr>
					</c:forEach>
			</table>
		</div>
		<div class="push"></div>
	</div>
	
	<%@ include file="include/footer.jsp" %>
	
</body>

</html>