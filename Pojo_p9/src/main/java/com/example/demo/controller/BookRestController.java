package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
	
	// Temporary storage for books in memory
    private List<Book> books = new ArrayList<>();
    
	@PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
		
		books.add(book);
		
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(book);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> showBook(@PathVariable Integer id)
	{
		for (Book book : books) {

		    if (book.getId() == id) {
		        return ResponseEntity.ok(book);
		    }
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable Integer id)
	{
		for(Book existingbook: books)
		{
			if(existingbook.getId()==id)
			{
				existingbook.setTitle(book.getTitle());
				existingbook.setPrice(book.getPrice());
	            return ResponseEntity.ok(existingbook);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id)
	{
		for(Book deleteBook:books)
		{
			if(deleteBook.getId()==id)
			{
				books.remove(deleteBook);
				return ResponseEntity.ok("Book deleted succesfully");
			}
		}
		return ResponseEntity.notFound().build();
	}
	
}
