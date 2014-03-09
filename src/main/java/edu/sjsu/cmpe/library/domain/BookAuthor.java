package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.dto.LinkDto;

public class BookAuthor {

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
	    
	    ArrayList<LinkDto> authors= new ArrayList<LinkDto>();;
	    ArrayList<LinkDto> reviews= new ArrayList<LinkDto>();
		private String publicationDate;
		private int numOfPages;
	   
	    
	    //private long numPages;


	    public BookAuthor(Book book) {
			// TODO Auto-generated constructor stub
	    	this.isbn = book.getIsbn();
	    	this.title = book.getTitle();
	    	this.publicationDate = book.getPublicationDate();
	    	this.language = book.getLanguage();
	    	this.numOfPages = book.getNoOfPages();
	    	this.status = book.getStatus();
	    	
	    	for(int i=0; i < book.reviews.size(); i++){
	    		this.reviews.add(new LinkDto("view-review", "/books/" + book.getIsbn() + "/reviews/" + book.reviews.get(i).getId(),"GET"));
	    	}
	    	
	    	for(int i=0; i < book.authors.size(); i++){
	    		this.authors.add(new LinkDto("view-author", "/books/" + book.getIsbn() + "/authors/" + book.authors.get(i).getId(),"GET"));
	    	}
	    	
		}

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
	    public ArrayList<LinkDto> getAuthors(){
	    	return authors;
	    }
	    
	    /**
	     * @param Author
	     *            the Author to set
	     */
	    public void setAuthors(ArrayList<LinkDto> authors){
	    	this.authors = authors;
	    }
	    
	    /**
	     * @param Review
	     *            the review to set
	     */
	    public void setReview(ArrayList<LinkDto> review){
	    	this.reviews = reviews;
	    }
	    
	    /**
	     * @return the review
	     */
	    public ArrayList<LinkDto> getReviews(){
	    	return reviews;
	    }
	    
	    

	
	
}
