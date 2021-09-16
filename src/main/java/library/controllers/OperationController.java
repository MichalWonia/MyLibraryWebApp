package library.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import library.DAO.BooksDAO;
import library.entity.Books;

public class OperationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String path = "PATH_TO_BOOK_COVERS_FOLDER";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operation = request.getParameter("operation").toLowerCase();
		
		switch (operation) {
		case "deletebook":
			deleteBookById(request, response);
			break;

		case "editbook":
			showEditBookForm(request, response);
			break;

		case "deletebookcover":
			deleteBookCover(request, response);
			break;

		case "addbookcoverform":
			addBookCoverForm(request, response);
			break;
			
		default:
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String operation = request.getParameter("operation").toLowerCase();
			
		switch (operation) {
		case "updatebook":
			updateBook(request, response);
			break;
		case "addbookcover":
			addBookCover(request, response);
			break;
		default:
			break;
		}

	}

	private void addBookCoverForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		request.setAttribute("bookId", bookId);
		request.getRequestDispatcher("addBookCover.jsp").forward(request, response);
	}
	
	private void deleteBookCover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
	
		BooksDAO booksDao = new BooksDAO();
		Books book = booksDao.getBookById(bookId);
		
		File fileOnDisc = new File(path + book.getFileName());
		if (fileOnDisc.delete()) {
			System.out.println("File got deleted from filesystem");
		} else {
			System.out.println("File not deleted from filesystem");
		}
		
		booksDao.deleteBookCoverById(bookId);
		
		listingBooks(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		System.out.println(bookId);
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String bookAbstract = request.getParameter("bookAbstract");
		String status = request.getParameter("status");
		new BooksDAO().updateBook(bookId, author, title, bookAbstract, status);
		listingBooks(request, response);
	}

	private void showEditBookForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("path", path);
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Books book = new BooksDAO().getBookById(bookId);
		request.setAttribute("book", book);
		request.getRequestDispatcher("editBookForm.jsp").forward(request, response);
	}

	private void deleteBookById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));

		Books book = new BooksDAO().getBookById(bookId);
		new BooksDAO().deleteBookById(bookId);
		// logic for file deletion from fileSystem
		File fileOnDisc = new File(path + book.getFileName());
		if (fileOnDisc.delete()) {
			System.out.println("File got deleted from filesystem");
		} else {
			System.out.println("File not deleted from filesystem");
		}
		listingBooks(request, response);
	}

	private void listingBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Books> books = new BooksDAO().listBooks();
		request.setAttribute("books", books);
		request.setAttribute("path", path);
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
	}
	
	private void addBookCover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		try {
			List<FileItem> images = upload.parseRequest(request);
			for (FileItem image : images) {
				String name = image.getName();
				name = name.substring(name.lastIndexOf("\\") + 1);

				File file = new File(path + name);
				if (!file.exists()) {
					new BooksDAO().addBookCover(bookId, name);
					image.write(file);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		listingBooks(request, response);
	}
}
