package org.example.books.service;

import org.example.books.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class ScrapeBook {

    private static final String URL_PATTERN = "http://books.toscrape.com/catalogue/page-%d.html";


    public List<Book> scrapeBooks(int pageNumber) {
        List<Book> books = new ArrayList<>();
        try {
            String url = String.format(URL_PATTERN, pageNumber);
            Document doc = Jsoup.connect(url).get();
            for (Element bookElement : doc.select(".product_pod")) {
                Book book = new Book();
                book.setTitle(bookElement.select("h3 a").attr("title"));
                book.setPrice(bookElement.select(".price_color").text());
                book.setAvailability(bookElement.select(".availability").text());
                book.setRating(bookElement.select(".star-rating").attr("class").replace("star-rating", "").trim());
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

}
