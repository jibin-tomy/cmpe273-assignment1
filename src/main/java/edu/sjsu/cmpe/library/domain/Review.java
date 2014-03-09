package edu.sjsu.cmpe.library.domain;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class Review {
	@NotEmpty
    @Valid
	private int rating;
	private int id = 0 ;
	@NotEmpty
    @Valid
	private String comment;
	
	
	public void setId(int ReviewId){
		this.id = ReviewId;
	}
	
	public int getId(){
		return id;
	}
	
	public void setRating(int rating){
		this.rating = rating;	
	}
	
	public int getRating(){
		return rating;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}
	
	public boolean checkComment(){
		if(comment == "" || comment == null)
			return false;
		else
			return true;
	}
	
	public boolean checkRating(){
		if(rating < 1 || rating >5)
			return false;
		else
			return true;
	}
}
