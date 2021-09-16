package library.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.internal.build.AllowSysOut;

import library.DAO.BooksDAO;
import library.entity.Books;

//This controller is used only to handle adding new book operation

@MultipartConfig
public class FilesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String path = "PATH_TO_BOOK_COVERS_FOLDER";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action").toLowerCase();
			
		switch (action) {
		case "addbook":
			showAddBookForm(request, response);
			break;
		
		default:
			listingBooks(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		filesUpload(request, response);
		listingBooks(request, response);
	}

	
	public void filesUpload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fieldName = "";
		String fieldValue = "";
		String fileName = "";
		Map<String, String> parameterMap = new HashMap<String, String>();

		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// Process regular form field (input type="text|radio|checkbox|etc", select,
					// etc).
					// store all parameters with values
					fieldName = item.getFieldName(); // all fields name
					fieldValue = item.getString(); // all fields values
					parameterMap.put(fieldName, fieldValue);

				} else {
					// Process form file field (input type="file").
					fileName = item.getName();
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					System.out.println(fileName);
					File file = new File(path + fileName);
					if (!file.exists()) {
						item.write(file);
					}
				}
			}
		} catch (Exception e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}

		new BooksDAO().addNewBook(parameterMap.get("author"), parameterMap.get("title"),
				parameterMap.get("bookAbstract"), parameterMap.get("status"), fileName);
	}

	private void showAddBookForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("addBookForm.jsp").forward(request, response);
	}

	private void listingBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Books> books = new BooksDAO().listBooks();
		request.setAttribute("books", books);
		request.setAttribute("path", path);
		request.getRequestDispatcher("bookList.jsp").forward(request, response);
	}
}
