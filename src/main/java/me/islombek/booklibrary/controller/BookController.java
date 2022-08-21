package me.islombek.booklibrary.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.islombek.booklibrary.model.Book;
import me.islombek.booklibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(description = "Запросы для добавления, получения списка книг",
        tags = {"Запросы с книгами"})
@RestController
@RequestMapping("/api/v1")
public class BookController {

    /**
     * Интерфейс для реализации операций
     */
    @Autowired
    BookRepository bookRepository;


    /**
     * Метод, возвращающий список все книг, которые содержатся в таблице book,
     * отсортированные в обратном алфавитном порядке значения колонки book.title
     *
     * @return Список книг
     */
    @ApiOperation(value = "Получить список книг, отсортированных в обратном алфавитном порядке значения колонки Название книги", tags = {"Запросы с книгами"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно")})
    @GetMapping("/get-books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Book::getTitle)))
                .collect(Collectors.toList());
    }

    /**
     * Добавление новой книги
     *
     * @param book Объект, содержащий информацию о книге
     * @return Созданная книга
     */
    @ApiOperation(value = "Добавить новую книгу", tags = {"Запросы с книгами"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Книга добавлена"),
            @ApiResponse(code = 400, message = "Неправильные данные"),
            @ApiResponse(code = 409, message = "Книга уже существует")})
    @PostMapping("/create-book")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    /**
     * Получить все книги, сгрупированных по автору книги
     *
     * @return Список сгрупированных по автору книги
     */
    @ApiOperation(value = "Получить список книг, сгруппированных по автору книги", tags = {"Запросы с книгами"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно")})
    @GetMapping("/get-author-books")
    public ResponseEntity<?> getAuthorBooks() {
        Map<String, List<Book>> authorBooks = bookRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
        return ResponseEntity.ok(authorBooks);
    }
}
