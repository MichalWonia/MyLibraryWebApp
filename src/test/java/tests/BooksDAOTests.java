package tests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import library.DAO.BooksDAO;
import library.entity.Books;

public class BooksDAOTests {

	private BooksDAO booksDAO;

	@BeforeEach
	void init() {
		booksDAO = new BooksDAO();
	}
	
	@Test
	@Disabled // remove this to run the test
	public void getListOfAllBookTest() {
		Iterable<Books> booksList = booksDAO.listBooks();
		Assertions.assertNotNull(booksList);
		booksList.forEach(System.out::println);
	}
	
	@Test
	@Disabled
	public void addNewBookTest() {
		Books newBook = new Books("TestAuthor", "TestTitle", "TestBookAbstract", "TestStatus", "testFileName");
		booksDAO.addNewBook(newBook.getAuthor(), newBook.getTitle(), newBook.getBookAbstract(), newBook.getStatus(), newBook.getFileName());
		List<Books> listOfBooks = booksDAO.listBooks();
		Assertions.assertNotNull(listOfBooks.contains(newBook));
	}
	
	@Test
	@Disabled
	public void getBookByIdTest() {
		
		// set book ID which you want to select
		int bookId = 25;
		Books selectedBook = booksDAO.getBookById(bookId);
		Assertions.assertNotNull(selectedBook);
	}
	
	@Test
	@Disabled
	public void updateBookTest() {
		
		// set book ID which you want to update 
		int updatedBookId = 30;
		String newAuthor = "newAuthor";
		String newTitle = "newTitle";
		String newAbstract = "newAbstract";
		String newStatus = "newStatus";
		String newFileName = "newFileName";
				
		Books updatedBook = new Books(newAuthor, newTitle, newAbstract, newStatus, newFileName);
				
		booksDAO.updateBook(updatedBookId, updatedBook.getAuthor(), updatedBook.getTitle(), updatedBook.getBookAbstract(), updatedBook.getStatus());
		List<Books> listOfBooks = booksDAO.listBooks();
		Assertions.assertNotNull(listOfBooks.contains(updatedBook));
	}
	
	@Test
	@Disabled
	public void deleteBookCoverByIdTest() {
		
		// set book ID which cover you want to delete
		int bookId = 31;
		Books bookWithDeletedCover = booksDAO.deleteBookCoverById(bookId);
		Assertions.assertEquals(bookWithDeletedCover.getFileName(), null);
	}
	
	@Test
	@Disabled
	public void addBookCoverTest() {
		
		// set book ID which cover you want to add
		int bookId = 31;
		String newFileName = "testFileName";
		Books book = booksDAO.getBookById(bookId);
		booksDAO.addBookCover(bookId, newFileName);
		
		Books bookWithNewCover = booksDAO.getBookById(bookId);
		
		Assertions.assertEquals(bookWithNewCover.getFileName(), newFileName);
	}
	
	@Test
	@Disabled
	public void deleteBookByIdTest() {
		
		// set book ID which you want to delete
		int deleteBookId = 31;
		booksDAO.deleteBookById(deleteBookId);
		List<Books> listOfBooks = booksDAO.listBooks();
		boolean isDeletedBookPresent = listOfBooks.stream().anyMatch(user -> user.getId() == deleteBookId);
		Assertions.assertEquals(isDeletedBookPresent, false);
	}
}
