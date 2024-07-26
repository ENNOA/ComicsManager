package com.comicsColl.client.services;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comicsColl.client.model.ComicBook;
import com.comicsColl.client.repository.ComicBookRepository;

@Service
public class ComicBookService {
	private final CsvParserService csvParserService;
	private final ComicBookRepository comicBookRepo;
	private static final Logger logger = LoggerFactory.getLogger(ComicBookService.class);
		
	@Autowired
    public ComicBookService (ComicBookRepository comicBookRepo, CsvParserService csvParserService) {
        this.comicBookRepo = comicBookRepo;
        this.csvParserService = csvParserService;
    }
	
	public List<ComicBook> findAll() {
		logger.info("Fetching all comic books.");
        return comicBookRepo.findAll();
    }

    public Optional<ComicBook> findById(Integer id) {
    	logger.info("Fetching all comic books with ID #"+id);
        return comicBookRepo.findById(id);
    }
    
    public Optional<ComicBook> findByIssue(Integer issue) {
    	logger.info("Fetching all comic books with issue #"+issue);
    	return comicBookRepo.findByIssue(issue);
    }
    
    public Optional<ComicBook> findByTitle(String title) {
    	logger.info("Fetching all comic books with title: "+title);
        return comicBookRepo.findByTitle(title);
    }
    
    public Optional<ComicBook> findByAuthor(String author) {
    	logger.info("Fetching all comic books with Author: "+author);
        return comicBookRepo.findByAuthor(author);
    }
    
    public Optional<ComicBook> findByPublisher(String publisher) {
    	logger.info("Fetching all comic books with publisher: "+publisher);
        return comicBookRepo.findByPublisher(publisher);
    }
    
    public Optional<ComicBook> findByYear(int year) {
    	logger.info("Fetching all comic books with year: "+year);
        return comicBookRepo.findByYear(year);
    }

    public ComicBook save(ComicBook comicBook) {
        return comicBookRepo.save(comicBook);
    }
    
    public void importCsvToDatabase(String tableName, InputStream csvInputStream) {
        try {
            List<ComicBook> comicBooks = csvParserService.parseCsv(csvInputStream);
            comicBookRepo.saveAll(comicBooks); // Use saveAll for batch inserts
            logger.info("Imported {} comic books into the database.", comicBooks.size());
        } catch (Exception e) {
            logger.error("Error importing CSV to database: {}", e.getMessage(), e);
            // Re-throw if needed or handle accordingly
        }
    }
    

}
