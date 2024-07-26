package com.comicsColl.client.services;

import org.springframework.stereotype.Service;

import com.comicsColl.client.model.ComicBook;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParserService {

    public List<ComicBook> parseCsv(InputStream csvInputStream) {
        List<ComicBook> comicBooks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvInputStream))) {
            String line;
            boolean headerRow = true; // to skip the header row

            while ((line = reader.readLine()) != null) {
                if (headerRow) {
                    headerRow = false;
                    continue; // skip the header row
                }
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Regex to handle CSV with quotes
                if (values.length < 6) {
                    throw new IllegalArgumentException("Invalid CSV line: " + line);
                }

                try {
                    ComicBook comicBook = new ComicBook();
                    comicBook.setArtist(values[0].trim());
                    comicBook.setAuthor(values[1].trim());
                    comicBook.setIssue(Integer.parseInt(values[2].trim()));
                    comicBook.setPublisher(values[3].trim());
                    comicBook.setTitle(values[4].trim());
                    comicBook.setYear(Integer.parseInt(values[5].trim()));

                    comicBooks.add(comicBook);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format in CSV line: " + line, e);
                }
            }
        } catch (Exception e) {
            // Log and handle exceptions
        }
        return comicBooks;
    }
}


