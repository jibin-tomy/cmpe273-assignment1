package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Book {
    private long isbn;
    @NotEmpty
    @NotNull
    private String title;
    
    private String status;
    private String language;

    @JsonProperty ("num-pages")
    public int num_pages;
    private int reviewId;
    
    @NotEmpty
    @NotNull
    @JsonProperty ("publication-date")
    public String publication_date;
    
    ArrayList<Author> authors;
    ArrayList<Review> reviews= new ArrayList<Review>();
   
    
    //private long numPages;


    /**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    
    /**
     * @return the status
     */
    public String getStatus() {
	return status;
    }
    
    /**
     * @param status
     *            the status to set
     */
     public void setStatus(String status) {
    	this.status = status;
        }
    
    
    /**
     * @return the language
     */
    public String getLanguage() {
	return language;
    }
    
    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(String language) {
    	this.language = language;
        }
    
    /**
     * @return the Author
     */
    public ArrayList<Author> getAuthors(){
    	return authors;
    }
    
    /**
     * @param Author
     *            the Author to set
     */
    public void setAuthors(ArrayList<Author> authors){
    	this.authors = authors;
    }
    
    /**
     * @param Review
     *            the review to set
     */
    public void setReview(ArrayList<Review> review){
    	this.reviews = reviews;
    }
    
    /**
     * @return the review
     */
    public ArrayList<Review> getReviews(){
    	return reviews;
    }
    
    public String getPublicationDate(){
    	return publication_date;
    }
    
    public int getNoOfPages(){
    	return num_pages;
    }
    
}
