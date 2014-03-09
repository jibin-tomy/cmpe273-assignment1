package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;


@JsonPropertyOrder (alphabetic = true)

public class AuthorsDto extends LinksDto {

	private ArrayList<Author> authors = new ArrayList<Author>();
	
	/*public AuthorsDto(ArrayList<Author> authors2) {
		// TODO Auto-generated constructor stub
	}
*/
	public AuthorsDto(ArrayList<Author> authors){
		this.authors = authors;
	}
	
	public ArrayList<Author> getAuthors(){
		return authors;
	}
	
}

