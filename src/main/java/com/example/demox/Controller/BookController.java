package com.example.demox.Controller;

import com.example.demox.Pojo.BookPojo;
import com.example.demox.entity.Book;
import com.example.demox.projection.BookProjection;
import com.example.demox.service.BookService;
import com.example.demox.shared.pojo.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.codec.ServerSentEvent.builder;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public GlobalApiResponse<List<BookProjection>> getData(BookProjection bookProjection) {
        return GlobalApiResponse.
                <List<BookProjection>>builder()
                .data(this.bookService.findAll())
                .statusCode(200)
                .message("Successfully fetched")
                .build();
    }

    @PostMapping
    public GlobalApiResponse<String> save(@RequestBody BookPojo bookPojo) {
        this.bookService.saveData(bookPojo);
        return GlobalApiResponse.
                <String>builder()
                .data("saved")
                .statusCode(200)
                .message("Successfully saved")
                .build();
    }
    @PutMapping
    public GlobalApiResponse<Book> update(@RequestBody BookPojo bookPojo){
        Book updateBook  = this.bookService.saveData(bookPojo);
        return  GlobalApiResponse.<Book>builder()
                .data(updateBook)
                .statusCode(200)
                .message("Successfully updated")
                .build() ;
    }

    @DeleteMapping("/delete-book")
    public GlobalApiResponse<String> deleteBook(@RequestBody BookPojo bookPojo) {
        this.bookService.deleteAll(bookPojo);
        return GlobalApiResponse.<String>builder()
                .data("Book with groundId " + bookPojo.getGroundId() + ", bookName " + bookPojo.getBookName() + ", and userId " + bookPojo.getUserId() + " deleted successfully")
                .statusCode(200)
                .message("Successfully deleted")
                .build();
    }
}
