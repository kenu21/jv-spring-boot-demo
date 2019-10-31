package mate.academy.boot.hellobootdemo.service;

import java.util.List;
import java.util.Optional;

import mate.academy.boot.hellobootdemo.dto.BookDto;
import mate.academy.boot.hellobootdemo.entity.Book;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(Book book);

    Optional<Book> updateById(Long bookId, BookDto bookDto);

    void delete(Long bookId);
}
