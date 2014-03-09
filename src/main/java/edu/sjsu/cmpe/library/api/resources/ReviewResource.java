package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ReviewResource {
	
	 /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public ReviewResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }
    
  //Work in progress
    @POST
    @Timed(name = "create-review")
    public Response createNewReview(@PathParam("isbn") LongParam isbn,Review request){
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	int reviewId;	
    	if (request.checkComment() == false || request.checkRating() == false )
    		return Response.status(400).entity("error insufficient parameters").build();
    	else{
	    	ArrayList<Review> reviews = book.getReviews();
	    	reviewId = reviews.size();
	    	++reviewId;
	    	request.setId(reviewId);
	    	reviews.add(request);
	    	ReviewsDto  links = new ReviewsDto(reviews);
	    	//LinksDto links = new LinksDto();
	    	//links.addLink(new LinkDto("view-review", "/books/"+isbn+"/reviews/"+reviewId, "GET"));
	    	
	    	return Response.status(201).entity(links).build();
    	}
    }
    
    @GET
    @Timed(name = "view-review")
    public Response viewAllReview(@PathParam("isbn") LongParam isbn){
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	ArrayList<Review> reviews = book.getReviews();
    	ReviewsDto allReviews = new ReviewsDto(reviews);
    	//LinksDto links = new LinksDto();
    	//links.addLink(new LinkDto("view-review", "/books/"+isbn+"/reviews/"+reviewId, "GET"));
    	return Response.status(201).entity(allReviews).build();
    	
    }
    
     @GET
     @Path("/{reviewId}")
     @Timed(name = "view-review")
     public Response viewReview(@PathParam("isbn") LongParam isbn, @PathParam("reviewId") int reviewId){
     	Book book = bookRepository.getBookByISBN(isbn.get());
     	ArrayList<Review> reviews = book.getReviews();
     	//Review tempReviews = new ArrayList<Review>();
     	int reviewIndex = 999;
     	for (int i = 0 ; i< reviews.size(); i++){
    		if (i != reviewId ){
    			continue;
    		}
    		else
    		{
    			reviewIndex = i-1;
    			break;
    		}
    	}
    	
    	Review review = reviews.get(reviewIndex);
    	ReviewDto reviewResponse = new ReviewDto(review);
     	//LinksDto links = new LinksDto();
     	//links.addLink(new LinkDto("view-review", "/books/"+isbn+"/reviews/"+reviewId, "GET"));
     	return Response.status(201).entity(reviewResponse).build();
     	
     }
    

}
