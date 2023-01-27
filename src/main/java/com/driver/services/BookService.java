package com.driver.services;

import com.driver.models.Author;
import com.driver.models.Book;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository2;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorService authorService;

    public void createBook(Book book){
//        if(book != null) {
//            if(book.getAuthor() != null) {
//                Author author = book.getAuthor();
//                author.getBooksWritten().add(book);
//                book.setAuthor(author);
//                book.setAvailable(true);
//                authorRepository.save(author);
//                bookRepository2.save(book);
//            }
//        }

            try {
                int id = book.getAuthor().getId();
                Author author = authorRepository.findById(id).get();
                List<Book> bookList = author.getBooksWritten();
                if(bookList==null) {
                    bookList = new ArrayList<>();
                }
                bookList.add(book);
                book.setAuthor(author);
                author.setBooksWritten(bookList);
                authorRepository.save(author);

            }
            catch (Exception e) {

                bookRepository2.save(book);
            }
        }



    public List<Book> getBooks(String genre, boolean available, String author){
         //find the elements of the list by yourself
        if(genre!=null && author!=null){
           return bookRepository2.findBooksByGenreAuthor(genre,author,available);
        } else if (author!=null) {
            return bookRepository2.findBooksByAuthor(author,available);
        } else if (genre!=null) {
            return bookRepository2.findBooksByGenre(genre,available);
        } else if (available) {
            return bookRepository2.findByAvailability(available);
        }else {
            return bookRepository2.findAll();
        }
    }
}
