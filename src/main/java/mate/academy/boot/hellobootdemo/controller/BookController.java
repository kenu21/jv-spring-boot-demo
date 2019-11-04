package mate.academy.boot.hellobootdemo.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import mate.academy.boot.hellobootdemo.dto.BookDto;
import mate.academy.boot.hellobootdemo.entity.Book;
import mate.academy.boot.hellobootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> allBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{bookId}")
    public Book getById(@PathVariable("bookId") Long id) {
        return bookService.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id " + id));
    }

    @PostMapping
    public void add(@RequestBody @Valid BookDto bookDto) {
        Book book = mappingDto(bookDto);
        bookService.save(book);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable("bookId") Long id) {
        bookService.delete(id);
    }

    @PutMapping("/{bookId}")
    public Book update(@PathVariable("bookId") Long bookId, @RequestBody @Valid BookDto bookDto) {
        Optional<Book> book = bookService.updateById(bookId, mappingDto(bookDto));
        if (book.isPresent()) {
            return book.get();
        } else {
            return null;
        }
    }

    private Book mappingDto(BookDto bookDto) {
        Book book = new Book(bookDto.getTitle(), bookDto.getYear(), bookDto.getPrice());
        return book;
    }
}
