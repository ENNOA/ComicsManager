package com.comicsColl.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comicsColl.client.model.ComicBook;

public interface ComicBookRepository extends JpaRepository <ComicBook, Integer> {

	Optional<ComicBook> findByIssue(Integer issue);

	Optional<ComicBook> findByTitle(String title);

	Optional<ComicBook> findByAuthor(String author);

	Optional<ComicBook> findByPublisher(String publisher);

	Optional<ComicBook> findByYear(int year);

}
