<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

	<nav>
		<img src="images/books_navbar.jpg" class="navbar_img">
		<label class="home_label" style="font-family: myFont">Your library</label>
		
		<div class="links">
			<a class="home_link" style="font-family: myFont" href="${pageContext.request.contextPath}/SiteController?page=home">Home</a>
			<a class="book_list" style="font-family: myFont" href="${pageContext.request.contextPath}/SiteController?page=bookList">Your books</a>
		</div>
	</nav>