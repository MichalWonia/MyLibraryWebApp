package library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "books")
@Table(name = "books")
public class Books {

	@Id
	@Column(name = "id")
	int id;
	
	@Column(name = "author")
	String author;
	
	@Column(name = "title")
	String title;
	
	@Column(name = "abstract")
	String bookAbstract;
	
	@Column(name = "status")
	String status;
	
	@Column(name = "file_name")
	String fileName;

	public Books() {
	}
	
	public Books(String author, String title, String book_abstract, String status, String fileName) {
		this.author = author;
		this.title = title;
		this.bookAbstract = book_abstract;
		this.status = status;
		this.fileName = fileName;
	}

	public int getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBookAbstract() {
		return bookAbstract;
	}

	public void setBookAbstract(String book_abstract) {
		this.bookAbstract = book_abstract;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", author=" + author + ", title=" + title + ", bookAbstract=" + bookAbstract
				+ ", status=" + status + ", fileName=" + fileName + "]";
	}
}
