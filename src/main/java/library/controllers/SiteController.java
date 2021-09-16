package library.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.DAO.BooksDAO;
import library.entity.Books;

public class SiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String path = "PATH_TO_BOOK_COVERS_FOLDER";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page").toLowerCase();
		
		switch (page) {
		case "booklist":
			listingBooks(request, response);
			break;
		case "viewbook":
			viewBook(request, response);
			break;
		case "home":
			homePage(request, response);
			break;
		default:
			homePage(request, response);
		}
	}

	private void listingBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Books> books = new BooksDAO().listBooks();
		request.setAttribute("books", books);
		request.setAttribute("path", path);
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
	}

	private void homePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	private void viewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Books book = new BooksDAO().getBookById(bookId);
		request.setAttribute("book", book);
		request.setAttribute("path", path);
		request.getRequestDispatcher("viewBook.jsp").forward(request, response);
	}
}
