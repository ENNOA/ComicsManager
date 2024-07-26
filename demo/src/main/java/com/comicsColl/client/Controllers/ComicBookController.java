package com.comicsColl.client.Controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.comicsColl.client.exceptions.ResourceNotFoundException;
import com.comicsColl.client.model.ComicBook;
import com.comicsColl.client.repository.ComicBookRepository;
import com.comicsColl.client.services.ComicBookService;

@RestController
@RequestMapping("/api/comics")
public class ComicBookController {
    private final ComicBookRepository comicBookRepository;
    private final ComicBookService comicBookservice;

    
    public ComicBookController(ComicBookRepository comicBookRepository, ComicBookService comicBookservice) {
        this.comicBookRepository = comicBookRepository;
		this.comicBookservice = comicBookservice;
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello World!";
    }
    
   
    @GetMapping
    public List<ComicBook> getAllComics() {
        return comicBookRepository.findAll();
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComicBook createComicBook(@RequestBody ComicBook comicBook) {
		return comicBookRepository.save(comicBook);
	}
/*
 *  this needs the logic to be fixed up to handle the csv import. refer to the csvreader program for help
 */
	@PostMapping("/uploadCsv")
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
	    if (!file.isEmpty()) {
	        try {
	            String tableName = "comic_book"; // Assuming you have a single table for comic books
	            comicBookservice.importCsvToDatabase(tableName, file.getInputStream());
	            return ResponseEntity.ok("File uploaded and data imported successfully!");
	        } catch (Exception e) {
	            // Handle exceptions related to file processing and database operations
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty file cannot be uploaded.");
	    }
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComicBook> getComicById(@PathVariable Integer id) {
		ComicBook comicBook = comicBookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ComicBook not found with id: " + id));
		return ResponseEntity.ok(comicBook);
	}
	@GetMapping("/search")
	public ResponseEntity<List<ComicBook>> searchComics(
	        @RequestParam(required = false) String title,
	        @RequestParam(required = false) Integer issue,
	        @RequestParam(required = false) String author,
	        @RequestParam(required = false) String artist,
	        @RequestParam(required = false) String publisher,
	        @RequestParam(required = false) Integer year) {
	    
		  List<ComicBook> comics = comicBookservice.findAll();
		    Stream<ComicBook> stream = comics.stream();

		    if (title != null) {
		        stream = stream.filter(comic -> comic.getTitle().equalsIgnoreCase(title));
		    }
		    if (issue != null) {
		        stream = stream.filter(comic -> comic.getIssue()==(issue));
		    }
		    if (author != null) {
		        stream = stream.filter(comic -> comic.getAuthor().equalsIgnoreCase(author));
		    }
		    if (artist != null) {
		        stream = stream.filter(comic -> comic.getArtist().equalsIgnoreCase(artist));
		    }
		    if (publisher != null) {
		        stream = stream.filter(comic -> comic.getPublisher().equalsIgnoreCase(publisher));
		    }
		    if (year != null) {
		        stream = stream.filter(comic -> comic.getYear()==(year));
		    }

		    return ResponseEntity.ok(stream.collect(Collectors.toList()));
		}
	}



