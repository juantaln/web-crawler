package com.portafolio.web_crawler.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portafolio.web_crawler.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}