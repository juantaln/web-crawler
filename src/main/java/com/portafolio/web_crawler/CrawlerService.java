package com.portafolio.web_crawler;

import com.portafolio.web_crawler.entities.Book;
import com.portafolio.web_crawler.repositories.BookRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CrawlerService implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository; 

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🚀 Iniciando Crawler de libros...");

    
        bookRepository.deleteAll();

        String baseUrl = "http://books.toscrape.com/catalogue/page-";
        int totalPagesToScrape = 3;
        List<Book> allBooks = new ArrayList<>();

        for (int i = 1; i <= totalPagesToScrape; i++) {
            String urlToScrape = baseUrl + i + ".html";
            System.out.println("\n Rastreando página " + i + ": " + urlToScrape);

            try {
                Document doc = Jsoup.connect(urlToScrape).get();
                Elements bookArticles = doc.select("article.product_pod");

                if (bookArticles.isEmpty()) {
                    break;
                }

                for (Element article : bookArticles) {
                    String title = article.select("h3 a").attr("title");
                    String price = article.select("p.price_color").text();
                    allBooks.add(new Book(title, price));
                }
                System.out.println("📄 Se extrajeron " + bookArticles.size() + " libros de esta página.");

            } catch (Exception e) {
                System.err.println("❌ Error en la página " + i + ": " + e.getMessage());
            }
        }

        if (!allBooks.isEmpty()) {
            bookRepository.saveAll(allBooks);
            System.out.println("\n💾 Se guardaron " + allBooks.size() + " libros en la base de datos.");
        }

        System.out.println("\n✅ Crawler finalizado.");
    }
}