package library.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import library.entity.Books;


public class BooksDAO {

	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Books.class)
			.buildSessionFactory();
	
	public List<Books> listBooks(){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Books> books = session.createQuery("from books").getResultList();
		System.out.println(books);
		return books;
	}

	public void addNewBook(String author, String title, String bookAbstract, String status, String fileName) {
		Books newBook = new Books(author, title, bookAbstract, status, fileName);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(newBook);
		session.getTransaction().commit();	
	}

	public Books getBookById(int bookId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Books book = session.get(Books.class, bookId);
		session.getTransaction().commit();
		return book;
	}
	
	public void deleteBookById(int bookId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Books book = session.get(Books.class, bookId);
		session.delete(book);
		session.getTransaction().commit();
	}

	public void updateBook(int bookId, String author, String title, String bookAbstract, String status) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Books book = session.get(Books.class, bookId);
		book.setAuthor(author);
		book.setBookAbstract(bookAbstract);
		book.setStatus(status);
		book.setTitle(title);
		session.getTransaction().commit();
	}

	public Books deleteBookCoverById(int bookId) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Books book = session.get(Books.class, bookId);
		book.setFileName(null);
		session.getTransaction().commit();
		return book;
	}

	public void addBookCover(int bookId, String name) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Books book = session.get(Books.class, bookId);
		book.setFileName(name);
		session.getTransaction().commit();
	}
}
