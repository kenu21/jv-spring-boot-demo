package mate.academy.boot.hellobootdemo.service;

import java.util.List;
import java.util.Optional;

import mate.academy.boot.hellobootdemo.entity.Book;
import mate.academy.boot.hellobootdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> updateById(Long bookId, Book book) {
        Optional<Book> byId = bookRepository.findById(bookId);
        if (byId.isPresent()) {
            Book bookFromBd = byId.get();
            bookFromBd.setTitle(book.getTitle());
            bookFromBd.setYear(book.getYear());
            bookFromBd.setPrice(book.getPrice());
            bookRepository.save(bookFromBd);
            return Optional.of(bookFromBd);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
