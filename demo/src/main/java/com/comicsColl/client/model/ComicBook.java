package com.comicsColl.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ComicBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int issue;
	private String title;
	private String author;
	private String artist;
	private String publisher;
	private int year;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getArtist() {
		return artist;
	}

	public String getPublisher() {
		return publisher;
	}

	public int getYear() {
		return year;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getIssue() {
		return issue;
	}

	public void setIssue(int issue) {
		this.issue = issue;
	}
}
