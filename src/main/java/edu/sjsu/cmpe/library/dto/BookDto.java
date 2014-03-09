package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.BookAuthor;

@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto {
    private BookAuthor book;

    /**
     * @param book
     */
    public BookDto(BookAuthor book) {
	super();
	this.book = book;
    }

    /**
     * @return the book
     */
    public BookAuthor getBook() {
	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(BookAuthor book) {
	this.book = book;
    }
}
