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
import com.sun.jersey.spi.inject.Errors.ErrorMessage;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.BookAuthor;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public BookResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
	Book book = bookRepository.getBookByISBN(isbn.get());
	BookAuthor savedBook = new BookAuthor(book);
	BookDto bookResponse = new BookDto(savedBook);
	
	bookResponse.addLink(new LinkDto("view-book1", "/books/" + book.getIsbn(),
		"GET"));
	bookResponse.addLink(new LinkDto("update-book1",
		"/books/" + book.getIsbn(), "POST"));
	bookResponse.addLink(new LinkDto("delete-book1", "/books/" + book.getIsbn(),
			"DELETE"));
	bookResponse.addLink(new LinkDto("create-review1", "/books/" + book.getIsbn(),
			"POST"));
	// add more links
	
	return bookResponse;
    }
    

    @POST
    @Timed(name = "create-book")
    public Response createBook(@Valid Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
	Book savedBook = bookRepository.saveBook(request);
	if (request.getTitle().equals(null)){
		return Response.status(400).entity("error").build();
	}else{
		String location = "/books/" + savedBook.getIsbn();
		//BookDto bookResponse = new BookDto(savedBook);
		LinksDto bookResponse = new LinksDto();
		bookResponse.addLink(new LinkDto("view-book", location, "GET"));
		bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
		bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
		bookResponse.addLink(new LinkDto("create-review", location, "POST"));
		// Add other links if needed
	
		return Response.status(201).entity(bookResponse).build();
	}
    }
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response deleteBookByIsbn(@Valid @PathParam("isbn") LongParam isbn) {
    	
    	//int bookIndex = getBookIndex(isbn);
    	bookRepository.deletebook(isbn.get());
    	LinksDto links = new LinksDto();
    	links.addLink(new LinkDto("create-book", "/books", "POST"));

    	return Response.status(201).entity(links).build();

        }
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updatebookByIsbn(@Valid @PathParam("isbn") LongParam isbn,@QueryParam ("status") String status){
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	//BookDto bookResponse = new BookDto(book);
    	book.setStatus(status);
    	LinksDto Links = new LinksDto(); 
    	Links.addLink(new LinkDto("view-book1", "/books/" + book.getIsbn(),
    		"GET"));
    	Links.addLink(new LinkDto("update-book1",
    		"/books/" + book.getIsbn(), "POST"));
    	Links.addLink(new LinkDto("delete-book1", "/books/" + book.getIsbn(),
    			"DELETE"));
    	Links.addLink(new LinkDto("create-review1", "/books/" + book.getIsbn(),
    			"POST"));
    	
    	 //return bookResponse;
    	 return Response.status(201).entity(Links).build();

    }
 
    
    @GET
    @Path("/{isbn}/authors")
    @Timed (name="view-all-authors")
    public Response viewAllAuthor( @PathParam("isbn") LongParam isbn){
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	
    	ArrayList<Author> authors = book.getAuthors();
    	AuthorsDto authorsResponse = new AuthorsDto(authors);
    	//if(book.getAuthors().g)
    	
    	//LinksDto Links = new LinksDto();
    	//authorsResponse.addLink(new LinkDto("view-author", "/books/" + book.getIsbn()+"/authors/" ,"GET"));
    	return Response.status(201).entity(authorsResponse).build();
    	//return Response.status(201).entity("error").build();
    }
    
    
    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed (name="view-authors")
    public Response viewAuthor(@PathParam("isbn") LongParam isbn,@PathParam("id") long id){
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	int authorIndex = 999;
    	ArrayList<Author> authors = book.getAuthors();
    	
    	//System.out.print("jibin");
    	
    	for (int i = 0 ; i< authors.size(); i++){
    		if (id != authors.get(i).getId()){
    			continue;
    		}
    		else
    		{
    			authorIndex = i;
    			break;
    		}
    	}
    	/*ErrorMessage errorMsg = new ErrorMessage();
    	if (authorIndex == 999){
    		return Response.status(420).entity(errorMsg.getBookNotFoundErrorMessage()).build();
    	}*/
    	Author author = authors.get(authorIndex);
    	AuthorDto authorsResponse = new AuthorDto(author);
    	authorsResponse.addLink(new LinkDto("view-author", "/books/" + book.getIsbn()+"/authors/"+id ,"GET"));
    	
    	return Response.status(201).entity(authorsResponse).build();
    }
    
    
    
}




