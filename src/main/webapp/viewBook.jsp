<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false"%>
<%@ page import="library.entity.Books" %>  
 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>View book</title>
	<link href="css/style.css" rel="stylesheet" />

	<style type="text/css">
	
		@font-face {
		  font-family: myFont;
		  src: url(fonts/Lobster-Regular.ttf);
		}
		
	</style>
</head>


<body>

	<%@ include file="include/navbar.jsp" %>
	
	<%! Books book; String path; %>
	
	<% 
	book = (Books)request.getAttribute("book");
	path = (String)request.getAttribute("path");
	%>

<div class="wrapper">
	<div style="margin-top: 40px;">
		<table>
			<thead>
				<tr>
					<th>Book cover</th>
					<th>Informations</th>
					<th>Abstract</th>
				</tr>
			</thead>
				<tr>
					<td><img src="<%=path+book.getFileName()%>" height='200'></td>
					<td> 
					File ID: <%= book.getId() %> 
					</br></br>
					File name: <%= book.getFileName() %> 
					</br></br>
					<%
						if(book.getAuthor() != null){
							out.print("Author: " +book.getAuthor());
						}
					%>
					</br></br>
					<%
						if(book.getTitle() != null){
							out.print("Title: " +book.getTitle());
						}
					%>
					</br></br>
					<%
						if(book.getStatus() != null){
							out.print("Status: " +book.getStatus());
						}
					%>
					</td>
					<td> 
					<%
						if(book.getBookAbstract() != null){
							out.print("Abstract: </br>" +book.getBookAbstract());
						}
					%>
					</td>
				</tr>
		</table>
	</div>	
	<div class="push"></div>
</div>

<%@ include file="include/footer.jsp" %>

</body>
</html>